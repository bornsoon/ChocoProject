package com.choco.pet.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(exclude= {"petProfile"})
public class Pet {

	private String petId;
	private String petName;
	private String petBreed;
	private String petWeight;
	private String petBirthdate;
	private byte[] petProfile;
	
}
