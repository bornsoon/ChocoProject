package com.choco.users.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersRepository {
	int getUsersCount();
	String getUsersName(String usersId);
}
