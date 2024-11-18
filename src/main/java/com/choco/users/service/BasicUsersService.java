package com.choco.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choco.users.dao.UsersRepository;

@Service
public class BasicUsersService implements UsersService {
	
	@Autowired
	UsersRepository usersRepository;
	
	@Override
	public int getUsersCount() {
		return usersRepository.getUsersCount();
	}
	
	@Override
	public String getUsersName(String usersId) {
		return usersRepository.getUsersName(usersId);
	}
}
