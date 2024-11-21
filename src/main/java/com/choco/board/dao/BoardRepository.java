package com.choco.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.choco.board.model.Board;

@Mapper
public interface BoardRepository {
	List<Board> getBoardList();
	List<Board> getBoardList(int usersId);
	List<Board> getBoardList(String boardCategory);
	Board getBoardInfo(int boardId);
	void createBoard(Board board);
	void updateBoard(Board board);
	void deleteBoard(int boardId);
}
