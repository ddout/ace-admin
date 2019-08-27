package com.github.wxiaoqi.security.admin.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 组织机构
 *
 * @author author
 * @email email
 * @date 2019-08-27 19:54:19
 */
@Table(name = "base_dept")
public class BaseDept implements Serializable {
	private static final long serialVersionUID = 1L;

	    	//id
				@Id
		@GeneratedValue(generator="UUID")
		private String id;
			
	    //名称
    @Column(name = "name")
    private String name;
	
	    //代码
    @Column(name = "code")
    private String code;
	
	    //父部门
    @Column(name = "pid")
    private String pid;
	

	/**
	 * 设置：id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：代码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：代码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：父部门
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * 获取：父部门
	 */
	public String getPid() {
		return pid;
	}
}
