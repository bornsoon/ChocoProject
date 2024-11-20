package com.choco.attach.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(exclude= {"attachFile"}) //ToString에서 attachFile만 빼겠다.
@AllArgsConstructor
@NoArgsConstructor
public class Attach {
	private int attachId;
	private String attachName;
	private byte[] attachFile;
	private int boardId;
}