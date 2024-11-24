package com.choco.board.service;

import java.util.List;

import com.choco.board.model.Board;

public interface BoardService {
	List<Board> getBoardList();
	List<Board> getBoardList(String boardCategory);
	List<Board> getBoardListByHeart(int rank);
	List<Board> getBoardListByUsersId(String usersId);
	Board getBoardInfo(int boardId);
	int createBoardId();
	void createBoard(Board board);
	void updateBoard(Board board);
	void deleteBoard(int boardId);
}