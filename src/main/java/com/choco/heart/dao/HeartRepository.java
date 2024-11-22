package com.choco.heart.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.choco.heart.model.Heart;

@Mapper
public interface HeartRepository {
   
    int checkHeart(@Param("boardId") int boardId, // 특정 게시물 또는 댓글에 좋아요 데이터 존재 여부 확인
				   @Param("sessionId") String sessionId); 
    int getHeartCount(@Param("heartId") int heartId); // 특정 게시물/댓글 좋아요 개수 반환
    void insertHeart(Heart heart); // 좋아요 추가
    void deleteHeart(@Param("heartId") int heartId, @Param("usersId") String usersId); // 좋아요 삭제
}