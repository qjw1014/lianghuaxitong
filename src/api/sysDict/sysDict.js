import request from '@/utils/request'

// 查询参数配置列表
export function listSysDict(query) {
  return request({
    url: '/sysDict/sysDict/list',
    method: 'get',
    params: query
  })
}

// 查询参数配置列表
export function parentList(query) {
  return request({
    url: '/sysDict/sysDict/parentList',
    method: 'get',
    params: query
  })
}

// 查询参数配置详细
export function getSysDict(id) {
  return request({
    url: '/sysDict/sysDict/' + id,
    method: 'get'
  })
}


// 新增参数配置
export function addSysDict(data) {
  return request({
    url: '/sysDict/sysDict',
    method: 'post',
    data: data
  })
}

// 修改参数配置
export function updateSysDict(data) {
  return request({
    url: '/sysDict/sysDict',
    method: 'put',
    data: data
  })
}

// 删除参数配置
export function delSysDict(id) {
  return request({
    url: '/sysDict/sysDict/' + id,
    method: 'delete'
  })
}

// 导出参数配置
export function exportSysDict(query) {
  return request({
    url: '/sysDict/sysDict/export',
    method: 'get',
    params: query
  })
}
