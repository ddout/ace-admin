package com.github.wxiaoqi.security.pms.rest;

import com.github.wxiaoqi.security.common.rest.BaseController;
import com.github.wxiaoqi.security.pms.biz.BaseDictTypeBiz;
import com.github.wxiaoqi.security.pms.entity.BaseDictType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dict/baseDictType")
public class BaseDictTypeController extends BaseController<BaseDictTypeBiz,BaseDictType> {

}
