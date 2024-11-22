package com.choco.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	private int boardId;
	private String boardTitle;
	private String boardContent;
	private String boardDate;
	private String usersId;
	private String boardCategory;
	private int HeartSum; // 도메인에는 존재하지 않지만 컬럼이지만 DTO로 추가
}