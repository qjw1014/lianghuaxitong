import request from '@/utils/request'

// 查询币种列表
export function listCoinInfo(query) {
  return request({
    url: '/strategy/coinInfo/list',
    method: 'get',
    params: query
  })
}

// 查询币种列表
export function listCoinInfoAll(query) {
  return request({
    url: '/strategy/coinInfo/listAll',
    method: 'get',
    params: query
  })
}

// 查询币种详细
export function getCoinInfo(id) {
  return request({
    url: '/strategy/coinInfo/' + id,
    method: 'get'
  })
}

// 新增币种
export function addCoinInfo(data) {
  return request({
    url: '/strategy/coinInfo',
    method: 'post',
    data: data
  })
}

// 修改币种
export function updateCoinInfo(data) {
  return request({
    url: '/strategy/coinInfo',
    method: 'put',
    data: data
  })
}

// 删除币种
export function delCoinInfo(id) {
  return request({
    url: '/strategy/coinInfo/' + id,
    method: 'delete'
  })
}

// 导出币种
export function exportCoinInfo(query) {
  return request({
    url: '/strategy/coinInfo/export',
    method: 'get',
    params: query
  })
}
