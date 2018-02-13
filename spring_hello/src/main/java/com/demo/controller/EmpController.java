package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Employee;
import com.demo.service.Empdao;

@RestController
@RequestMapping(value = "/emp")
public class EmpController {

	@Autowired
	Empdao empDao;
	

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Employee create(@RequestBody Employee emp) {
		emp = empDao.save(emp);
		return emp;

	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE) 
    public void delete(@PathVariable Integer id) {
        empDao.delete(id); 
	}
	@RequestMapping(value = "/read/{id}") 
    public Employee read(@PathVariable Integer id) {
        return empDao.findOne(id);
    }
	
	@RequestMapping(value = "/update",method = RequestMethod.PUT)
    public void update(@RequestBody Employee emp) {
		empDao.save(emp);
    }
	//@PreAuthorize("hasRole(ROLE_USER)")
	@RequestMapping(value = "/getall")
	public @ResponseBody Iterable<Employee>  getAllUsers() {
		return empDao.findAll();
	}
	
	
}
	
