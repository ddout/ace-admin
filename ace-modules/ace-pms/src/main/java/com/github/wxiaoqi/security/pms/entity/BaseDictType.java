package com.github.wxiaoqi.security.pms.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 字典类型
 *
 * @author author
 * @email email
 * @date 2019-08-23 10:08:54
 */
@Table(name = "base_dict_type")
public class BaseDictType implements Serializable {
	private static final long serialVersionUID = 1L;

	    	//ID
				@Id
		@GeneratedValue(generator="UUID")
		private String id;
			
	    //名称
    @Column(name = "name")
    private String name;
	

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
}
