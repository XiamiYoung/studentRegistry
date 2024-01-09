package edu.dufe.student.registry.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.dufe.student.registry.entity.SiteManageModel;

@Repository
public interface SiteManageDao extends CrudRepository<SiteManageModel, Long>{
	public List<SiteManageModel> findAll();
	public SiteManageModel save(SiteManageModel model);
	public SiteManageModel findByPk(Long id);
	public void deleteAll();
}
