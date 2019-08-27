package com.github.wxiaoqi.security.admin.rest;

import com.github.wxiaoqi.security.common.rest.BaseController;
import com.github.wxiaoqi.security.admin.biz.BaseDeptBiz;
import com.github.wxiaoqi.security.admin.entity.BaseDept;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dept/baseDept")
public class BaseDeptController extends BaseController<BaseDeptBiz,BaseDept> {

}
