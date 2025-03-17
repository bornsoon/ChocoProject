package com.choco.heart.service;

import java.util.Map;

import com.choco.heart.model.Heart;

public interface HeartService {
    int checkHeart(int heartId, String sessionId); // 특정 게시물 또는 댓글에 좋아요 데이터 존재 여부 확인
    int getHeartCount(int heartId); // 게시물,댓글 좋아요 개수 반환
    void insertHeart(Heart heart); // 좋아요 추가
    void deleteHeart(Map<String, Object> heartMap); // 좋아요 삭제
}