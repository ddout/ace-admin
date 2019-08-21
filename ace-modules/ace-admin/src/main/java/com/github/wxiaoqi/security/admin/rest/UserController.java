package com.github.wxiaoqi.security.admin.rest;

import com.github.wxiaoqi.security.admin.biz.MenuBiz;
import com.github.wxiaoqi.security.admin.biz.UserBiz;
import com.github.wxiaoqi.security.admin.entity.Menu;
import com.github.wxiaoqi.security.admin.entity.User;
import com.github.wxiaoqi.security.admin.rpc.service.PermissionService;
import com.github.wxiaoqi.security.admin.vo.FrontUser;
import com.github.wxiaoqi.security.admin.vo.MenuTree;
import com.github.wxiaoqi.security.common.rest.BaseController;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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


  @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity updatePassword(@RequestBody Map<String, String> map)
      throws Exception {
    if (null == map.get("token") || "".equals(map.get("token").trim())) {
      throw new RuntimeException("无效的token");
    }
    if (null == map.get("password") || "".equals(map.get("password").trim())) {
      throw new RuntimeException("password");
    }
    String token = map.get("token").toString();
    String password = map.get("password").toString();
    permissionService.updatePassword(token, password);
    return ResponseEntity.status(200).body(true);
  }


}
