import request from '@/utils/request'

// 查询策略类型列表
export function listStrategyType(query) {
  return request({
    url: '/strategy/strategyType/list',
    method: 'get',
    params: query
  })
}

// 查询策略类型列表
export function listStrategyTypeAll(query) {
  return request({
    url: '/strategy/strategyType/listAll',
    method: 'get',
    params: query
  })
}

// 查询策略类型详细
export function getStrategyType(id) {
  return request({
    url: '/strategy/strategyType/' + id,
    method: 'get'
  })
}

// 新增策略类型
export function addStrategyType(data) {
  return request({
    url: '/strategy/strategyType',
    method: 'post',
    data: data
  })
}

// 修改策略类型
export function updateStrategyType(data) {
  return request({
    url: '/strategy/strategyType',
    method: 'put',
    data: data
  })
}

// 删除策略类型
export function delStrategyType(id) {
  return request({
    url: '/strategy/strategyType/' + id,
    method: 'delete'
  })
}

// 导出策略类型
export function exportStrategyType(query) {
  return request({
    url: '/strategy/strategyType/export',
    method: 'get',
    params: query
  })
}
