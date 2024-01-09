package edu.dufe.student.registry.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.dufe.student.registry.entity.UserModel;

@Repository
public interface UserDao extends CrudRepository<UserModel, Long>{
	public List<UserModel> findAll();
	public UserModel findByUserName(String userName);
	public UserModel findByUserNameAndPassword(String userName, String password);
}
