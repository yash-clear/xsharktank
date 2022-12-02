package com.example.xsharktank.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostPitch {

    private String entrepreneur;

    private String pitchTitle;

    private String pitchIdea;

    private float askAmount;

    private float equity;
}
