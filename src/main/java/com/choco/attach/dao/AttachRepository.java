package com.choco.attach.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.choco.attach.model.Attach;

@Mapper
public interface AttachRepository {
	Attach getOneAttachInfo(int boardId);
	List<Attach> getAttachList(int boardId);
	void insertAttach(int boardId);
	void deleteAttach(int boardId);
}