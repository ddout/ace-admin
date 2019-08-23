package com.github.wxiaoqi.security.pms.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 项目组成员
 *
 * @author author
 * @email email
 * @date 2019-08-23 19:05:51
 */
@Table(name = "pms_project_report_users")
public class PmsProjectReportUsers implements Serializable {
	private static final long serialVersionUID = 1L;

	    	//ID
				@Id
		@GeneratedValue(generator="UUID")
		private String id;
			
	    //研发人员
    @Column(name = "user")
    private String user;
	
	    //备注
    @Column(name = "remark")
    private String remark;
	

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
	 * 设置：研发人员
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * 获取：研发人员
	 */
	public String getUser() {
		return user;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
}
