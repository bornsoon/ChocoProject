package com.choco.attach.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(exclude= {"attachFile"}) 
@AllArgsConstructor
@NoArgsConstructor
public class Attach {
	private int attachId;
	private String attachName;
	private int postId;
	private byte[] attachFile;
}
