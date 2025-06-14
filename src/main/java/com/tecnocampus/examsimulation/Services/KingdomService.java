package com.tecnocampus.examsimulation.Services;

import com.tecnocampus.examsimulation.Entities.Kingdom;
import com.tecnocampus.examsimulation.Persistence.KingdomRepository;
import com.tecnocampus.examsimulation.Utilities.BadRequestException;
import com.tecnocampus.examsimulation.Utilities.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class KingdomService {

    private KingdomRepository repo;

    public KingdomService(KingdomRepository repo) {
        this.repo = repo;
    }

    public Kingdom getKingdom(String kingdomId) {
        return repo.findKingdom(kingdomId)
                .orElseThrow(() -> new NotFoundException("kingdom not found"));
    }

    public Kingdom findTheRichestKingdom(){
        return repo.findTheRichest()
                .orElseThrow(() -> new NotFoundException("there are no kingdoms in the database"));
    }

    public void createKingdom(int gold, int citizens, int food) {
        Kingdom kingdom = new Kingdom(gold, citizens, food);
        repo.save(kingdom);
    }

    public void deleteKingdom(String kingdomId) {
        repo.deleteKingdom(kingdomId);
    }

    public void investFood(Kingdom kingdom) throws BadRequestException {
        existsKingdom(kingdom);
        kingdom.investFood();
        updateKingdomState(kingdom);
    }

    public void investCivilian(Kingdom kingdom) throws BadRequestException {
        existsKingdom(kingdom);
        kingdom.investCivilian();
        updateKingdomState(kingdom);
    }

    private void existsKingdom(Kingdom kingdom) {
        if(kingdom == null || !repo.existsKingdom(kingdom.getId())){
            throw new NotFoundException("kingdom not found");
        }
    }

    public void attackKingdom(Kingdom attacker, Kingdom defender){

        existsKingdom(attacker);
        existsKingdom(defender);
        attacker.attackKingdom(defender);
        updateKingdomState(attacker);
        updateKingdomState(defender);

    }

    public boolean produce(Kingdom kingdom) throws BadRequestException {
        existsKingdom(kingdom);
        try {
            kingdom.produce();
        }
        catch (BadRequestException e){
            deleteKingdom(kingdom.getId());
            return false;
        }
        updateKingdomState(kingdom);
        return true;
    }

    public List<Kingdom> getAllKingdoms() {
        List<Kingdom> kingdoms = repo.findAll();
        if(kingdoms.isEmpty()){
            throw new NotFoundException("there are no kingdoms in the database");
        }
        return kingdoms;
    }

    private void updateKingdomState(Kingdom k) {
        existsKingdom(k);
        repo.updateKingdom(k.getId(), k.getGold(), k.getCitizens(), k.getFood());
    }

}