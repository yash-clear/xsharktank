package com.example.xsharktank.service;

import com.example.xsharktank.model.CounterOffer;
import com.example.xsharktank.model.Pitch;
import com.example.xsharktank.repository.PitchRepository;
import com.example.xsharktank.request.PostPitch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PitchServiceImpl implements PitchService{

    @Autowired
    private PitchRepository pitchRepository;
    @Override
    public String postPitch(PostPitch postPitch) {
        List<Pitch> pitchesList = pitchRepository.findAll();
        Integer currentId = pitchesList.size() + 1;
        List<CounterOffer> counterOffers = new ArrayList<>();
        Pitch pitch = new Pitch(currentId.toString(), postPitch.getEntrepreneur(), postPitch.getPitchTitle(),
                postPitch.getPitchIdea(), postPitch.getAskAmount(), postPitch.getEquity(), counterOffers);
        pitchRepository.save( pitch);
        return currentId.toString();
    }

    @Override
    public String makeCounterOffer(String id, CounterOffer counterOffer) {
        Optional<Pitch> pitch = pitchRepository.findById( id);
        if (pitch.isPresent()){
            Pitch currentPitch = pitch.get();
            List<CounterOffer> currentCounterOffers = currentPitch.getOffers();
            Integer currentOffers = currentCounterOffers.size();
            currentOffers += 1;
            String offerId = currentOffers.toString();
            counterOffer.setId(offerId);
            currentCounterOffers.add( counterOffer);
            currentPitch.setOffers( currentCounterOffers);
            pitchRepository.save(currentPitch);
            return offerId;
        }
        return "";
    }

    @Override
    public List<Pitch> getAllPitches() {
        List<Pitch> pitches = pitchRepository.findAll();
        Collections.reverse(pitches);
        return pitches;
    }

    @Override
    public Pitch getPitchById(String id) {
        Optional<Pitch> pitch = pitchRepository.findById( id);
        if(pitch.isPresent()){
            return pitch.get();
        }
        return null;
    }
}
