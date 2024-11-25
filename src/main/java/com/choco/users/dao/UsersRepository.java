package com.choco.users.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.choco.users.model.Users;


@Mapper
public interface UsersRepository{
	
	int getUsersCount();
	String getUsersName(String usersId);
	
	Users loginUsers(Users users);
	
	String findId(@Param("usersName") String usersName,
			@Param("usersNickname") String usersNickname, @Param("usersBirthdate") String usersBirthdate);
	
	String findPwd(@Param("usersId") String usersId,
			@Param("usersName") String usersName, @Param("usersBirthdate") String usersBirthdate);
	
	void insertUsers(Users users);
	
	List<Users> getUsersList();
	List<String> getIdList();
	
	
}
