import request from '@/utils/request'

// 查询策略信息列表
export function listStrategyInfo(query) {
  return request({
    url: '/strategy/strategyInfo/list',
    method: 'get',
    params: query
  })
}

// 查询策略信息详细
export function getStrategyInfo(strategyId) {
  return request({
    url: '/strategy/strategyInfo/' + strategyId,
    method: 'get'
  })
}

// 新增策略信息
export function addStrategyInfo(data) {
  return request({
    url: '/strategy/strategyInfo',
    method: 'post',
    data: data
  })
}

// 修改策略信息
export function updateStrategyInfo(data) {
  return request({
    url: '/strategy/strategyInfo',
    method: 'put',
    data: data
  })
}

// 开启策略
export function updateStatus(strategyId,strategyStatus) {
  const data = {
    strategyId,
    strategyStatus
  }
  return request({
    url: '/strategy/strategyInfo/updateStatus',
    method: 'post',
    data: data
  })
}

// 业务删除策略信息
export function updateDelete(strategyId) {
  return request({
    url: '/strategy/strategyInfo/updateDelete/' + strategyId,
    method: 'delete'
  })
}

// 删除策略信息
export function delStrategyInfo(strategyId) {
  return request({
    url: '/strategy/strategyInfo/' + strategyId,
    method: 'delete'
  })
}

// 导出策略信息
export function exportStrategyInfo(query) {
  return request({
    url: '/strategy/strategyInfo/export',
    method: 'get',
    params: query
  })
}
