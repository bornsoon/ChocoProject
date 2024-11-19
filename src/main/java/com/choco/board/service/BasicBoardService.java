package com.choco.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choco.board.dao.BoardRepository;
import com.choco.board.model.Board;

@Service
public class BasicBoardService implements BoardService {
	
	@Autowired
	BoardRepository boardRepository;
	
	@Override
	public List<Board> getBoardList() {
		return boardRepository.getBoardList();
	}
	
	@Override
	public Board getBoardDetail(int boardId) {
		return boardRepository.getBoardDetail(boardId);
	}
	
	@Override
	public void createBoard(Board board) {
		boardRepository.createBoard(board);
	}
	
	@Override
	public void updateBoard(Board board) {
		boardRepository.updateBoard(board);
	}
}
