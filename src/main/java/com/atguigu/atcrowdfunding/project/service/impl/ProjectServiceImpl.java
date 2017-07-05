package com.atguigu.atcrowdfunding.project.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.common.bean.Datas;
import com.atguigu.atcrowdfunding.project.bean.Project;
import com.atguigu.atcrowdfunding.project.dao.ProjectDao;
import com.atguigu.atcrowdfunding.project.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{
   @Autowired
	private ProjectDao projectDao;

	public List<Project> pageQuery(Map<String, Object> paraMap) {
		return projectDao.pageQuery(paraMap);
		
	}

	public int queryCount(Map<String, Object> paraMap) {
		
		return projectDao.queryCount(paraMap);
	}

	public int queryCount() {
		
		return projectDao.queryCount();
	}

	public int insertProject(Project project) {
		return projectDao.insertProject(project);
		
	}

	public Project queryById(Integer id) {
		return projectDao.queryById( id);
	}

	public int updateProject(Project project) {
		return projectDao.updateProject( project);
	}

	public int deleteProject(Integer id) {
		return projectDao.deleteProject( id);
	}

	public int deleteUsers(Datas ds) {
		return projectDao.deleteUsers(ds);
	}






   
}
