import request from '@/utils/request'

// 查询统计账户信息列表
export function listStatisticsAccount(query) {
  return request({
    url: '/strategy/statisticsAccount/list',
    method: 'get',
    params: query
  })
}

// 查询套利引擎专用统计（由 stats_server.py 提供）
export function getArbStatistics(apiAccountId) {
  return request({
    url: '/arb-stats/' + apiAccountId,
    method: 'get'
  })
}

// 查询统计账户信息详细
export function getStatisticsAccount(apiAccountId) {
  return request({
    url: '/strategy/statisticsAccount/' + apiAccountId,
    method: 'get'
  })
}

// 新增统计账户信息
export function addStatisticsAccount(data) {
  return request({
    url: '/strategy/statisticsAccount',
    method: 'post',
    data: data
  })
}

// 修改统计账户信息
export function updateStatisticsAccount(data) {
  return request({
    url: '/strategy/statisticsAccount',
    method: 'put',
    data: data
  })
}

// 删除统计账户信息
export function delStatisticsAccount(apiAccountId) {
  return request({
    url: '/strategy/statisticsAccount/' + apiAccountId,
    method: 'delete'
  })
}

// 导出统计账户信息
export function exportStatisticsAccount(query) {
  return request({
    url: '/strategy/statisticsAccount/export',
    method: 'get',
    params: query
  })
}