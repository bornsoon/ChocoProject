package com.choco.reply.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
	private int replyId;
	private String replyContent;
	private String replyDate;
	private String usersId;
	private int boardId;
	private int heartSum;
 // private String nickname;
}
