package com.github.wxiaoqi.security.admin.mapper;

import com.github.wxiaoqi.security.admin.entity.BaseDept;
import com.github.wxiaoqi.security.common.util.Query;
import java.util.List;
import java.util.Map;
import tk.mybatis.mapper.common.Mapper;

/**
 * 组织机构
 *
 * @author author
 * @email email
 * @date 2019-08-27 19:54:19
 */
public interface BaseDeptMapper extends Mapper<BaseDept> {

  int getChildCnt(Object id);

  int getChildUser(Object id);

  String getParentCode(BaseDept entity);

  int getParentCodeCont(BaseDept entity);

  List<Map<String, Object>> selectByQuery(Query query);

  int selectByQueryCount(Query query);
}
