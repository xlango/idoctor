package com.idoctor.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idoctor.pojo.JSONResult;
import com.idoctor.pojo.User;
import com.idoctor.service.impl.UserServiceImpl;


@RestController   //@RestController =@Controller+@ResponseBody
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserServiceImpl userService;
	
	
	String string = "2016-10-24 21:59:06";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
	
	@RequestMapping("/getuser")
	public JSONResult getuser() throws ParseException {	
		User user=new User();
		user.setName(null);
		user.setPassword("123");
		user.setBirthday(string);
		return JSONResult.ok(user);
	}
	
	@RequestMapping("/all")
	public JSONResult getAllUser() {
/*		for(User user:userService.findAll()) {
			System.out.println(user.getName());
		}*/
		
		return JSONResult.ok(userService.findAll());
	}
	
	@RequestMapping("/pageall")
	public JSONResult getPageAllUser(Integer page, Integer pageSize) {		
		return JSONResult.ok(userService.pageuser(page, pageSize));
	}
	
	@RequestMapping("/add")
	public JSONResult add() {
		User user=new User();
		user.setName("222");
		user.setPassword("222");
		user.setAge(22);
		user.setBirthday("2018-01-01 00:00:00");
		user.setDescation("222");
		
		userService.add(user);
		return JSONResult.ok();
	}
	
	@RequestMapping("/update")
	public JSONResult update(){
		User user=new User();
		user.setId(2);
		user.setName("2223");
		user.setPassword("2223");
		user.setAge(22);
		user.setBirthday("2018-02-02 00:00:00");
		user.setDescation("2223");
		
		userService.update(user);
		return JSONResult.ok();
	}
	
	@RequestMapping("/findbyName")
	public JSONResult findbyName(){
		User user=new User();
		user.setName("2223");
		
		System.out.println(userService.findbyName(user).getBirthday());
		return JSONResult.ok();
	}
	
	@RequestMapping("/delete")
	public JSONResult delete(){
		User user=new User();
		user.setId(2);

		userService.delete(user);
		return JSONResult.ok();
	}
	
	
	
}
