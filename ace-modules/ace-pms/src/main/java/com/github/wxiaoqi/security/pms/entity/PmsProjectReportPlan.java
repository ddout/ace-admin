package com.github.wxiaoqi.security.pms.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 项目进度安排
 *
 * @author author
 * @email email
 * @date 2019-08-23 22:18:30
 */
@Table(name = "pms_project_report_plan")
public class PmsProjectReportPlan implements Serializable {
	private static final long serialVersionUID = 1L;

	    	//ID
				@Id
		@GeneratedValue(generator="UUID")
		private String id;
			
	    //工作内容
    @Column(name = "context")
    private String context;
	
	    //开始时间
    @Column(name = "begin_time")
    private Date beginTime;
	
	    //结束时间
    @Column(name = "end_time")
    private Date endTime;
	
	    //立项报告
    @Column(name = "project_report_id")
    private String projectReportId;
	

	/**
	 * 设置：ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：工作内容
	 */
	public void setContext(String context) {
		this.context = context;
	}
	/**
	 * 获取：工作内容
	 */
	public String getContext() {
		return context;
	}
	/**
	 * 设置：开始时间
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * 获取：开始时间
	 */
	public Date getBeginTime() {
		return beginTime;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置：立项报告
	 */
	public void setProjectReportId(String projectReportId) {
		this.projectReportId = projectReportId;
	}
	/**
	 * 获取：立项报告
	 */
	public String getProjectReportId() {
		return projectReportId;
	}
}
