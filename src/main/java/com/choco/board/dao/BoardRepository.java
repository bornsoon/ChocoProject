package com.choco.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.choco.board.model.Board;

@Mapper
public interface BoardRepository {
	List<Board> getBoardList();
	List<Board> getBoardList(@Param("boardCategory") String boardCategory);
	List<Board> getBoardListByHeart();
	List<Board> getBoardListByHeart(Map<String, Object> params);
	List<Board> getBoardListByUsersId(Map<String, String> params);
	List<Board> getBoardListByUsersHeart(Map<String, String> params);
	Board getBoardInfo(int boardId);
	int createBoardId();
	void createBoard(Board board);
	void updateBoard(Board board);
	void deleteBoard(int boardId);
}
