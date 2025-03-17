package com.choco.heart.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Heart {
	private int heartId;    // boardId + replyId (PK)
	private String usersId;  
}