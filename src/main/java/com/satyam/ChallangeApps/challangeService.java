package com.satyam.ChallangeApps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
class ChallangeService {
    private List<Challenge> challanges = new ArrayList<>();
    @Autowired
    ChallengeRepo challengeRepo;
    private Long nextId = 1L;
    public List<Challenge> getAllChallanges(){
//        return challanges;
        return challengeRepo.findAll();
    }

    public boolean AddChallange(Challenge challange){
        if(challange == null){
            return false;
        }
        challange.setId(nextId++);
//        challanges.add(challange);
        challengeRepo.save(challange);
        return true;
    }

    public Challenge getChallenge(Long id){
//        for(Challenge challenge : challanges){
//            if(challenge.getId() == id){
//                return challenge;
//            }
//        }
        Optional<Challenge> ch =  challengeRepo.findById(id);
        return ch.orElse(null);
    }

    public Challenge getChallengeByMonth(String month){
        Optional<Challenge> ch =  challengeRepo.findByMonthIgnoreCase(month);
        return ch.orElse(null);
    }

    public boolean updateChallange(Long id, Challenge challenge){
        Optional<Challenge> ch  = challengeRepo.findById(id);
        if(ch.isPresent()){
            Challenge challengeToUpdate = ch.get();
            if(challenge.getDescription() != null)
                challengeToUpdate.setDescription(challenge.getDescription());
            if(challenge.getMonth() != null)
                challengeToUpdate.setMonth(challenge.getMonth());
            challengeRepo.save(challengeToUpdate);
            return true;
        }

//        for(Challenge ch : challanges){
//            if(ch.getId() == id){
//                if(challenge.getDescription() != null)
//                    ch.setDescription(challenge.getDescription());
//                if(challenge.getMonth() != null)
//                    ch.setMonth(challenge.getMonth());
//                return true;
//            }
//        }
        return false;
    }


    public boolean deleteChallenge(Long id){
//        return challanges.removeIf(ch -> ch.getId() == id);
        Optional<Challenge> ch  = challengeRepo.findById(id);
        if(ch.isPresent()){
            challengeRepo.deleteById(id);
            return true;
        }
        return false;
    }

}
