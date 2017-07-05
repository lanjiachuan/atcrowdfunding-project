package com.atguigu.atcrowdfunding.projice.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.common.bean.AJAXResult;
import com.atguigu.atcrowdfunding.common.bean.Datas;
import com.atguigu.atcrowdfunding.common.bean.Page;
import com.atguigu.atcrowdfunding.project.bean.Project;
import com.atguigu.atcrowdfunding.project.service.ProjectService;
import com.atguigu.atcrowdfunding.util.StringUtil;

@Controller
@RequestMapping("/project")
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	
	/**
	 * 分页查询
	 * 
	 * @param pageText
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(String pageText, Integer pageNo, Integer pageSize) {

		AJAXResult result = new AJAXResult();

			try {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("start", (pageNo - 1) * pageSize);
			paraMap.put("size", pageSize);
			if ( StringUtil.isNotEmpty(pageText) ) {
				pageText = pageText.replaceAll("%", "\\%");
			}
			paraMap.put("pageText", pageText);
			// 分页查询数据
			List<Project> projects = projectService.pageQuery(paraMap);
			// 获取数据的总条数
			int acount = projectService.queryCount(paraMap);
			int totalNo = 0;
			if (acount % pageSize == 0) {
				totalNo = acount / pageSize;
			} else {
				totalNo = acount / pageSize + 1;
			}

			Page<Project> projectPage = new Page<Project>();

			projectPage.setDatas(projects);
			projectPage.setPageNo(pageNo);
			projectPage.setPageSize(pageSize);
			projectPage.setTotalNo(totalNo);
			projectPage.setTotalSize(acount);

			result.setPageObj(projectPage);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		} 
		return result;
		
	}
	/**
	 * 批量删除项目
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deletes")
	public Object deletes(Datas ds) {
		AJAXResult result = new AJAXResult();
		try {
			int count = projectService.deleteUsers(ds);
			if (count == ds.getDatas().size()) {
				result.setSuccess(true);
			}else {
				result.setSuccess(false);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;

	}
	/**
	 * 删除项目
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete(Integer id) {
		AJAXResult result = new AJAXResult();
		try {
			int count = projectService.deleteProject(id);
			if (count == 1) {
				result.setSuccess(true);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;

	}
	/**
	 * 修改
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Object update(Project project) {
		AJAXResult result = new AJAXResult();
		try {
			int count = projectService.updateProject(project);
			if (count == 1) {
				result.setSuccess(true);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	/**
	 * user/add跳转到user/edit
	 * 
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(Model model, Integer id) {
		Project project = projectService.queryById(id);
		model.addAttribute("project", project);
		return "project/edit";
	}
	/**
	 * 添加项目
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert(Project project) {
		AJAXResult result = new AJAXResult();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			project.setCreatedate(sdf.format(new Date()));
			int count = projectService.insertProject(project);
			if (count == 1) {
				result.setSuccess(true);
			}else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
		
	}
	/**
	 * 跳转项目form页面
	 * @return
	 */
	@RequestMapping("/form")
	public String form() {
		
		
		return "project/form";
		
	}
	/**
	 * 跳转project_type页面
	 * @return
	 */
	@RequestMapping("/project_type")
	public String project_type() {
		
		
		return "manager/project_type";
		
	}

	@RequestMapping("/project")
	public String project(){
		
		return "project/project";
	}
	
	@RequestMapping("/projects")
	public String projects(){
		return "project/projects";
	}
	
	@RequestMapping("/start")
	public String start() {
		
		return "project/start";
	}
	
	@RequestMapping("/start-step-1")
	public String start1(){
		return "project/start-step-1";
	}
	
	@RequestMapping("/start-step-2")
	public String start2(){
		return "project/start-step-2";
	}
	
	@RequestMapping("/start-step-3")
	public String start3(){
		return "project/start-step-3";
	}
	
	@RequestMapping("/start-step-4")
	public String start4(){
		return "project/start-step-4";
	}
}
