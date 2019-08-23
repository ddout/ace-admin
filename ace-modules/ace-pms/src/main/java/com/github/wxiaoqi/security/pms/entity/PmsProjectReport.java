package com.github.wxiaoqi.security.pms.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 项目立项报告
 *
 * @author author
 * @email email
 * @date 2019-08-23 19:10:56
 */
@Table(name = "pms_project_report")
public class PmsProjectReport implements Serializable {
	private static final long serialVersionUID = 1L;

	    	//ID
				@Id
		@GeneratedValue(generator="UUID")
		private String id;
			
	    //项目编号
    @Column(name = "project_code")
    private String projectCode;
	
	    //项目名称
    @Column(name = "project_name")
    private String projectName;
	
	    //项目类型
    @Column(name = "project_type")
    private String projectType;
	
	    //客户名称
    @Column(name = "customer")
    private String customer;
	
	    //项目发起人
    @Column(name = "sponsor")
    private String sponsor;
	
	    //项目负责人
    @Column(name = "project_manager")
    private String projectManager;
	
	    //研发负责人
    @Column(name = "dev_manager")
    private String devManager;
	
	    //立项时间
    @Column(name = "project_report_time")
    private Date projectReportTime;
	
	    //是否签订销售合同
    @Column(name = "sales_contract")
    private String salesContract;
	
	    //项目开始日期
    @Column(name = "project_begin_time")
    private Date projectBeginTime;
	
	    //项目结束日期
    @Column(name = "project_end_time")
    private Date projectEndTime;
	
	    //项目立项内容
    @Column(name = "project_context")
    private String projectContext;
	
	    //主要研究内容
    @Column(name = "dev_context")
    private String devContext;
	
	    //关键技术
    @Column(name = "key_technology")
    private String keyTechnology;
	
	    //创新点
    @Column(name = "innovation")
    private String innovation;
	
	    //项目开始期间
    @Column(name = "project_begin_period")
    private Date projectBeginPeriod;
	
	    //项目结束期间
    @Column(name = "project_end_period")
    private Date projectEndPeriod;
	
	    //人员人工预算
    @Column(name = "human_budget")
    private Double humanBudget;
	
	    //直接投入预算
    @Column(name = "direct_input_budget")
    private Double directInputBudget;
	
	    //设计费用预算
    @Column(name = "design_budget")
    private Double designBudget;
	
	    //其他费用预算
    @Column(name = "other_budget")
    private Double otherBudget;
	
