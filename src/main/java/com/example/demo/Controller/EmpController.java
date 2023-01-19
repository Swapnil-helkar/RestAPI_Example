package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.EmployeeDAO;
import com.example.demo.Model.Employee;

@RestController
public class EmpController {
	@Autowired
	EmployeeDAO dao;

	@RequestMapping("/")
	@ResponseBody
	String Welcome() {
		return "Welcome to REST API";

	}

//GET ALL EMPLOYEES
	@RequestMapping(value = "/employees", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE ,MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public List<Employee> getEmployees() {
		List<Employee> list = dao.getAllEmployees();
		return list; // covert ArryList to json/xml
	}

//GET EMP
	@RequestMapping(value = "/employee/{empNo}", method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Employee getEmployee(@PathVariable("empNo") String empNo) {
		return dao.getEmployee(empNo);
	}

//POST
	// http://localhost:9080/employee POST
	// In response we will get status code as 200 ok which is the default
	@RequestMapping(value = "/employee", //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.ALL_VALUE })
	@ResponseBody
	public Employee addEmployee(@RequestBody Employee emp) {
		System.out.println("(Service Side) Creating employee: " + emp.getEmpNo());
		return dao.addEmployee(emp);
	}

//POST WITH RESPONSE ENTITY
	// URL -> http://localhost:9080/addemployee POST
	// In response we will get status code as 201 Created
	@RequestMapping(method = RequestMethod.POST, value = "/addemployee")
	public ResponseEntity<Employee> addEmployeeWithResponseEntity(@RequestBody Employee employee) {
		dao.addEmployee(employee);
		System.out.println(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}

	// DELETE
//	http://localhost:9095/employee/E02
	@RequestMapping(value = "/employee/{empNo}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public void deleteEmployee(@PathVariable("empNo") String empNo) {
		System.out.println("(Service Side) Deleting employee: " + empNo);
		dao.deleteEmployee(empNo);
	}

//UPDATE
	@RequestMapping(value = "/employee", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Employee updateEmployee(@RequestBody Employee emp) {

		System.out.println("(Service Side) updating employee: " + emp.getEmpNo());

		return dao.updateEmployee(emp);
	}

	/*
	 * GET -> http://localhost:8080/SomeContextPath/employees GET ->
	 * http://localhost:8080/SomeContextPath/employees/{id} POST ->
	 * http://localhost:8080/SomeContextPath/employees DELETE ->
	 * http://localhost:8080/SomeContextPath/employees - all emp DELETE ->
	 * http://localhost:8080/SomeContextPath/employees/{id} PUT ->
	 * http://localhost:8080/SomeContextPath/updateemp
	 */

}
