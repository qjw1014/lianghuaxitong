import request from '@/utils/request'

// 查询充值记录	列表
export function listChargeHistory(query) {
  return request({
    url: '/strategy/chargeHistory/list',
    method: 'get',
    params: query
  })
}

// 查询充值记录	详细
export function getChargeHistory(id) {
  return request({
    url: '/strategy/chargeHistory/' + id,
    method: 'get'
  })
}

// 新增充值记录	
export function addChargeHistory(data) {
  return request({
    url: '/strategy/chargeHistory',
    method: 'post',
    data: data
  })
}

// 修改充值记录	
export function updateChargeHistory(data) {
  return request({
    url: '/strategy/chargeHistory',
    method: 'put',
    data: data
  })
}

// 删除充值记录	
export function delChargeHistory(id) {
  return request({
    url: '/strategy/chargeHistory/' + id,
    method: 'delete'
  })
}

// 导出充值记录	
export function exportChargeHistory(query) {
  return request({
    url: '/strategy/chargeHistory/export',
    method: 'get',
    params: query
  })
}