package com.example.xsharktank.service;

import com.example.xsharktank.model.CounterOffer;
import com.example.xsharktank.model.Pitch;
import com.example.xsharktank.request.PostPitch;

import java.util.List;

public interface PitchService {
    String postPitch(PostPitch postPitch);

    String makeCounterOffer(String id, CounterOffer counterOffer);

    List<Pitch> getAllPitches();

    Pitch getPitchById(String id);
}
