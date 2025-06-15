package com.tecnocampus.examsimulation.DTO;

import jakarta.validation.constraints.Min;

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

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getCitizens() {
        return citizens;
    }

    public void setCitizens(int citizens) {
        this.citizens = citizens;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

}
