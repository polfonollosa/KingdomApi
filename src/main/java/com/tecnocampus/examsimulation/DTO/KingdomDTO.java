package com.tecnocampus.examsimulation.DTO;

import jakarta.validation.constraints.Min;

public class KingdomDTO{

    //Valores que necesitamos / mandamos. Aisalando id i createAt que los gestiona el repositorio

    @Min(0)
    private int gold;

    @Min(0)
    private int citizens;

    @Min(0)
    private int food;

    public KingdomDTO() {
    }

    KingdomDTO(int gold, int citizens, int food) {
        this.gold = gold;
        this.citizens = citizens;
        this.food = food;
    }

}