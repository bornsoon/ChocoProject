package com.choco.board.model;

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
	private String boardCategory;
	private LocalDateTime boardDate;
	private String usersId;
}
