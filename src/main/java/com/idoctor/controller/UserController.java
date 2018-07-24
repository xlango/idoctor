package com.idoctor.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idoctor.pojo.JSONResult;
import com.idoctor.pojo.User;
import com.idoctor.service.impl.UserServiceImpl;
import com.idoctor.utils.JsonUtils;
import com.idoctor.utils.RedisTemplateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController   //@RestController =@Controller+@ResponseBody
@RequestMapping("/user")
@Api(value="用户接口",tags= {"用户API"})
public class UserController {
	
	@Resource
	private UserServiceImpl userService;
	
    @Resource
    private RedisTemplateUtil redisTemplateUtil;

	
	String string = "2016-10-24 21:59:06";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
	
	@RequestMapping("/getuser")
	@ApiOperation(value = "获取一个用户json", httpMethod = "GET", notes = "获取一个用户json")
	public JSONResult getuser() throws ParseException {	
		User user=new User();
		user.setName(null);
		user.setPassword("123");
		user.setBirthday(string);
		return JSONResult.ok(user);
	}
	
	@RequestMapping("/all")
	@ApiOperation(value = "获取所有用户", httpMethod = "GET", notes = "获取所有用户")
	public JSONResult getAllUser() {
/*		for(User user:userService.findAll()) {
			System.out.println(user.getName());
		}*/
		
		return JSONResult.ok(userService.findAll());
	}
	
	@RequestMapping("/pageall")
	@ApiOperation(value = "分页获取用户", httpMethod = "GET", notes = "分页获取用户")
	public JSONResult getPageAllUser(Integer page, Integer pageSize) {		
		return JSONResult.ok(userService.pageuser(page, pageSize));
	}
	
	@RequestMapping("/add")
	@ApiOperation(value = "添加用户", httpMethod = "POST", notes = "添加用户")
	public JSONResult add(User user) {
/*		User user=new User();
		user.setName("222");
		user.setPassword("222");
		user.setAge(22);
		user.setBirthday("2018-01-01 00:00:00");
		user.setDescation("222");*/
		
		userService.add(user);
		return JSONResult.ok();
	}
	
	@RequestMapping("/update")
	@ApiOperation(value = "更新用户", httpMethod = "POST", notes = "更新用户")
	public JSONResult update(User user){
		/*User user=new User();
		user.setId(2);
		user.setName("2223");
		user.setPassword("2223");
		user.setAge(22);
		user.setBirthday("2018-02-02 00:00:00");
		user.setDescation("2223");*/
		
		userService.update(user);
		return JSONResult.ok();
	}
	
	@RequestMapping("/findByIf")
	@ApiOperation(value = "通过条件获取用户", httpMethod = "POST", notes = "通过条件获取用户")
	public JSONResult findByIf(User user){
		/*User user=new User();
		user.setName(name);
		
		System.out.println(userService.findbyName(user).getBirthday());*/
		return JSONResult.ok(userService.findByIf(user));
	}
	
	@RequestMapping("/delete")
	@ApiOperation(value = "删除用户", httpMethod = "POST", notes = "删除用户")
	public JSONResult delete(int id){
		User user=new User();
		user.setId(id);

		userService.delete(user);
		return JSONResult.ok();
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/redis")
	@ApiOperation(value = "用户缓存", httpMethod = "GET", notes = "用户缓存")
	public JSONResult redis() {		 
		List<User> userList = (List<User>) redisTemplateUtil.getList("userlist");
		System.out.println(userList);
        if(userList==null) {
            System.out.println("还没有缓存，将从数据库中查询。。。");
            userList = userService.findAll();
            redisTemplateUtil.setList("userlist", userList);
        }else {
            System.out.println("已经有缓存了。。。");
        }
        return JSONResult.ok(userList);
	}
	
}
