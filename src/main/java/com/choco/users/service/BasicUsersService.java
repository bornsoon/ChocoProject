package com.choco.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choco.users.dao.UsersRepository;
import com.choco.users.model.Users;

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
	
	@Override
	public String findId(String usersName, String usersNickname, String usersBirthdate) {
		return usersRepository.findId(usersName, usersNickname, usersBirthdate);
	}
	
	@Override
	public String findPwd(String usersId, String usersName, String usersBirthdate) {
		return usersRepository.findPwd(usersId, usersName, usersBirthdate);
	}

	
	@Override
	public boolean checkId(String usersId) {
        return usersRepository.checkId(usersId); // 데이터베이스에 아이디 존재 여부 확인
    }
	

	
	
	@Override
	public Users loginUsers(String usersId, String usersPwd) {
		Users users = new Users();
		users.setUsersId(usersId);
		users.setUsersPwd(usersPwd);
		
		return usersRepository.loginUsers(users);
	}
	
	@Override
	public void insertUsers(Users users) {
		usersRepository.insertUsers(users);
	}
	
}
