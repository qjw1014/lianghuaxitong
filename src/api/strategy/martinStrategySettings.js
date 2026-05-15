import request from '@/utils/request'

// 查询策略比例设置列表
export function listMartinStrategySettings(query) {
  return request({
    url: '/strategy/martinStrategySettings/list',
    method: 'get',
    params: query
  })
}

// 查询策略比例设置详细
export function getMartinStrategySettings(id) {
  return request({
    url: '/strategy/martinStrategySettings/' + id,
    method: 'get'
  })
}

// 新增策略比例设置
export function addMartinStrategySettings(data) {
  return request({
    url: '/strategy/martinStrategySettings',
    method: 'post',
    data: data
  })
}

// 修改策略比例设置
export function updateMartinStrategySettings(data) {
  return request({
    url: '/strategy/martinStrategySettings',
    method: 'put',
    data: data
  })
}

// 删除策略比例设置
export function delMartinStrategySettings(id) {
  return request({
    url: '/strategy/martinStrategySettings/' + id,
    method: 'delete'
  })
}

// 导出策略比例设置
export function exportMartinStrategySettings(query) {
  return request({
    url: '/strategy/martinStrategySettings/export',
    method: 'get',
    params: query
  })
}