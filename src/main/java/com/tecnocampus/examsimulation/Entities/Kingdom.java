package com.tecnocampus.examsimulation.Entities;

import com.tecnocampus.examsimulation.DTO.KingdomDTO;
import com.tecnocampus.examsimulation.Utilities.BadRequestException;
import com.tecnocampus.examsimulation.Utilities.NotAcceptableException;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class Kingdom {

    private String id;
    private String dateOfCreation;
    private int gold;
    private int citizens;
    private int food;

    public Kingdom(){}

    public Kingdom(int gold , int citizens , int food) {
        id = UUID.randomUUID().toString();
        dateOfCreation = LocalDate.now().toString();
        if (gold < 0 || citizens < 0 || food < 0){
            throw new NotAcceptableException("must be positive params");
        }
        this.gold = gold;
        this.citizens = citizens;
        this.food = food;
    }

    public void investFood(){
        if(gold < 5){
            throw new NotAcceptableException("You don't have enough gold >5");
        }
        gold -= 5;
        food += 10;
    }

    public void investCivilian(){
        if(gold < 5){
            throw new NotAcceptableException("You don't have enough gold >5");
        }
        gold -= 5;
        citizens += 5;
    }

    public void produce(){
        if(citizens < 5 && food < 5){
            throw new NotAcceptableException("You don't have enough food >5");
        }
        if(citizens > food){
            citizens /= 2;
            food = 0;
            return;
        }
        gold *= 2;
        food /= 2;
    }

    public void attackKingdom(Kingdom kingdom){
        if(kingdom.citizens >= this.citizens){
            kingdom.gold += this.gold;
            this.gold = 0;
            this.citizens /= 2;
            kingdom.citizens += this.citizens;
        }
        else{
            this.gold += kingdom.gold;
            kingdom.gold = 0;
            kingdom.citizens /= 2;
            this.citizens += kingdom.citizens;
        }
    }

    //Getters y Setters

    public void setParam(String id, String dat){
        this.id = id;
        this.dateOfCreation = dat;
    }

    public String getId() {
        return id;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public int getGold() {
        return gold;
    }

    public int getCitizens() {
        return citizens;
    }

    public int getFood() {
        return food;
    }

    public void setGold(int gold) {
        if(gold < 0){
            throw new BadRequestException("invalid gold must be positive");
        }
        this.gold = gold;
    }

    public void setCitizens(int citizens) {
        if(citizens < 0){
            throw new BadRequestException("invalid citizens must be positive");
        }
        this.citizens = citizens;
    }

    public void setFood(int food) {
        if(food < 0){
            throw new BadRequestException("invalid food must be positive");
        }
        this.food = food;
    }

    public KingdomDTO toDTO(){

        KingdomDTO dto = new KingdomDTO(this.id, this.dateOfCreation, this.gold, this.citizens, this.food);

        return dto;
    }

}
