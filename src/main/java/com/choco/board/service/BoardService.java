package com.choco.board.service;

import java.util.List;

import com.choco.board.model.Board;

public interface BoardService {
	List<Board> getBoardList();
	Board getBoardDetail(int boardId);
	void createBoard(Board board);
	void updateBoard(Board board);
}
