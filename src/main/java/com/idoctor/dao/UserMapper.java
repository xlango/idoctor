package com.idoctor.dao;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.idoctor.pojo.User;

@Mapper
@Component
public interface UserMapper {

	List<User> findAll();
	
	List<User> findByIf(User user);
	
	void add(User user);
	
	void update(User user);
	
	void delete(User user);
	
	void addList(List<User> userList);
	
	void deleteList(int[] ids);
	
	void updateList(List<User> userList);
}
