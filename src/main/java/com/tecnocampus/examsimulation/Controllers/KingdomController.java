package com.tecnocampus.examsimulation.Controllers;

import com.tecnocampus.examsimulation.DTO.CreateKingdomRequest;
import com.tecnocampus.examsimulation.DTO.KingdomDTO;
import com.tecnocampus.examsimulation.Entities.Kingdom;
import com.tecnocampus.examsimulation.Services.KingdomService;
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


}
