package com.github.wxiaoqi.security.admin.rest;

import com.github.wxiaoqi.security.admin.biz.MenuBiz;
import com.github.wxiaoqi.security.admin.biz.UserBiz;
import com.github.wxiaoqi.security.admin.entity.Menu;
import com.github.wxiaoqi.security.admin.entity.User;
import com.github.wxiaoqi.security.admin.rpc.service.PermissionService;
import com.github.wxiaoqi.security.admin.vo.FrontUser;
import com.github.wxiaoqi.security.admin.vo.MenuTree;
import com.github.wxiaoqi.security.common.exception.BaseException;
import com.github.wxiaoqi.security.common.exception.BizException;
import com.github.wxiaoqi.security.common.rest.BaseController;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-08 11:51
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserBiz, User> {

  @Autowired
  private MenuBiz menuBiz;
  @Autowired
  private PermissionService permissionService;

  @RequestMapping(value = "/front/menu/all", method = RequestMethod.GET)
  public @ResponseBody
  List<Menu> getAllMenus() throws Exception {
    return menuBiz.selectListAll();
  }


  @RequestMapping(value = "/front/menus", method = RequestMethod.GET)
  public @ResponseBody
  List<MenuTree> getMenusByUsername(String token) throws Exception {
    return permissionService.getMenusByUsername(token);
  }


  @RequestMapping(value = "/front/info", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> getUserInfo(String token) throws Exception {
    FrontUser userInfo = permissionService.getUserInfo(token);
    if (userInfo == null) {
      return ResponseEntity.status(401).body(false);
    } else {
      return ResponseEntity.ok(userInfo);
    }
  }


  @RequestMapping(value = "/reset/password", method = RequestMethod.POST)
  @ResponseBody
  public void updatePassword(@RequestBody Map<String, String> map)
      throws Exception {
    if (null == map.get("token") || "".equals(map.get("token").trim())) {
      throw new BizException("非法token");
    }
    if (null == map.get("passwordOld") || "".equals(map.get("passwordOld").trim())) {
      throw new BizException("非发的旧密码");
    }
    if (null == map.get("passwordNew") || "".equals(map.get("passwordNew").trim())) {
      throw new BizException("非发的新密码");
    }
    if (null == map.get("passwordNew2") || "".equals(map.get("passwordNew2").trim())) {
      throw new BizException("非发的新密码");
    }
    String token = map.get("token").toString();
    String passwordOld = map.get("passwordOld").toString();
    String passwordNew = map.get("passwordNew").toString();
    String passwordNew2 = map.get("passwordNew2").toString();
    permissionService.updatePassword(token, passwordOld, passwordNew,passwordNew2 );
  }

  @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
  @ResponseBody
  public void resetPassword(int id)
      throws Exception {
    permissionService.resetPassword(id);
  }


}
