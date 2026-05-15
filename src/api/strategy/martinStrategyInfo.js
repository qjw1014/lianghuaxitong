import request from '@/utils/request'

// 查询策略信息列表
export function listMartinStrategyInfo(query) {
  return request({
    url: '/strategy/martinStrategyInfo/list',
    method: 'get',
    params: query
  })
}

// 查询策略信息详细
export function getMartinStrategyInfo(strategyId) {
  return request({
    url: '/strategy/martinStrategyInfo/' + strategyId,
    method: 'get'
  })
}

// 新增策略信息
export function addMartinStrategyInfo(data) {
  return request({
    url: '/strategy/martinStrategyInfo',
    method: 'post',
    data: data
  })
}

// 修改策略信息
export function updateMartinStrategyInfo(data) {
  return request({
    url: '/strategy/martinStrategyInfo',
    method: 'put',
    data: data
  })
}

// 删除策略信息
export function delMartinStrategyInfo(strategyId) {
  return request({
    url: '/strategy/martinStrategyInfo/' + strategyId,
    method: 'delete'
  })
}

// 业务删除策略信息
export function updateDelete(strategyId) {
  return request({
    url: '/strategy/martinStrategyInfo/updateDelete/' + strategyId,
    method: 'delete'
  })
}

// 导出策略信息
export function exportMartinStrategyInfo(query) {
  return request({
    url: '/strategy/martinStrategyInfo/export',
    method: 'get',
    params: query
  })
}
