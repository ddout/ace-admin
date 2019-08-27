package com.github.wxiaoqi.security.admin.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.wxiaoqi.security.common.exception.BizException;
import com.github.wxiaoqi.security.common.msg.TableResultResponse;
import com.github.wxiaoqi.security.common.util.Query;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.github.wxiaoqi.security.admin.entity.BaseDept;
import com.github.wxiaoqi.security.admin.mapper.BaseDeptMapper;
import com.github.wxiaoqi.security.common.biz.BaseBiz;
import tk.mybatis.mapper.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 组织机构
 *
 * @author author
 * @email email
 * @date 2019-08-27 19:54:19
 */
@Service
public class BaseDeptBiz extends BaseBiz<BaseDeptMapper,BaseDept> {

  @Autowired
  private BaseDeptMapper bizMapper;

  @Override
  public TableResultResponse selectByQuery(Query query) {

    //pid, name, code
    int cnt = bizMapper.selectByQueryCount(query);
    List<Map<String,Object>> data = new ArrayList<>();
    if(cnt > 0){
      data = bizMapper.selectByQuery(query);
    }
    return new TableResultResponse(cnt, data);
  }


  @Override
  protected void baseXInsertHandler(BaseDept entity) {
    String code = buildCode(entity);
    entity.setCode(code);
  }

  @Override
  protected void baseXDeleteByIdHandler(Object id) {
    int hasChild = bizMapper.getChildCnt(id);
    if(hasChild>0){
      throw new BizException("包含下级机构,不允许删除");
    }
    int hasUser = bizMapper.getChildUser(id);
    if(hasUser>0){
      throw new BizException("包含用户信息,不允许删除");
    }
  }

  @Override
  protected void baseXUpdateSelectiveByIdHandler(BaseDept entity) {
    BaseDept old = selectById(entity.getId());
    String oldPid = old.getPid()==null ? "" : old.getPid();
    if(oldPid.equals(entity.getPid())){
      entity.setCode(old.getCode());
    } else {
      entity.setCode(buildCode(entity));
    }

  }

  @Override
  protected void BaseXSelectByQueryHandler(Example example) {
  }

  private String buildCode(BaseDept entity){
    int cnt = bizMapper.getParentCodeCont(entity) + 1;
    String pcode = "";
    if(StringUtils.isNotEmpty(entity.getPid())){
      pcode = bizMapper.getParentCode(entity);
    }
    String code = "";
    if(cnt < 10){
      code = "D00" + cnt;
    } else if(cnt < 100){
      code = "D0" + cnt;
    } else {
      code = "D" + cnt;
    }
    return pcode + code;
  }


}
