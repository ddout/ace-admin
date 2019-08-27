package com.github.wxiaoqi.security.pms.biz;

import com.github.wxiaoqi.security.common.biz.BaseBiz;
import com.github.wxiaoqi.security.common.exception.BizException;
import com.github.wxiaoqi.security.pms.entity.PmsProjectReport;
import com.github.wxiaoqi.security.pms.mapper.PmsProjectReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * 项目立项报告
 *
 * @author author
 * @email email
 * @date 2019-08-27 19:28:27
 */
@Service
public class PmsProjectReportBiz extends BaseBiz<PmsProjectReportMapper, PmsProjectReport> {

  @Autowired
  private PmsProjectReportMapper bizMapper;

  @Override
  protected void baseXDeleteByIdHandler(Object id) {
  }

  @Override
  protected void baseXInsertHandler(PmsProjectReport entity) {
    int cnt = bizMapper.checkCode(entity);
    if(cnt > 0){
      throw new BizException("项目编号重复");
    }
  }

  @Override
  protected void BaseXSelectByQueryHandler(Example example) {
  }

  @Override
  protected void baseXUpdateSelectiveByIdHandler(PmsProjectReport entity) {
    int cnt = bizMapper.checkCode(entity);
    if(cnt > 0){
      throw new BizException("项目编号重复");
    }
  }


}
