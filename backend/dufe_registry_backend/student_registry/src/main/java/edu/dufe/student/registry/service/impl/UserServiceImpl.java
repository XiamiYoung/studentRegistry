package edu.dufe.student.registry.service.impl;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.dufe.student.registry.converter.UserConverter;
import edu.dufe.student.registry.dao.UserDao;
import edu.dufe.student.registry.data.in.UserData;
import edu.dufe.student.registry.entity.UserModel;
import edu.dufe.student.registry.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	@Resource
	private UserConverter userConverter;

	public UserDetails findByUserNameAndPassword(String username, String password) throws UsernameNotFoundException {
		UserModel userModel = userDao.findByUserNameAndPassword(username, password);
		UserData userData = userConverter.modelToData(userModel);
		return userData;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel userModel = userDao.findByUserName(username);
		UserData userData = userConverter.modelToData(userModel);
		return userData;
	}

}
