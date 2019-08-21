package com.github.wxiaoqi.security.pms.rest;

import com.github.wxiaoqi.security.common.rest.BaseController;
import com.github.wxiaoqi.security.pms.biz.BaseDictBiz;
import com.github.wxiaoqi.security.pms.entity.BaseDict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dict/baseDict")
public class BaseDictController extends BaseController<BaseDictBiz,BaseDict> {

}