	    //结论和建议
    @Column(name = "peoject_report_conclusion")
    private String peojectReportConclusion;
	

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
	 * 设置：项目编号
	 */
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	/**
	 * 获取：项目编号
	 */
	public String getProjectCode() {
		return projectCode;
	}
	/**
	 * 设置：项目名称
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * 获取：项目名称
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * 设置：项目类型
	 */
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	/**
	 * 获取：项目类型
	 */
	public String getProjectType() {
		return projectType;
	}
	/**
	 * 设置：客户名称
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	/**
	 * 获取：客户名称
	 */
	public String getCustomer() {
		return customer;
	}
	/**
	 * 设置：项目发起人
	 */
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	/**
	 * 获取：项目发起人
	 */
	public String getSponsor() {
		return sponsor;
	}
	/**
	 * 设置：项目负责人
	 */
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}
	/**
	 * 获取：项目负责人
	 */
	public String getProjectManager() {
		return projectManager;
	}
	/**
	 * 设置：研发负责人
	 */
	public void setDevManager(String devManager) {
		this.devManager = devManager;
	}
	/**
	 * 获取：研发负责人
	 */
	public String getDevManager() {
		return devManager;
	}
	/**
	 * 设置：立项时间
	 */
	public void setProjectReportTime(Date projectReportTime) {
		this.projectReportTime = projectReportTime;
	}
	/**
	 * 获取：立项时间
	 */
	public Date getProjectReportTime() {
		return projectReportTime;
	}
	/**
	 * 设置：是否签订销售合同
	 */
	public void setSalesContract(String salesContract) {
		this.salesContract = salesContract;
	}
	/**
	 * 获取：是否签订销售合同
	 */
	public String getSalesContract() {
		return salesContract;
	}
	/**
	 * 设置：项目开始日期
	 */
	public void setProjectBeginTime(Date projectBeginTime) {
		this.projectBeginTime = projectBeginTime;
	}
	/**
	 * 获取：项目开始日期
	 */
	public Date getProjectBeginTime() {
		return projectBeginTime;
	}
	/**
	 * 设置：项目结束日期
	 */
	public void setProjectEndTime(Date projectEndTime) {
		this.projectEndTime = projectEndTime;
	}
	/**
	 * 获取：项目结束日期
	 */
	public Date getProjectEndTime() {
		return projectEndTime;
	}
	/**
	 * 设置：项目立项内容
	 */
	public void setProjectContext(String projectContext) {
		this.projectContext = projectContext;
	}
	/**
	 * 获取：项目立项内容
	 */
	public String getProjectContext() {
		return projectContext;
	}
	/**
	 * 设置：主要研究内容
	 */
	public void setDevContext(String devContext) {
		this.devContext = devContext;
	}
	/**
	 * 获取：主要研究内容
	 */
	public String getDevContext() {
		return devContext;
	}
	/**
	 * 设置：关键技术
	 */
	public void setKeyTechnology(String keyTechnology) {
		this.keyTechnology = keyTechnology;
	}
	/**
	 * 获取：关键技术
	 */
	public String getKeyTechnology() {
		return keyTechnology;
	}
	/**
	 * 设置：创新点
	 */
	public void setInnovation(String innovation) {
		this.innovation = innovation;
	}
	/**
	 * 获取：创新点
	 */
	public String getInnovation() {
		return innovation;
	}
	/**
	 * 设置：项目开始期间
	 */
	public void setProjectBeginPeriod(Date projectBeginPeriod) {
		this.projectBeginPeriod = projectBeginPeriod;
	}
	/**
	 * 获取：项目开始期间
	 */
	public Date getProjectBeginPeriod() {
		return projectBeginPeriod;
	}
	/**
	 * 设置：项目结束期间
	 */
	public void setProjectEndPeriod(Date projectEndPeriod) {
		this.projectEndPeriod = projectEndPeriod;
	}
	/**
	 * 获取：项目结束期间
	 */
	public Date getProjectEndPeriod() {
		return projectEndPeriod;
	}
	/**
	 * 设置：人员人工预算
	 */
	public void setHumanBudget(Double humanBudget) {
		this.humanBudget = humanBudget;
	}
	/**
	 * 获取：人员人工预算
	 */
	public Double getHumanBudget() {
		return humanBudget;
	}
	/**
	 * 设置：直接投入预算
	 */
	public void setDirectInputBudget(Double directInputBudget) {
		this.directInputBudget = directInputBudget;
	}
	/**
	 * 获取：直接投入预算
	 */
	public Double getDirectInputBudget() {
		return directInputBudget;
	}
	/**
	 * 设置：设计费用预算
	 */
	public void setDesignBudget(Double designBudget) {
		this.designBudget = designBudget;
	}
	/**
	 * 获取：设计费用预算
	 */
	public Double getDesignBudget() {
		return designBudget;
	}
	/**
	 * 设置：其他费用预算
	 */
	public void setOtherBudget(Double otherBudget) {
		this.otherBudget = otherBudget;
	}
	/**
	 * 获取：其他费用预算
	 */
	public Double getOtherBudget() {
		return otherBudget;
	}
	/**
	 * 设置：结论和建议
	 */
	public void setPeojectReportConclusion(String peojectReportConclusion) {
		this.peojectReportConclusion = peojectReportConclusion;
	}
	/**
	 * 获取：结论和建议
	 */
	public String getPeojectReportConclusion() {
		return peojectReportConclusion;
	}
}
