package com.choco.heart.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choco.heart.dao.HeartRepository;
import com.choco.heart.model.Heart;

@Service
public class BasicHeartService implements HeartService {

    @Autowired
    HeartRepository heartRepository;

    @Override
    public int checkHeart(int boardId, String sessionId) {
			  return heartRepository.checkHeart(boardId, sessionId);
		}
        
    @Override
    public int getHeartCount(int heartId) {
		    return heartRepository.getHeartCount(heartId);
    }
    
    @Override
    public void insertHeart(Heart heart) {
		    heartRepository.insertHeart(heart);
    }
    
    @Override
    public void deleteHeart(int heartId, String usersId) {
		    heartRepository.deleteHeart(heartId, usersId);
    }

}
