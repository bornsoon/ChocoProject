package com.choco.reply.service;

import java.util.List;

import com.choco.reply.model.Reply;

public interface ReplyService {
	List<Reply> getReplyList();
	Reply getReplyInfo(int replyId);
	void createReply(Reply reply);
	void updateReply(Reply reply);
	void deleteReply(int replyId);

	
	// userId의 댓글 조회
    List<Reply> getReplysByUsersId(String usersId);
	
	// boardId의 댓글 조회
    List<Reply> getReplyByBoardId(int boardId);

}
