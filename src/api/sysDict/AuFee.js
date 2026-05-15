import request from '@/utils/request'

// 查询手续费配置列表
export function listAuFee(query) {
  return request({
    url: '/sysDict/AuFee/list',
    method: 'get',
    params: query
  })
}

// 查询手续费配置详细
export function getAuFee(id) {
  return request({
    url: '/sysDict/AuFee/' + id,
    method: 'get'
  })
}

// 新增手续费配置
export function addAuFee(data) {
  return request({
    url: '/sysDict/AuFee',
    method: 'post',
    data: data
  })
}

// 修改手续费配置
export function updateAuFee(data) {
  return request({
    url: '/sysDict/AuFee',
    method: 'put',
    data: data
  })
}

// 删除手续费配置
export function delAuFee(id) {
  return request({
    url: '/sysDict/AuFee/' + id,
    method: 'delete'
  })
}

// 导出手续费配置
export function exportAuFee(query) {
  return request({
    url: '/sysDict/AuFee/export',
    method: 'get',
    params: query
  })
}