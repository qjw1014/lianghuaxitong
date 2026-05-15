import request from '@/utils/request'

// 查询策略字段关系列表
export function listStrategyField(query) {
  return request({
    url: '/strategy/strategyField/list',
    method: 'get',
    params: query
  })
}

// 查询策略字段关系详细
export function getStrategyField(id) {
  return request({
    url: '/strategy/strategyField/' + id,
    method: 'get'
  })
}

// 新增策略字段关系
export function addStrategyField(data) {
  return request({
    url: '/strategy/strategyField',
    method: 'post',
    data: data
  })
}

// 新增策略字段关系
export function batchAddStrategyField(data) {
  return request({
    url: '/strategy/strategyField/batchAdd',
    method: 'post',
    data: data
  })
}

// 修改策略字段关系
export function updateStrategyField(data) {
  return request({
    url: '/strategy/strategyField',
    method: 'put',
    data: data
  })
}

// 删除策略字段关系
export function delStrategyField(id) {
  return request({
    url: '/strategy/strategyField/' + id,
    method: 'delete'
  })
}

// 导出策略字段关系
export function exportStrategyField(query) {
  return request({
    url: '/strategy/strategyField/export',
    method: 'get',
    params: query
  })
}
