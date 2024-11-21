package com.choco.reply.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.choco.reply.model.Reply;

@Mapper
public interface ReplyRepository {
	List<Reply> getReplyList();
	Reply getReplyInfo(int replyId);
	void createReply(Reply reply);
	void updateReply(Reply reply);
	void deleteReply(int replyId);
	
	// userId의 댓글 조회
	List<Reply> getReplyByUserId(@Param("usersId") String usersId);
	
	// boardId의 댓글 조회
	List<Reply> getReplyByBoardId(@Param("boardId") int boardId);
}

