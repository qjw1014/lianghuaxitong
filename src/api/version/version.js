import request from '@/utils/request'

// 查询app版本升级列表
export function listVersion(query) {
  return request({
    url: '/version/version/list',
    method: 'get',
    params: query
  })
}

// 查询app版本升级详细
export function getVersion(id) {
  return request({
    url: '/version/version/' + id,
    method: 'get'
  })
}

// 新增app版本升级
export function addVersion(data) {
  return request({
    url: '/version/version',
    method: 'post',
    data: data
  })
}

// 修改app版本升级
export function updateVersion(data) {
  return request({
    url: '/version/version',
    method: 'put',
    data: data
  })
}

// 修改app版本升级
export function changeStatus(id,status) {
  const  data={
    id,
    status
  }
  return request({
    url: '/version/version',
    method: 'put',
    data: data
  })
}


// 删除app版本升级
export function delVersion(id) {
  return request({
    url: '/version/version/' + id,
    method: 'delete'
  })
}

// 导出app版本升级
export function exportVersion(query) {
  return request({
    url: '/version/version/export',
    method: 'get',
    params: query
  })
}
