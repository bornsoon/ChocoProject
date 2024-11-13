package app.labs.ex05.mybatis.service;

import java.util.List;
import java.util.Map;

import app.labs.ex05.mybatis.model.Emp;
// GenericEmpService라고 명명하기도 함
public interface EmpService {
	int getEmpCount();
	int getEmpCount(int deptid);
	List<Emp> getEmpList();
	Emp getEmpInfo(int empid);
	void updateEmp(Emp emp);
	void insertEmp(Emp emp);
	int deleteEmp(int empid, String email);
	List<Map<String, Object>> getAllDeptId();
	List<Map<String, Object>> getAllJobId();
	List<Map<String, Object>> getAllManagerId();
	Emp loginEmp(String firstName, String lastName);
}