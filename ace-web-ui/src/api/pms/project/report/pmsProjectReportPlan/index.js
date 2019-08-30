import fetch from 'utils/fetch';

export function page(query) {
return fetch({
url: '/api/pms/project/report/pmsProjectReportPlan/page',
method: 'get',
params: query
});
}

export function addObj(obj) {
return fetch({
url: '/api/pms/project/report/pmsProjectReportPlan',
method: 'post',
data: obj
});
}

export function getObj(id) {
return fetch({
url: '/api/pms/project/report/pmsProjectReportPlan/' + id,
method: 'get'
})
}

export function delObj(id) {
return fetch({
url: '/api/pms/project/report/pmsProjectReportPlan/' + id,
method: 'delete'
})
}

export function putObj(id, obj) {
return fetch({
url: '/api/pms/project/report/pmsProjectReportPlan/' + id,
method: 'put',
data: obj
})
}


export function getStageTypeList() {
  return fetch({
    url: '/api/pms/dict/baseDict/page',
    method: 'get',
    params: {"typeId":"d0e50e7ca54d43fbbe73284597ab6fe5"}
  });
}

export function getProjectReportList() {
  return fetch({
    url: '/api/pms/project/report/pmsProjectReport/page',
    method: 'get',
    params: {"limit":99999}
  });
}

