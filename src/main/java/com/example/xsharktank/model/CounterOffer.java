package com.example.xsharktank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounterOffer {
    @Id
    private String id;
    private String investor;
    private float amount;
    private float equity;
    private String comment;
}
