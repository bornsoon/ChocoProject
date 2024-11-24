package com.choco.users.service;

import com.choco.users.model.Users;

public interface UsersService {
	int getUsersCount();
	String getUsersName(String usersId);
	
	void insertUsers(Users users);
	
	public boolean checkId(String usersId);
	
	Users loginUsers(String usersId, String usersPwd);
	String findId(String usersName, String usersNickname, String usersBirthdate);
	String findPwd(String usersId, String usersName, String usersBirthdate);
	
	
}