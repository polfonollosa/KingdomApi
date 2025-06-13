package com.tecnocampus.examsimulation.Services;

import com.tecnocampus.examsimulation.Entities.Kingdom;
import com.tecnocampus.examsimulation.Persistence.KingdomRepository;
import com.tecnocampus.examsimulation.Utilities.BadRequestException;
import com.tecnocampus.examsimulation.Utilities.NotFoundException;

public class KingdomService {

    private KingdomRepository repo;

    public KingdomService(KingdomRepository repo) {
        this.repo = repo;
    }

    public Kingdom getKingdom(String kingdomId) {
        return repo.findKingdom(kingdomId)
                .orElseThrow(() -> new NotFoundException("Kingdom not found"));
    }

    public Kingdom findTheRichestKingdom(){
        return repo.findTheRichest()
                .orElseThrow(() -> new BadRequestException("Llista buida"));
    }

    public void createKingdom(int gold, int citizens, int food) {
        Kingdom kingdom = new Kingdom(gold, citizens, food);
        repo.save(kingdom);
    }

    public void deleteKingdom(String kingdomId) {
        if(!repo.existsKingdom(kingdomId)){
            throw new NotFoundException("can't delete Kingdom not found");
        }
        repo.deleteKingdom(kingdomId);
    }

    public void investFood(Kingdom kingdom) throws BadRequestException {
        if(!repo.existsKingdom(kingdom.getId()) || kingdom == null){
            throw new NotFoundException("Kingdom not found");
        }
        kingdom.investFood();
        updateKingdomState(kingdom);
    }

    public void investCivilian(Kingdom kingdom) throws BadRequestException {
        if(!repo.existsKingdom(kingdom.getId()) && kingdom != null){
            throw new NotFoundException("Kingdom not found");
        }
        kingdom.investCivilian();
        updateKingdomState(kingdom);
    }

    public void attackKingdom(Kingdom attacker, Kingdom defender){

        if(!repo.existsKingdom(attacker.getId()) && attacker != null){
            throw new NotFoundException("Kingdom not found");
        }
        if(!repo.existsKingdom(defender.getId()) && defender != null){
            throw new NotFoundException("Kingdom not found");
        }
        attacker.attackKingdom(defender);
        updateKingdomState(attacker);
        updateKingdomState(defender);

    }

    private void updateKingdomState(Kingdom k) {
        repo.updateKingdom(k.getId(), k.getGold(), k.getCitizens(), k.getFood());
    }

}
