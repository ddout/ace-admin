package com.github.wxiaoqi.security.pms.rest;

import com.github.wxiaoqi.security.common.rest.BaseController;
import com.github.wxiaoqi.security.pms.biz.PmsProjectReportUsersBiz;
import com.github.wxiaoqi.security.pms.entity.PmsProjectReportUsers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("project/report/pmsProjectReportUsers")
public class PmsProjectReportUsersController extends BaseController<PmsProjectReportUsersBiz,PmsProjectReportUsers> {

}
