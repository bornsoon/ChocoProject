package com.choco.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choco.users.dao.UsersRepository;
import com.choco.pet.dao.PetRepository;
import com.choco.pet.model.Pet;
import com.choco.users.model.Users;

@Service
public class BasicUsersService implements UsersService {
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	PetRepository petRepository;
	
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
	@Transactional
	public void insertUsersAndPet(Users users, Pet pet) {
		usersRepository.insertUsers(users);
		petRepository.insertPet(pet);
	}
	
	@Override
	public List<String> getIdList() {
		List<String> idList = usersRepository.getIdList();
		
		return idList;
	}
	
	@Override
	public boolean getIdCheck(String inputId) {
		boolean idCheck = usersRepository.getIdCheck(inputId);
		
		return idCheck;
	}
	
	
	@Override
	public Users loginUsers(String usersId, String usersPwd) {
		Users users = new Users();
		users.setUsersId(usersId);
		users.setUsersPwd(usersPwd);
		
		return usersRepository.loginUsers(users);
	}
	

	
}