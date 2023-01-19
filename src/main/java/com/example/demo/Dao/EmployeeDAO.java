package com.example.demo.Dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.demo.Model.Employee;

@Repository
public class EmployeeDAO {
	private static final Map<String, Employee> empMap = new HashMap<String, Employee>();

	static {
		System.out.println("Inside Static Block");
		initEmps();
	}

	private static void initEmps() {
		Employee e = new Employee();
		Map<String, Employee> empMap = new HashMap<String, Employee>();
		empMap.put(e.getEmpNo(), new Employee("E01", "Smith", "Clerk"));
		empMap.put(e.getEmpNo(), new Employee("E02", "Allen", "Salesman"));
		empMap.put(e.getEmpNo(), new Employee("E03", "Jones", "Manager"));
	}

	public Employee getEmployee(String eNo) {
		Employee employee = empMap.get(eNo);
		return employee;
	}

	public List<Employee> getAllEmployees() {
		List<Employee> e = new ArrayList<Employee>();
		e.addAll(empMap.values());
		return e;
	}

	public Employee addEmployee(Employee emp) {
		empMap.put(emp.getEmpNo(), emp);
		return emp;
	}

	public Employee updateEmployee(Employee emp) {
		empMap.put(emp.getEmpNo(), emp);
		return emp;
	}

	public void deleteEmployee(String empNo) {
		empMap.remove(empNo);
	}

	public boolean existsById(String id) {
		if (this.getEmployee(id) != null)
			return true;
		else
			return false;
	}

}
