package app.labs.ex03.template.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
	String idx;
	String userId;
	String userPwd;
	String userName;
	String userRole;
	String userStatus;
}
