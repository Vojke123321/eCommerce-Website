package com.vojke.bookstore.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.vojke.bookstore.entity.Users;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {

	public UserDAO() {
		// TODO Auto-generated constructor stub
	}

	public boolean checkLogin(String email,String password) {
		Map<String,Object> parameters=new HashMap<>();
		parameters.put("email", email);
		parameters.put("pass", password);
		
		List<Users> listUsers = super.findWithNamedQueryAndParamater("Users.checkLogin", parameters);
		if(listUsers.size()==1) {
			return true;
		}
		
		
		return false;
	}

	@Override
	public Users create(Users user) {
		// TODO Auto-generated method stub
		return super.create(user);
	}

	@Override
	public Users update(Users user) {
		// TODO Auto-generated method stub
		return super.update(user);
	}

	@Override
	public Users get(Object user_id) {
		// TODO Auto-generated method stub
		return super.find(Users.class, user_id);
	}

	@Override
	public void delete(Object userid) {
		super.delete(Users.class, userid);
	}

	@Override
	public List<Users> listAll() {
		return super.findWithNamedQuery("Users.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Users.countAll");
	}

	public Users findbyEmail(String email) {
		List<Users> listUsers = super.findWithNamedQueryAndParamater("Users.findByEmail", "email", email);
		if (listUsers != null && listUsers.size() > 0) {
			return listUsers.get(0);
		}
		return null;
	}

}
