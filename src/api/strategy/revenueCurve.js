import request from '@/utils/request'

// 查询收益曲线列表
export function listRevenueCurve(query) {
  return request({
    url: '/strategy/revenueCurve/list',
    method: 'get',
    params: query
  })
}

// 查询收益曲线详细
export function getRevenueCurve(id) {
  return request({
    url: '/strategy/revenueCurve/' + id,
    method: 'get'
  })
}

// 查询收益曲线图详情
export function getRevenueCurveData(id) {
  return request({
    url: '/strategy/revenueCurve/listByAccount/' + id,
    method: 'get'
  })
}

// 新增收益曲线
export function addRevenueCurve(data) {
  return request({
    url: '/strategy/revenueCurve',
    method: 'post',
    data: data
  })
}

// 修改收益曲线
export function updateRevenueCurve(data) {
  return request({
    url: '/strategy/revenueCurve',
    method: 'put',
    data: data
  })
}

// 删除收益曲线
export function delRevenueCurve(id) {
  return request({
    url: '/strategy/revenueCurve/' + id,
    method: 'delete'
  })
}

// 导出收益曲线
export function exportRevenueCurve(query) {
  return request({
    url: '/strategy/revenueCurve/export',
    method: 'get',
    params: query
  })
}
