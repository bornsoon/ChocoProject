package com.choco.board.service;

import java.util.List;
import java.util.Map;

import com.choco.board.model.Board;

public interface BoardService {
	List<Board> getBoardList();
	List<Board> getBoardList(String boardCategory);
	List<Board> getBoardListByHeart();
	List<Board> getBoardListByHeart(String boardCategory);
	List<Board> getBoardListByHeart(int rank);
	List<Board> getBoardListByUsersId(Map<String, String> params);
	List<Board> getBoardListByUsersHeart(Map<String, String> params);
	Board getBoardInfo(int boardId);
	int createBoardId();
	void createBoard(Board board);
	void updateBoard(Board board);
	void deleteBoard(int boardId);
}