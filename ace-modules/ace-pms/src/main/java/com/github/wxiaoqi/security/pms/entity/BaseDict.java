package com.github.wxiaoqi.security.pms.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 
 * 
 * @author author
 * @email email
 * @date 2019-08-21 19:15:00
 */
@Table(name = "base_dict")
public class BaseDict implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private String id;
	
	    //
    @Column(name = "name")
    private String name;
	
	    //
    @Column(name = "value")
    private String value;
	
	    //
    @Column(name = "type_id")
    private String typeId;
	

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置：
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	/**
	 * 获取：
	 */
	public String getTypeId() {
		return typeId;
	}
}
