package app.labs.ex05.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import app.labs.ex05.mybatis.model.Emp;

@Mapper
public interface EmpRepository {
	int getEmpCount();
	int getEmpCount(@Param("deptid") int deptid);
	List<Emp> getEmpList();
	Emp getEmpInfo(int empid);
	void updateEmp(Emp emp);
	void insertEmp(Emp emp);
	void deleteJobHistory(int empid);
	int deleteEmp(@Param("empid") int empid, @Param("email") String email);
	List<Map<String, Object>> getAllDeptId();
	List<Map<String, Object>> getAllJobId();
	List<Map<String, Object>> getAllManagerId();
	Emp loginEmp(Emp emp);
}
