package com.choco.board.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
	private Timestamp boardDate;
	private String usersId;
	private String boardCategory;
}