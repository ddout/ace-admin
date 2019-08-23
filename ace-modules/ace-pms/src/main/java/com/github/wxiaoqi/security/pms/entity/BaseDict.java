package com.github.wxiaoqi.security.pms.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 字典值
 *
 * @author author
 * @email email
 * @date 2019-08-23 10:08:55
 */
@Table(name = "base_dict")
public class BaseDict implements Serializable {
	private static final long serialVersionUID = 1L;

	    	//ID
				@Id
		@GeneratedValue(generator="UUID")
		private String id;
			
	    //名称
    @Column(name = "name")
    private String name;
	
	    //值
    @Column(name = "value")
    private String value;
	
	    //类型ID
    @Column(name = "type_id")
    private String typeId;
	

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
	 * 设置：值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：值
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置：类型ID
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	/**
	 * 获取：类型ID
	 */
	public String getTypeId() {
		return typeId;
	}
}
