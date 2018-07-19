package com.idoctor.service;

import java.util.List;

import com.idoctor.pojo.User;

public interface IUserService {

	List<User> findAll();
	
	List<User> pageuser(Integer page,Integer pageSize);
	
    User findbyName(User user);
	
    void add(User user);
	
    void update(User user);
	
    void delete(User user);
}
