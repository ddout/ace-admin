import fetch from 'utils/fetch';

export function page(query) {
return fetch({
url: '/api/pms/project/report/pmsProjectReport/page',
method: 'get',
params: query
});
}

export function addObj(obj) {
return fetch({
url: '/api/pms/project/report/pmsProjectReport',
method: 'post',
data: obj
});
}

export function getObj(id) {
return fetch({
url: '/api/pms/project/report/pmsProjectReport/' + id,
method: 'get'
})
}

export function delObj(id) {
return fetch({
url: '/api/pms/project/report/pmsProjectReport/' + id,
method: 'delete'
})
}

export function putObj(id, obj) {
return fetch({
url: '/api/pms/project/report/pmsProjectReport/' + id,
method: 'put',
data: obj
})
}


export function getProjectTypeList() {
  return fetch({
    url: '/api/pms/dict/baseDict/page',
    method: 'get',
    params: {"typeId":"a59adf96111c4408add8c275f06daabb"}
  });
}

export function getUserOptions() {
  return fetch({
    url: '/api/admin/user/page',
    method: 'get',
    params: {"limit":99999}
  });
}
