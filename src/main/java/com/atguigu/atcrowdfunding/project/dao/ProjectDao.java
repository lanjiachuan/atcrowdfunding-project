package com.atguigu.atcrowdfunding.project.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.bean.Datas;
import com.atguigu.atcrowdfunding.project.bean.Project;

public interface ProjectDao {

	public List<Project> pageQuery(Map<String, Object> paraMap);

	public int queryCount(Map<String, Object> paraMap);

	public int queryCount();

	public int insertProject(Project project);

	public Project queryById(Integer id);

	public int updateProject(Project project);

	public int deleteProject(Integer id);

	public int deleteUsers(Datas ds);

}
