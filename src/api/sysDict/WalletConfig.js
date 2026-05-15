import request from '@/utils/request'

// 查询钱包配置信息列表
export function listWalletConfig(query) {
  return request({
    url: '/sysDict/WalletConfig/list',
    method: 'get',
    params: query
  })
}

// 查询钱包配置信息详细
export function getWalletConfig(id) {
  return request({
    url: '/sysDict/WalletConfig/' + id,
    method: 'get'
  })
}

// 新增钱包配置信息
export function addWalletConfig(data) {
  return request({
    url: '/sysDict/WalletConfig',
    method: 'post',
    data: data
  })
}

// 修改钱包配置信息
export function updateWalletConfig(data) {
  return request({
    url: '/sysDict/WalletConfig',
    method: 'put',
    data: data
  })
}

// 删除钱包配置信息
export function delWalletConfig(id) {
  return request({
    url: '/sysDict/WalletConfig/' + id,
    method: 'delete'
  })
}

// 导出钱包配置信息
export function exportWalletConfig(query) {
  return request({
    url: '/sysDict/WalletConfig/export',
    method: 'get',
    params: query
  })
}