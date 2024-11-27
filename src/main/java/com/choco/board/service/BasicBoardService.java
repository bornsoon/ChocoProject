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
	public List<Board> getBoardListByHeart() {
		return boardRepository.getBoardListByHeart();
	}
	
	@Override
	public List<Board> getBoardListByHeart(int rank) {
		return boardRepository.getBoardListByHeart(rank);
	}
	
	@Override
	public List<Board> getBoardListByUsersId(String usersId) {
		return boardRepository.getBoardListByUsersId(usersId);
	}
	
	@Override
	public List<Board> getBoardListByUsersHeart(String usersId) {
		return boardRepository.getBoardListByUsersHeart(usersId);
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
	public int createBoardId() {
		return boardRepository.createBoardId();
	}
	
	@Override
	public void createBoard(Board board) {
		boardRepository.createBoard(board);
	}
	
	@Override
	public void updateBoard(Board board) {
		boardRepository.updateBoard(board);
	}
	
	@Override
	public void deleteBoard(int boardId) {
		boardRepository.deleteBoard(boardId);
	}
}
