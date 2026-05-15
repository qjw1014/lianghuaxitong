import request from '@/utils/request'

// 查询交易平台列表
export function listPlatform(query) {
  return request({
    url: '/strategy/platform/list',
    method: 'get',
    params: query
  })
}

// 查询交易平台列表
export function listPlatformAll(query) {
  return request({
    url: '/strategy/platform/listAll',
    method: 'get',
    params: query
  })
}

// 查询交易平台详细
export function getPlatform(id) {
  return request({
    url: '/strategy/platform/' + id,
    method: 'get'
  })
}

// 新增交易平台
export function addPlatform(data) {
  return request({
    url: '/strategy/platform',
    method: 'post',
    data: data
  })
}

// 修改交易平台
export function updatePlatform(data) {
  return request({
    url: '/strategy/platform',
    method: 'put',
    data: data
  })
}

// 删除交易平台
export function delPlatform(id) {
  return request({
    url: '/strategy/platform/' + id,
    method: 'delete'
  })
}

// 导出交易平台
export function exportPlatform(query) {
  return request({
    url: '/strategy/platform/export',
    method: 'get',
    params: query
  })
}
