package edu.dufe.student.registry.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.dufe.student.registry.entity.StudentRegistryModel;

@Repository
public interface StudentRegistryDao extends CrudRepository<StudentRegistryModel, Long>{
	public List<StudentRegistryModel> findAll();
	public StudentRegistryModel findByStudentIdentityNum(String studentIdentityNum);
	public Long countByStudentIdentityNumOrStudentStudyNum(String studentIdentityNum, String studentStudyNum);
	public List<StudentRegistryModel> findByAssistUnitIgnoreCase(String assistUnit);
	public StudentRegistryModel save(StudentRegistryModel model);
	@Modifying
	@Transactional 
	public void deleteByStudentIdentityNum(String studentIdentityNum);
	public void deleteAll();
}
