package com.tecnocampus.examsimulation.DTO;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateKingdomRequest {

    @Min(0)
    int gold;

    @Min(0)
    int citizens;

    @Min(0)
    int food;

    public CreateKingdomRequest(int gold, int citizens, int food) {
        this.gold = gold;
        this.citizens = citizens;
        this.food = food;
    }



}
