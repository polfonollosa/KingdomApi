package com.tecnocampus.examsimulation.Controllers;

import com.tecnocampus.examsimulation.DTO.CreateKingdomRequest;
import com.tecnocampus.examsimulation.DTO.KingdomDTO;
import com.tecnocampus.examsimulation.Entities.Kingdom;
import com.tecnocampus.examsimulation.Services.KingdomService;
import com.tecnocampus.examsimulation.Utilities.NotAcceptableException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kingdoms")
public class KingdomController {

    private final KingdomService kingdomService;

    public KingdomController(KingdomService kingdomService) {
        this.kingdomService = kingdomService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<KingdomDTO> getAKingdom(@PathVariable String id){
        Kingdom k = kingdomService.getKingdom(id);
        return new ResponseEntity<>(k.toDTO(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<KingdomDTO> createKingdom(@Valid @RequestBody CreateKingdomRequest request) {
        Kingdom k = kingdomService.createKingdom(request.getGold(), request.getCitizens(), request.getFood());
        return new ResponseEntity<>(k.toDTO(), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<KingdomDTO> produceKingdom(@PathVariable String id) {
        Kingdom k = kingdomService.getKingdom(id);
        kingdomService.produce(k);
        return new ResponseEntity<>(k.toDTO(), HttpStatus.OK);
    }

    @PostMapping("/{id}/invest")
    public ResponseEntity<KingdomDTO> invest(
            @PathVariable String id,
            @RequestParam String type
    ) {
        Kingdom k = kingdomService.getKingdom(id);

        switch (type.toLowerCase()) {
            case "food" -> kingdomService.investFood(k);
            case "citizens" -> kingdomService.investCivilian(k);
            default -> throw new NotAcceptableException("Invalid investment type: " + type);
        }

        return ResponseEntity.ok(k.toDTO());
    }

    @GetMapping ("/richest")
    public ResponseEntity<KingdomDTO> getRichestKingdom() {
        Kingdom k = kingdomService.findTheRichestKingdom();
        return new ResponseEntity<>(k.toDTO(), HttpStatus.OK);
    }

    @PostMapping("/{attackerId}/attack/{targetId}")
    public ResponseEntity<KingdomDTO> attackKingdom(@PathVariable String attackerId, @PathVariable String targetId) {
        Kingdom k = kingdomService.getKingdom(attackerId);
        Kingdom d = kingdomService.getKingdom(targetId);
        kingdomService.attackKingdom(k, d);
        return new ResponseEntity<>(k.toDTO(), HttpStatus.OK);
    }


}
