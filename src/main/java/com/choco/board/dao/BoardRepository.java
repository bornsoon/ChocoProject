package com.choco.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.choco.board.model.Board;

@Mapper
public interface BoardRepository {
	List<Board> getBoardList();
	List<Board> getBoardList(@Param("boardCategory") String boardCategory);
	List<Board> getBoardListByHeart();
	List<Board> getBoardListByHeart(int rank);
	List<Board> getBoardListByUsersId(@Param("usersId") String usersId);
	List<Board> getBoardListByUsersHeart(@Param("usersId") String usersId);
	Board getBoardInfo(int boardId);
	int createBoardId();
	void createBoard(Board board);
	void updateBoard(Board board);
	void deleteBoard(int boardId);
}
