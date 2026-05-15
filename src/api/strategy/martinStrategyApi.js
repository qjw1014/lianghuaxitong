import request from '@/utils/request'

// 查询api对应策略信息列表
export function listMartinStrategyApi(query) {
  return request({
    url: '/strategy/martinStrategyApi/list',
    method: 'get',
    params: query
  })
}

// 查询api对应策略信息详细
export function getMartinStrategyApi(id) {
  return request({
    url: '/strategy/martinStrategyApi/' + id,
    method: 'get'
  })
}

// 新增api对应策略信息
export function addMartinStrategyApi(data) {
  return request({
    url: '/strategy/martinStrategyApi',
    method: 'post',
    data: data
  })
}

// 修改api对应策略信息
export function updateBaseRate(data) {
  return request({
    url: '/strategy/martinStrategyApi/updateBaseRate',
    method: 'put',
    data: data
  })
}

// 修改api首单金额比例
export function updateMartinStrategyApi(data) {
  return request({
    url: '/strategy/martinStrategyApi',
    method: 'put',
    data: data
  })
}

// 开启api对应策略信息
export function startMartinStrategyApi(data) {
  return request({
    url: '/strategy/martinStrategyApi/startMartinStrategy',
    method: 'put',
    data: data
  })
}

// 关闭api对应策略信息
export function stopMartinStrategyApi(data) {
  return request({
    url: '/strategy/martinStrategyApi/stopMartinStrategy',
    method: 'put',
    data: data
  })
}

// 删除api对应策略信息
export function delMartinStrategyApi(id) {
  return request({
    url: '/strategy/martinStrategyApi/' + id,
    method: 'delete'
  })
}

// 导出api对应策略信息
export function exportMartinStrategyApi(query) {
  return request({
    url: '/strategy/martinStrategyApi/export',
    method: 'get',
    params: query
  })
}
