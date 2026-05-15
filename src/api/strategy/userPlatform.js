import request from '@/utils/request'

// 查询用户平台私钥信息列表
export function listUserPlatform(query) {
  return request({
    url: '/strategy/userPlatform/list',
    method: 'get',
    params: query
  })
}

// 查询用户平台私钥信息列表
export function listUserPlatformAll(query) {
  return request({
    url: '/strategy/userPlatform/listAll',
    method: 'get',
    params: query
  })
}

// 查询用户平台私钥信息详细
export function getUserPlatform(id) {
  return request({
    url: '/strategy/userPlatform/' + id,
    method: 'get'
  })
}

// 新增用户平台私钥信息
export function addUserPlatform(data) {
  return request({
    url: '/strategy/userPlatform',
    method: 'post',
    data: data
  })
}

// 修改用户平台私钥信息
export function updateUserPlatform(data) {
  return request({
    url: '/strategy/userPlatform',
    method: 'put',
    data: data
  })
}

// 支付分润
export function updateShare(data) {
  return request({
    url: '/strategy/userPlatform/share',
    method: 'put',
    data: data
  })
}

// 删除用户平台私钥信息
export function delUserPlatform(id) {
  return request({
    url: '/strategy/userPlatform/' + id,
    method: 'delete'
  })
}

// 业务删除
export function updateDelete(id) {
  return request({
    url: '/strategy/userPlatform/updateDelete/'+id,
    method: 'delete',
  })
}

// 导出用户平台私钥信息
export function exportUserPlatform(query) {
  return request({
    url: '/strategy/userPlatform/export',
    method: 'get',
    params: query
  })
}
