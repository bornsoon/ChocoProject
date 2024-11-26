package com.choco.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choco.users.dao.UsersRepository;
import com.choco.pet.dao.PetRepository;
import com.choco.pet.model.Pet;
import com.choco.users.model.Users;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	public String IdCheck(String usersId) {
        
		String result = usersRepository.IdCheck(usersId);
		
		System.out.println("result: " + result);
		
		if (result == null) {
			result ="사용 가능한 아이디입니다.";
		}
		else {result="이미 사용 중인 아이디입니다.";}
		
		System.out.println(result);
		
		return result; // 데이터베이스에 아이디 존재 여부 확인
    }
	
	@Override
	public String NicknameCheck(String usersNickname) {
        
		String result = usersRepository.NicknameCheck(usersNickname);
		
		System.out.println("result: " + result);
		
		if (result == null) {
			result ="사용 가능한 닉네임입니다.";
		}
		else {result="이미 사용 중인 닉네임입니다.";}
		
		System.out.println(result);
		
		return result; // 데이터베이스에 아이디 존재 여부 확인
    }
	
	@Override
	public String EmailCheck(String usersEmail) {
        
		String result = usersRepository.EmailCheck(usersEmail);
		
		System.out.println("result: " + result);
		
		if (result == null) {
			result ="사용 가능한 이메일입니다.";
		}
		else {result="이미 사용 중인 이메일입니다.";}
		
		System.out.println(result);
		
		return result; // 데이터베이스에 아이디 존재 여부 확인
    }
	
	@Override
	@Transactional
	public void insertUsersAndPet(Users users, Pet pet) {
		usersRepository.insertUsers(users);
		System.out.println("insertUsers 통과함");
		petRepository.insertPet(pet);
	}
	
	@Override
	@Transactional
	public void reviseUsersAndPet(Users users, Pet pet) {
		usersRepository.reviseUsers(users);
		petRepository.revisePet(pet);
	}
	
	@Override
	@Transactional
	public void deleteUsersAndPet(String usersId) {
		petRepository.deletePet(usersId);
		usersRepository.deleteUsers(usersId);
	}
	
	
	@Override
	public List<String> getAllUsersIds() {
        return usersRepository.getIdList(); // USERS_ID 리스트 가져오기
    }
	
	@Override
	public String getUsersId(String usersId) {
		System.out.println("getUsersId 실행");
		return usersRepository.getUsersId(usersId);
	}
	
	@Override
	public Users getUsersInfo(String usersId) {
		System.out.println("getUsersInfo 실행");
		return usersRepository.getUsersInfo(usersId);
	}
	
	
	@Override
	public Users loginUsers(String usersId, String usersPwd) {
		Users users = new Users();
		users.setUsersId(usersId);
		users.setUsersPwd(usersPwd);
		
		return usersRepository.loginUsers(users);
	}
	

	
}