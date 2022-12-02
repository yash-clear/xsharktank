package com.example.xsharktank.controller;

import com.example.xsharktank.model.CounterOffer;
import com.example.xsharktank.model.Pitch;
import com.example.xsharktank.request.PostPitch;
import com.example.xsharktank.service.PitchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/pitches")
public class PitchController {

    @Autowired
    private PitchService pitchService;

    @PostMapping("")
    public ResponseEntity<Map<String, String>> postPitch(@RequestBody PostPitch postPitch){
        if(!isPitchRequestValid(postPitch)){
            return new ResponseEntity<>( HttpStatusCode.valueOf(400));
        }
        String id = pitchService.postPitch( postPitch);
        Map<String, String> response = new HashMap<>();
        response.put("id", id);
        return new ResponseEntity<>( response, HttpStatusCode.valueOf(201));
    }

    @PostMapping("/{id}/makeOffer")
    public ResponseEntity<Map<String, String>> makeCounterOffer(@PathVariable("id") String id,
                                    @RequestBody CounterOffer counterOffer){
        if(!isCounterOfferValid(counterOffer)){
            return new ResponseEntity<>( HttpStatusCode.valueOf(400));
        }
        String generated_id = pitchService.makeCounterOffer( id, counterOffer);
        Map<String, String> response = new HashMap<>();
        if(id.equals("")){
            return new ResponseEntity<>( response, HttpStatusCode.valueOf(404));
        }
        response.put("id", generated_id);
        return new ResponseEntity<>( response, HttpStatusCode.valueOf(201));
    }

    @GetMapping("")
    public ResponseEntity<List<Pitch>> getAllPitches(){
        List<Pitch> pitchesList = pitchService.getAllPitches();
        return new ResponseEntity<>( pitchesList, HttpStatusCode.valueOf(200));
    }

    @GetMapping("{id}")
    public ResponseEntity<Pitch> getPitch(@PathVariable("id") String id){
        Pitch pitch = pitchService.getPitchById(id);
        if(Objects.isNull(pitch)){
            new ResponseEntity<>( HttpStatusCode.valueOf(404));
        }
        return new ResponseEntity<>( pitch, HttpStatusCode.valueOf(200));
    }
    private Boolean isPitchRequestValid(PostPitch postPitch){
        if(Objects.isNull( postPitch.getPitchIdea())){
            return false;
        }
        else if(Objects.isNull( postPitch.getPitchTitle())) {
            return false;
        }
        else if(Objects.isNull(postPitch.getEntrepreneur())){
            return false;
        }
        return true;
    }
    private Boolean isCounterOfferValid( CounterOffer counterOffer){
        if(Objects.isNull( counterOffer.getInvestor())) {
            return false;
        }
        return true;
    }
}
