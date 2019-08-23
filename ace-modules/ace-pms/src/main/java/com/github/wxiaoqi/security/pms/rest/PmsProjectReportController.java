package com.github.wxiaoqi.security.pms.rest;

import com.github.wxiaoqi.security.common.rest.BaseController;
import com.github.wxiaoqi.security.pms.biz.PmsProjectReportBiz;
import com.github.wxiaoqi.security.pms.entity.PmsProjectReport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("project/report/pmsProjectReport")
public class PmsProjectReportController extends BaseController<PmsProjectReportBiz,PmsProjectReport> {

}
