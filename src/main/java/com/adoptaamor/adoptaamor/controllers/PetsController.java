package com.adoptaamor.adoptaamor.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adoptaamor.adoptaamor.models.Pets;
import com.adoptaamor.adoptaamor.services.PetsService;

@CrossOrigin(origins = "http://localhost:5173") // Cambia el puerto al del frontend
@RestController
public class PetsController {
    private final PetsService petsService;

    public PetsController(PetsService petsService) {
        this.petsService = petsService;
    }

    @GetMapping("/pets")
    public ResponseEntity<List<Pets>> getPets() {
        List<Pets> pets = petsService.getPets();
        if (pets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pets);
    }

    @PostMapping("/pets/create")
    public ResponseEntity<Object> addPets(@RequestBody Pets pets) {
        return petsService.addPets(pets);
    }

    @DeleteMapping("/pets/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Optional<Pets> existingPets = this.petsService.findById(id);
        if (existingPets.isPresent()) {
            this.petsService.delete(existingPets.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<Object> updatePets(@PathVariable("id") int id, @RequestBody Pets pets) {
        return petsService.updatePets(id, pets);
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<?> getPetsById(@PathVariable int id) {
        Optional<Pets> pets = petsService.getPetsById(id);
        if (pets.isPresent()) {
            return ResponseEntity.ok(pets.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pets not found");
        }
    }
}
