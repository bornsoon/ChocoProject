package com.choco.attach.service;

import java.util.List;

import com.choco.attach.model.Attach;

public interface AttachService {
	Attach getOneAttachInfo(int boardId);
	List<Attach> getAttachList(int boardId);
	void insertAttach(int boardId);
	void deleteAttach(int boardId);
}
