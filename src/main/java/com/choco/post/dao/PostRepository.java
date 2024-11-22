package com.choco.post.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostRepository {
	int getUsersCount();
	String getUsersName(String usersId);
}
