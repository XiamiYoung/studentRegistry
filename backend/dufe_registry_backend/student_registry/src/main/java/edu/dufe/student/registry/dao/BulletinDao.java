package edu.dufe.student.registry.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.dufe.student.registry.entity.BulletinModel;

@Repository
public interface BulletinDao extends CrudRepository<BulletinModel, Long>{
	public List<BulletinModel> findAll();
	public BulletinModel save(BulletinModel model);
	public BulletinModel findByPk(Long id);
	public void deleteAll();
}
