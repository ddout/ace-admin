package com.github.wxiaoqi.security.pms.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wxiaoqi.security.pms.entity.PmsProjectReportUsers;
import com.github.wxiaoqi.security.pms.mapper.PmsProjectReportUsersMapper;
import com.github.wxiaoqi.security.common.biz.BaseBiz;
import tk.mybatis.mapper.entity.Example;

/**
 * 项目组成员
 *
 * @author author
 * @email email
 * @date 2019-08-27 19:28:27
 */
@Service
public class PmsProjectReportUsersBiz extends BaseBiz<PmsProjectReportUsersMapper,PmsProjectReportUsers> {

  @Autowired
  private PmsProjectReportUsersMapper bizMapper;

  @Override
  protected void baseXInsertHandler(PmsProjectReportUsers entity) {
  }

  @Override
  protected void baseXDeleteByIdHandler(Object id) {
  }

  @Override
  protected void baseXUpdateSelectiveByIdHandler(PmsProjectReportUsers entity) {
  }

  @Override
  protected void BaseXSelectByQueryHandler(Example example) {
  }

}
