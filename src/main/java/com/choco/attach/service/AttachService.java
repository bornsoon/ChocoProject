package com.choco.attach.service;

//import java.util.List;

import com.choco.attach.model.Attach;

public interface AttachService {
	// List<Attach> getAttachList(int boardId);
	Attach getAttachFile(int boardId);
	void insertAttach(Attach attah);
	void updateAttach(Attach attach);
	void deleteAttach(int boardId);
}
