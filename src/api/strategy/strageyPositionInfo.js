import request from '@/utils/request'

// 查询策略仓位列表
export function listStrageyPositionInfo(query) {
  return request({
    url: '/strategy/strageyPositionInfo/list',
    method: 'get',
    params: query
  })
}

// 查询策略仓位列表
export function listStrageyPositionByStrategy(query) {
  return request({
    url: '/strategy/strageyPositionInfo/accountList',
    method: 'get',
    params: query
  })
}

// 查询策略仓位详细
export function getStrageyPositionInfo(id) {
  return request({
    url: '/strategy/strageyPositionInfo/' + id,
    method: 'get'
  })
}

// 新增策略仓位
export function addStrageyPositionInfo(data) {
  return request({
    url: '/strategy/strageyPositionInfo',
    method: 'post',
    data: data
  })
}

// 修改策略仓位
export function updateStrageyPositionInfo(data) {
  return request({
    url: '/strategy/strageyPositionInfo',
    method: 'put',
    data: data
  })
}

// 删除策略仓位
export function delStrageyPositionInfo(id) {
  return request({
    url: '/strategy/strageyPositionInfo/' + id,
    method: 'delete'
  })
}

// 导出策略仓位
export function exportStrageyPositionInfo(query) {
  return request({
    url: '/strategy/strageyPositionInfo/export',
    method: 'get',
    params: query
  })
}
