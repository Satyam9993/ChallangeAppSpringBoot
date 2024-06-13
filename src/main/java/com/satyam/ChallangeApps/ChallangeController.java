package com.satyam.ChallangeApps;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challanges")
public class ChallangeController {
    private  ChallangeService challangeService;

    // This line is for IOC Inversion of Control
    public ChallangeController(ChallangeService challangeService){
        this.challangeService = challangeService;
    }

    @GetMapping("")
    public ResponseEntity<List<Challenge>> getAllChallanges(){
        return new ResponseEntity<>(challangeService.getAllChallanges(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> AddChallange(@RequestBody Challenge challenge){
        return challangeService.AddChallange(challenge) == true ? new ResponseEntity<>("Successfully Added", HttpStatus.CREATED) : new ResponseEntity<>("Not Added", HttpStatus.BAD_GATEWAY);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Challenge> getChallange(@PathVariable Long id){
        Challenge challenge = challangeService.getChallenge(id);
        if(challenge != null){
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateChallange(@PathVariable Long id,@RequestBody Challenge challenge){
        boolean anyChallange = challangeService.updateChallange(id, challenge);
        if(anyChallange){
            return new ResponseEntity<>("Changes has been done.", HttpStatus.OK);
        }
        return new ResponseEntity<>("No changes",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChallange(@PathVariable Long id){
        boolean anyChallange = challangeService.deleteChallenge(id);
        if(anyChallange){
            return new ResponseEntity<>("Delete has been done.", HttpStatus.OK);
        }
        return new ResponseEntity<>("No changes",HttpStatus.NOT_FOUND);
    }
}
