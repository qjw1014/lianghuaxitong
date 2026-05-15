import request from '@/utils/request'

// 查询策略订单信息列表
export function listMartinStrategyOrder(query) {
  return request({
    url: '/strategy/martinStrategyOrder/list',
    method: 'get',
    params: query
  })
}

// 查询策略订单信息详细
export function getMartinStrategyOrder(serialId) {
  return request({
    url: '/strategy/martinStrategyOrder/' + serialId,
    method: 'get'
  })
}

// 查询策略订单信息详细
export function getCountOrder() {
  return request({
    url: '/strategy/martinStrategyOrder/getCountOrder',
    method: 'get'
  })
}

// 新增策略订单信息
export function addMartinStrategyOrder(data) {
  return request({
    url: '/strategy/martinStrategyOrder',
    method: 'post',
    data: data
  })
}

// 修改策略订单信息
export function updateMartinStrategyOrder(data) {
  return request({
    url: '/strategy/martinStrategyOrder',
    method: 'put',
    data: data
  })
}

// 删除策略订单信息
export function delMartinStrategyOrder(serialId) {
  return request({
    url: '/strategy/martinStrategyOrder/' + serialId,
    method: 'delete'
  })
}

// 导出策略订单信息
export function exportMartinStrategyOrder(query) {
  return request({
    url: '/strategy/martinStrategyOrder/export',
    method: 'get',
    params: query
  })
}
