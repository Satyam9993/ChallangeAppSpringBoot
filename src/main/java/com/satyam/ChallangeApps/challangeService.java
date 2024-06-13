package com.satyam.ChallangeApps;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
class ChallangeService {
    private List<Challenge> challanges = new ArrayList<>();
    private Long nextId = 1L;
    public List<Challenge> getAllChallanges(){
        return challanges;
    }

    public boolean AddChallange(Challenge challange){
        if(challange == null){
            return false;
        }
        challange.setId(nextId++);
        challanges.add(challange);
        return true;
    }

    public Challenge getChallenge(Long id){
        for(Challenge challenge : challanges){
            if(challenge.getId() == id){
                return challenge;
            }
        }
        return null;
    }

    public boolean updateChallange(Long id, Challenge challenge){
        for(Challenge ch : challanges){
            if(ch.getId() == id){
                if(challenge.getDescription() != null)
                    ch.setDescription(challenge.getDescription());
                if(challenge.getMonth() != null)
                    ch.setMonth(challenge.getMonth());
                return true;
            }
        }
        return false;
    }


    public boolean deleteChallenge(Long id){
        return challanges.removeIf(ch -> ch.getId() == id);
    }

}
