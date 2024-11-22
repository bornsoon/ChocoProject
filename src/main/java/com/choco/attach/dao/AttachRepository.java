package com.choco.attach.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.choco.attach.model.Attach;

@Mapper
public interface AttachRepository {
	List<Attach> getAttachList(int boardId);
	void insertAttach(Attach attah);
	void deleteAttach(int boardId);
}