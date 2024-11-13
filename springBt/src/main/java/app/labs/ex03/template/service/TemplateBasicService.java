package app.labs.ex03.template.service;

import java.util.List;
import java.util.Map;

import app.labs.ex03.template.user.UserAccount;

public interface TemplateBasicService {
	List<String> getList();
	Map<String, String> getMap();
	UserAccount getUserAccount();
}
