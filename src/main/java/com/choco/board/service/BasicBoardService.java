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
	public List<Board> getBoardList(String boardCategory) {
		return boardRepository.getBoardList(boardCategory);
	}
	
	@Override
	public Board getBoardInfo(int boardId) {
		return boardRepository.getBoardInfo(boardId);
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
