package com.github.wxiaoqi.security.common.rest;

import com.github.wxiaoqi.security.common.biz.BaseBiz;
import com.github.wxiaoqi.security.common.context.BaseContextHandler;
import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;
import com.github.wxiaoqi.security.common.msg.TableResultResponse;
import com.github.wxiaoqi.security.common.util.Query;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-15 8:48
 */
@Slf4j
public class BaseController<Biz extends BaseBiz, Entity> {

  @Autowired
  protected Biz baseBiz;
  @Autowired
  protected HttpServletRequest request;

  @RequestMapping(value = "", method = RequestMethod.POST)
  @ResponseBody
  public ObjectRestResponse<Entity> add(@RequestBody Entity entity) {
    baseBiz.insertSelective(entity);
    return new ObjectRestResponse<Entity>();
  }

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  @ResponseBody
  public List<Entity> all() {
    return baseBiz.selectListAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ObjectRestResponse<Entity> get(@PathVariable String id) {
    ObjectRestResponse<Entity> entityObjectRestResponse = new ObjectRestResponse<>();
    Object o = baseBiz.selectById(parseId(id));
    entityObjectRestResponse.data((Entity) o);
    return entityObjectRestResponse;
  }


  public String getCurrentUserName() {
    return BaseContextHandler.getUsername();
  }

  @RequestMapping(value = "/page", method = RequestMethod.GET)
  @ResponseBody
  public TableResultResponse<Entity> list(@RequestParam Map<String, Object> params) {
    //查询列表数据
    Query query = new Query(params);
    return baseBiz.selectByQuery(query);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public ObjectRestResponse<Entity> remove(@PathVariable String id) {
    baseBiz.deleteById(parseId(id));
    return new ObjectRestResponse<Entity>();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseBody
  public ObjectRestResponse<Entity> update(@RequestBody Entity entity) {
    baseBiz.updateSelectiveById(entity);
    return new ObjectRestResponse<Entity>();
  }

  public static final Object parseId(String id){
    Object val = id;
    try{
      int ids = Integer.parseInt(id);
      val = ids;
    } catch (Exception e){
    }
    return val;
  }

}
