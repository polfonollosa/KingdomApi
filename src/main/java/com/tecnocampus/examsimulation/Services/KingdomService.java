package com.tecnocampus.examsimulation.Services;

import com.tecnocampus.examsimulation.Entities.Kingdom;
import com.tecnocampus.examsimulation.Persistence.KingdomRepository;
import com.tecnocampus.examsimulation.Utilities.BadRequestException;
import com.tecnocampus.examsimulation.Utilities.NotAcceptableException;
import com.tecnocampus.examsimulation.Utilities.NotFoundException;
import org.springframework.data.relational.core.sql.Not;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KingdomService {

    private final KingdomRepository repo;

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

    public Kingdom createKingdom(int gold, int citizens, int food) throws NotAcceptableException {
        Kingdom kingdom = new Kingdom(gold, citizens, food);
        repo.save(kingdom);
        return kingdom;
    }

    public void deleteKingdom(String kingdomId) {
        repo.deleteKingdom(kingdomId);
    }

    public void investFood(Kingdom kingdom) throws NotAcceptableException {
        existsKingdom(kingdom);
        kingdom.investFood();
        updateKingdomState(kingdom);
    }

    public void investCivilian(Kingdom kingdom) throws NotAcceptableException {
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

    public void produce(Kingdom kingdom){
        existsKingdom(kingdom);
        try {
            kingdom.produce();
        }
        catch (NotAcceptableException e){
            deleteKingdom(kingdom.getId());
            throw new NotAcceptableException("not acceptable, borrado");
        }
        updateKingdomState(kingdom);
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