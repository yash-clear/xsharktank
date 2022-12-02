package com.example.xsharktank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pitch {

    @Id
    private String id;

    private String entrepreneur;

    private String pitchTitle;

    private String pitchIdea;

    private float askAmount;

    private float equity;

    private List<CounterOffer> offers;

}
