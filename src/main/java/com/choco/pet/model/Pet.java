package com.choco.pet.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(exclude= {"petProfile"})
public class Pet {

	private int petId;
	private String petName;
	private String petBreed;
	private String petWeight;
	private String petBirthdate;
	private MultipartFile petProfile;
	private byte[] petProfileBytes;
	
	private String usersId;
	
}