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
	@Transactional
	public void insertUsersAndPet(Users users, Pet pet) {
		usersRepository.insertUsers(users);
		petRepository.insertPet(pet);
	}
	
	@Override
	public String IdCheck(String usersId) {
		String result = usersRepository.IdCheck(usersId);
		
		System.out.println("result: " + result);
		
		if(result==null) result = "사용 가능한 아이디입니다.";
		else result = "이미 사용 중인 아이디입니다.";
		
		return result;
	}
	
	@Override
	public List<String> getAllUsersIds() {
        return usersRepository.getIdList(); // USERS_ID 리스트 가져오기
    }
	
	
	@Override
	public Users loginUsers(String usersId, String usersPwd) {
		Users users = new Users();
		users.setUsersId(usersId);
		users.setUsersPwd(usersPwd);
		
		return usersRepository.loginUsers(users);
	}
	

	
}
