import request from '@/utils/request'

// 查询策略公共字段描述列表
export function listCommonField(query) {
  return request({
    url: '/strategy/commonField/list',
    method: 'get',
    params: query
  })
}

// 查询策略公共字段描述详细
export function typeList(strategyId) {
  return request({
    url: '/strategy/commonField/typeList/' + strategyId,
    method: 'get'
  })
}

// 查询策略公共字段描述详细
export function getCommonField(id) {
  return request({
    url: '/strategy/commonField/' + id,
    method: 'get'
  })
}

// 新增策略公共字段描述
export function addCommonField(data) {
  return request({
    url: '/strategy/commonField',
    method: 'post',
    data: data
  })
}

// 修改策略公共字段描述
export function updateCommonField(data) {
  return request({
    url: '/strategy/commonField',
    method: 'put',
    data: data
  })
}

// 删除策略公共字段描述
export function delCommonField(id) {
  return request({
    url: '/strategy/commonField/' + id,
    method: 'delete'
  })
}

// 导出策略公共字段描述
export function exportCommonField(query) {
  return request({
    url: '/strategy/commonField/export',
    method: 'get',
    params: query
  })
}
