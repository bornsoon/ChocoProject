package com.choco.attach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choco.attach.dao.AttachRepository;
import com.choco.attach.model.Attach;

@Service
public class BasicAttachService implements AttachService {

	@Autowired
	AttachRepository attachRepository;

	/*
	@Override
	public List<Attach> getAttachList(int boardId) {
		return attachRepository.getAttachList(boardId);
	}*/
	
	@Override
	public Attach getAttachFile(int boardId) {
		return attachRepository.getAttachFile(boardId);
	}

	@Override
	public void insertAttach(Attach attach) {
		attachRepository.insertAttach(attach);
	}

	@Override
	public void updateAttach(Attach attach) {
		attachRepository.updateAttach(attach);
	}

	@Override
	public void deleteAttach(int boardId) {
		attachRepository.deleteAttach(boardId);
	}

}
