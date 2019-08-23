import fetch from 'utils/fetch';

export function page(query) {
return fetch({
url: '/api/pms/dict/baseDict/page',
method: 'get',
params: query
});
}

export function addObj(obj) {
return fetch({
url: '/api/pms/dict/baseDict',
method: 'post',
data: obj
});
}

export function getObj(id) {
return fetch({
url: '/api/pms/dict/baseDict/' + id,
method: 'get'
})
}

export function delObj(id) {
return fetch({
url: '/api/pms/dict/baseDict/' + id,
method: 'delete'
})
}

export function putObj(id, obj) {
return fetch({
url: '/api/pms/dict/baseDict/' + id,
method: 'put',
data: obj
})
}
