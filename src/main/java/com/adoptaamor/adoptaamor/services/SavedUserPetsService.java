package com.adoptaamor.adoptaamor.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adoptaamor.adoptaamor.models.SavedUserPets;
import com.adoptaamor.adoptaamor.repositories.SavedUserPetsRepository;

@Service
public class SavedUserPetsService {

    @Autowired
    private final SavedUserPetsRepository savedUserPetsRepository;

    public SavedUserPetsService(SavedUserPetsRepository savedUserPetsRepository) {
        this.savedUserPetsRepository = savedUserPetsRepository;
    }

    public SavedUserPets updateSavedUserPets(Long id, SavedUserPets newData) {
        Optional<SavedUserPets> savedPet = savedUserPetsRepository.findById(id);

        if (savedPet.isPresent()) {
            SavedUserPets pet = savedPet.get();

            if (pet.getReservedDate() != null) {
                throw new IllegalStateException(
                        "No se puede modificar la fecha de reserva después de haber sido asignado.");
            }

            pet.setSavedDate(newData.getSavedDate());

            return savedUserPetsRepository.save(pet);
        } else {
            throw new IllegalArgumentException("Animal no encontrado en el perfil del usuario.");
        }
    }

    public void deleteSavedUserPets(Long id) {
        Optional<SavedUserPets> savedPet = savedUserPetsRepository.findById(id);

        if (savedPet.isPresent()) {
            if (savedPet.get().getReservedDate() != null) {
                throw new IllegalStateException("No se puede eliminar un animal que ya ha sido reservado.");
            }

            savedUserPetsRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Animal no encontrado en el perfil del usuario.");
        }
    }

}
