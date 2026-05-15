import request from '@/utils/request'

// 查询收益明细	列表
export function listRevenueDetail(query) {
  return request({
    url: '/strategy/revenueDetail/list',
    method: 'get',
    params: query
  })
}

// 查询收益明细	详细
export function getRevenueDetail(id) {
  return request({
    url: '/strategy/revenueDetail/' + id,
    method: 'get'
  })
}

// 新增收益明细	
export function addRevenueDetail(data) {
  return request({
    url: '/strategy/revenueDetail',
    method: 'post',
    data: data
  })
}

// 修改收益明细	
export function updateRevenueDetail(data) {
  return request({
    url: '/strategy/revenueDetail',
    method: 'put',
    data: data
  })
}

// 删除收益明细	
export function delRevenueDetail(id) {
  return request({
    url: '/strategy/revenueDetail/' + id,
    method: 'delete'
  })
}

// 导出收益明细	
export function exportRevenueDetail(query) {
  return request({
    url: '/strategy/revenueDetail/export',
    method: 'get',
    params: query
  })
}