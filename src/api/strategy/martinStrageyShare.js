import request from '@/utils/request'

// 查询分润统计列表
export function listMartinStrageyShare(query) {
  return request({
    url: '/strategy/martinStrageyShare/list',
    method: 'get',
    params: query
  })
}

// 查询分润统计详细
export function getMartinStrageyShare(id) {
  return request({
    url: '/strategy/martinStrageyShare/' + id,
    method: 'get'
  })
}

// 新增分润统计
export function addMartinStrageyShare(data) {
  return request({
    url: '/strategy/martinStrageyShare',
    method: 'post',
    data: data
  })
}

// 修改分润统计
export function updateMartinStrageyShare(data) {
  return request({
    url: '/strategy/martinStrageyShare',
    method: 'put',
    data: data
  })
}

// 删除分润统计
export function delMartinStrageyShare(id) {
  return request({
    url: '/strategy/martinStrageyShare/' + id,
    method: 'delete'
  })
}

// 导出分润统计
export function exportMartinStrageyShare(query) {
  return request({
    url: '/strategy/martinStrageyShare/export',
    method: 'get',
    params: query
  })
}