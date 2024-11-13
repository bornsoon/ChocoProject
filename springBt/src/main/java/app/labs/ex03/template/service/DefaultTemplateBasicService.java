package app.labs.ex03.template.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import app.labs.ex03.template.user.UserAccount;

@Service
public class DefaultTemplateBasicService implements TemplateBasicService {
	List<String> list;
	Map<String, String> map;
	UserAccount userAccount;
	
	// java 9 >=
	public DefaultTemplateBasicService() {
		list = List.of("A", "B", "C", "D", "E");
		map = Map.of(
				"A", "가",
				"B", "나",
				"C", "다",
				"D", "라",
				"E", "마"
		);
		userAccount = new UserAccount("1", "user", "1234", "사용자", "ROLE_USER", "1");
	}
	
	@Override
	public List<String> getList() {
		return list;
	}

	@Override
	public Map<String, String> getMap() {
		return map;
	}

	@Override
	public UserAccount getUserAccount() {
		return userAccount;
	}

}
