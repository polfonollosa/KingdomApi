package com.tecnocampus.examsimulation.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;

public class KingdomDTO{

    @JsonProperty("id")
    private String id;

    @JsonProperty("dateOfCreation")
    private String dateOfCreation;

    @Min(0)
    @JsonProperty("gold")
    private int gold;

    @Min(0)
    @JsonProperty("citizens")
    private int citizens;

    @Min(0)
    @JsonProperty("food")
    private int food;

    public KingdomDTO() {
    }

    public KingdomDTO(String id, String dateOfCreation, int gold, int citizens, int food) {
        this.id = id;
        this.dateOfCreation = dateOfCreation;
        this.gold = gold;
        this.citizens = citizens;
        this.food = food;
    }

}