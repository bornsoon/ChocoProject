package com.choco.users.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(exclude= {"usersProfile"})
@AllArgsConstructor
@NoArgsConstructor
public class Users {
	
	private String usersId;
	private String usersName;
	private String usersPwd;
	private String usersEmail;
	private String usersNickname;
	private String usersBirthdate;
	private MultipartFile usersProfile;
	private byte[] usersProfileBytes;
	
	public String getUsersId() {
		return usersId;
	}
	
	public String getUsersName() {
		return usersName;
	}
	
}