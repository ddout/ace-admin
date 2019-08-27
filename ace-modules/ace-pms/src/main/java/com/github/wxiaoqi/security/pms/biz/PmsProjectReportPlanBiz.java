package com.github.wxiaoqi.security.pms.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wxiaoqi.security.pms.entity.PmsProjectReportPlan;
import com.github.wxiaoqi.security.pms.mapper.PmsProjectReportPlanMapper;
import com.github.wxiaoqi.security.common.biz.BaseBiz;
import tk.mybatis.mapper.entity.Example;

/**
 * 项目进度安排
 *
 * @author author
 * @email email
 * @date 2019-08-27 19:28:27
 */
@Service
public class PmsProjectReportPlanBiz extends BaseBiz<PmsProjectReportPlanMapper,PmsProjectReportPlan> {

  @Autowired
  private PmsProjectReportPlanMapper bizMapper;

  @Override
  protected void baseXInsertHandler(PmsProjectReportPlan entity) {
  }

  @Override
  protected void baseXDeleteByIdHandler(Object id) {
  }

  @Override
  protected void baseXUpdateSelectiveByIdHandler(PmsProjectReportPlan entity) {
  }

  @Override
  protected void BaseXSelectByQueryHandler(Example example) {
  }

}
