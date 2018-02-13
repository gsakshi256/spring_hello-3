package controller;

import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Info;
import service.RestService;

@RestController
public class Controller {
	@Autowired
	RestService rs;
  
	
	@RequestMapping(value="/all")
	public Hashtable<String, Info> getAll() {
		return rs.getAll();
	}

	@RequestMapping(value="/id/{id}")
	public Info getinfo(@PathVariable(value = "id") String id) {
		System.out.println(id);
		return rs.getInfo(id);
	}

	@RequestMapping(value="/info")
	public String getinfo2(@RequestParam(value = "id") String id) {
		System.out.println(id);
		return "sskk";
	}

	@RequestMapping(value="/id")
	public Info getinfo1(@RequestParam(value = "id") String id) {
		System.out.println(id);
		return rs.getInfo(id);
	}

}
