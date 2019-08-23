package com.github.wxiaoqi.security.pms.rest;

import com.github.wxiaoqi.security.common.rest.BaseController;
import com.github.wxiaoqi.security.pms.biz.PmsProjectReportPlanBiz;
import com.github.wxiaoqi.security.pms.entity.PmsProjectReportPlan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("project/report/pmsProjectReportPlan")
public class PmsProjectReportPlanController extends BaseController<PmsProjectReportPlanBiz,PmsProjectReportPlan> {

}
