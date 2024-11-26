package com.choco.pet.Service;

import com.choco.pet.model.Pet;

public interface PetService {

	int createPetId();
	Pet getPetInfo(String usersId);
}
