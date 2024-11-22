package com.choco.reply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choco.reply.dao.ReplyRepository;
import com.choco.reply.model.Reply;

@Service
public class BasicReplyService implements ReplyService {
	
	@Autowired
	private ReplyRepository replyRepository;
		//의존성 주입
		public BasicReplyService(ReplyRepository replyRepository) {
			this.replyRepository = replyRepository;
		}

	@Override
	public List<Reply> getReplyList() {
		return replyRepository.getReplyList();
	}

	@Override
	public Reply getReplyInfo(int replyId) {
		return replyRepository.getReplyInfo(replyId);
	}

	@Override
	public void createReply(Reply reply) {
		replyRepository.createReply(reply);
	}

	@Override
	public void updateReply(Reply reply) {
		replyRepository.updateReply(reply);
	}

	@Override
	public void deleteReply(int replyId) {
		replyRepository.deleteReply(replyId);
	}

	@Override
	public List<Reply> getReplysByUsersId(String usersId) {
		return replyRepository.getReplyByUsersId(usersId);
	}

	@Override
	public List<Reply> getReplyByBoardId(int boardId) {
		return replyRepository.getReplyByBoardId(boardId);
	}

}
