import fetch from 'utils/fetch';

export function page(query) {
  return fetch({
    url: '/api/admin/user/page',
    method: 'get',
    params: query
  });
}

export function addObj(obj) {
  return fetch({
    url: '/api/admin/user',
    method: 'post',
    data: obj
  });
}

export function getObj(id) {
  return fetch({
    url: '/api/admin/user/' + id,
    method: 'get'
  });
}

export function delObj(id) {
  return fetch({
    url: '/api/admin/user/' + id,
    method: 'delete'
  });
}

export function putObj(id, obj) {
  return fetch({
    url: '/api/admin/user/' + id,
    method: 'put',
    data: obj
  });
}


export function updatePassword(token, passwordOld, passwordNew, passwordNew2) {
  const data = {
    token,
    passwordOld,
    passwordNew,
    passwordNew2
  };
  return fetch({
    url: '/api/admin/user/updatePassword',
    method: 'post',
    data
  });
}

export function resetPassword(id) {
  return fetch({
    url: '/api/admin/user/reset/password?id='+id,
    method: 'get'
  });
}

export function getAttr1List() {
  return fetch({
    url: '/api/pms/dict/baseDict/page',
    method: 'get',
    params: {"typeId":"8e618af71a44426e9a4fd64e604b1d3d"}
  });
}
