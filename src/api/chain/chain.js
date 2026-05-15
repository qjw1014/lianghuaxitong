import request from '@/utils/request'

// 查询链列表
export function listChain(query) {
  return request({
    url: '/chain/chain/list',
    method: 'get',
    params: query
  })
}

// 查询链列表（不分页查询可用的）
export function listChainAll(query) {
  return request({
    url: '/chain/chain/listAll',
    method: 'get',
    params: query
  })
}

// 查询链详细
export function getChain(id) {
  return request({
    url: '/chain/chain/' + id,
    method: 'get'
  })
}

// 新增链
export function addChain(data) {
  return request({
    url: '/chain/chain',
    method: 'post',
    data: data
  })
}

// 修改链
export function updateChain(data) {
  return request({
    url: '/chain/chain',
    method: 'put',
    data: data
  })
}

// 修改链状态
export function changeStatus(id,status) {
  const data ={
    id,
    status
  }
  return request({
    url: '/chain/chain',
    method: 'put',
    data: data
  })
}

// 删除链
export function delChain(id) {
  return request({
    url: '/chain/chain/' + id,
    method: 'delete'
  })
}

// 导出链
export function exportChain(query) {
  return request({
    url: '/chain/chain/export',
    method: 'get',
    params: query
  })
}
