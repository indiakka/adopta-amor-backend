package com.adoptaamor.adoptaamor.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.adoptaamor.adoptaamor.models.Pets;
import com.adoptaamor.adoptaamor.payloads.AnimalDto;
import com.adoptaamor.adoptaamor.services.PetsService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class PetsController {

    private final PetsService petsService;

    public PetsController(PetsService petsService) {
        this.petsService = petsService;
    }

    // Obtener todos los animales
    @GetMapping("/pets")
    public ResponseEntity<List<Pets>> getPets() {
        List<Pets> pets = petsService.getPets();
        if (pets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pets);
    }

    // Crear un nuevo animal
    @PostMapping("/pets")
    public ResponseEntity<?> crearAnimal(@RequestBody AnimalDto animalDto) {
        Pets pet = new Pets();
        pet.setNombre(animalDto.getNombre());
        pet.setRaza(animalDto.getRaza());
        pet.setTipo(animalDto.getTipo());
        pet.setTamano(animalDto.getTamano());
        pet.setCuidadosEspeciales(animalDto.getCuidadosEspeciales());
        pet.setEdad(animalDto.getEdad());
        pet.setImagen(animalDto.getImagen());

        // Valores predeterminados
        pet.setUbicacion(animalDto.getUbicacion() != null ? animalDto.getUbicacion() : "Bilbao");
        pet.setGastosDeGestion("500€"); // Valor predeterminado

        petsService.addPets(pet);
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    // Actualizar un animal existente
    @PutMapping("/pets/{id}")
    public ResponseEntity<?> updatePets(@PathVariable("id") int id, @RequestBody AnimalDto animalDto) {
        Optional<Pets> optionalPet = petsService.getPetsById(id);
        if (!optionalPet.isPresent()) {
            return new ResponseEntity<>("Animal no encontrado", HttpStatus.NOT_FOUND);
        }

        Pets pet = optionalPet.get();
        
        // Actualizar todos los campos (mantener el valor actual si no se proporciona)
        pet.setNombre(animalDto.getNombre() != null ? animalDto.getNombre() : pet.getNombre());
        pet.setRaza(animalDto.getRaza() != null ? animalDto.getRaza() : pet.getRaza());
        pet.setTipo(animalDto.getTipo() != null ? animalDto.getTipo() : pet.getTipo());
        pet.setTamano(animalDto.getTamano() != null ? animalDto.getTamano() : pet.getTamano());
        pet.setCuidadosEspeciales(animalDto.getCuidadosEspeciales() != null ? animalDto.getCuidadosEspeciales() : pet.getCuidadosEspeciales());
        pet.setEdad(animalDto.getEdad() > 0 ? animalDto.getEdad() : pet.getEdad());
        pet.setImagen(animalDto.getImagen() != null ? animalDto.getImagen() : pet.getImagen());

        // La ubicación y los gastos de gestión no cambian a menos que se indique explícitamente
        pet.setUbicacion(animalDto.getUbicacion() != null ? animalDto.getUbicacion() : pet.getUbicacion());

        petsService.updatePets(id, pet);
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    // Obtener un animal por ID
    @GetMapping("/pets/{id}")
    public ResponseEntity<?> getPetsById(@PathVariable int id) {
        Optional<Pets> pets = petsService.getPetsById(id);
        if (pets.isPresent()) {
            return ResponseEntity.ok(pets.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal no encontrado");
        }
    }

    // Eliminar un animal
    @DeleteMapping("/pets/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        Optional<Pets> existingPets = petsService.findById(id);
        if (existingPets.isPresent()) {
            petsService.delete(existingPets.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
