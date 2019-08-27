package com.github.wxiaoqi.security.common.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.wxiaoqi.security.common.msg.TableResultResponse;
import com.github.wxiaoqi.security.common.util.EntityUtils;
import com.github.wxiaoqi.security.common.util.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * Created by Mr.AG Date: 17/1/13 Time: 15:13 Version 1.0.0
 */
public abstract class BaseBiz<M extends Mapper<T>, T> {

  @Autowired
  protected M mapper;

  public void delete(T entity) {
    deleteHandler(entity);
    mapper.delete(entity);
  }


  protected void deleteHandler(T entity) {
  }


  public void deleteById(Object id) {
    baseXDeleteByIdHandler(id);
    mapper.deleteByPrimaryKey(id);
  }


  protected void baseXDeleteByIdHandler(Object id) {
  }


  public void insert(T entity) {
    EntityUtils.setCreatAndUpdatInfo(entity);
    baseXInsertHandler(entity);
    mapper.insert(entity);
  }


  protected void baseXInsertHandler(T entity) {
  }


  public void insertSelective(T entity) {
    EntityUtils.setCreatAndUpdatInfo(entity);
    baseXInsertHandler(entity);
    mapper.insertSelective(entity);
  }


  public List<T> selectByExample(Object example) {
    return mapper.selectByExample(example);
  }


  public T selectById(Object id) {
    return mapper.selectByPrimaryKey(id);
  }


  public TableResultResponse<T> selectByQuery(Query query) {
    Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
        .getActualTypeArguments()[1];
    Example example = new Example(clazz);
    if (query.entrySet().size() > 0) {
      Example.Criteria criteria = example.createCriteria();
      for (Map.Entry<String, Object> entry : query.entrySet()) {
        criteria.andLike(entry.getKey(), "%" + entry.getValue().toString() + "%");
      }
    }
    Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
    BaseXSelectByQueryHandler(example);
    List<T> list = mapper.selectByExample(example);
    return new TableResultResponse<T>(result.getTotal(), list);
  }


  protected void BaseXSelectByQueryHandler(Example example) {
  }


  ;

  public Long selectCount(T entity) {
    return new Long(mapper.selectCount(entity));
  }


  public int selectCountByExample(Object example) {
    return mapper.selectCountByExample(example);
  }


  public List<T> selectList(T entity) {
    return mapper.select(entity);
  }


  public List<T> selectListAll() {
    return mapper.selectAll();
  }


  public T selectOne(T entity) {
    return mapper.selectOne(entity);
  }


  public void setMapper(M mapper) {
    this.mapper = mapper;
  }


  public void updateById(T entity) {
    EntityUtils.setUpdatedInfo(entity);
    baseXUpdateSelectiveByIdHandler(entity);
    mapper.updateByPrimaryKey(entity);
  }


  protected void baseXUpdateSelectiveByIdHandler(T entity) {

  }


  public void updateSelectiveById(T entity) {
    EntityUtils.setUpdatedInfo(entity);
    baseXUpdateSelectiveByIdHandler(entity);
    mapper.updateByPrimaryKeySelective(entity);

  }


}
