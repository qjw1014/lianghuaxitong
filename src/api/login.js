import request from '@/utils/request'

// 登录方法
export function login(username, password, code, uuid) {
  const data = {
    username,
    password,
    code,
    uuid
  }
  return request({
    url: '/login',
    method: 'post',
    data: data
  })
}

// 发送邮件
export function send(accountNum) {

  return request({
    url: '/sendMessage?accountNum='+accountNum,
    method: 'get'
  })
}


// 获取用户详细信息
export function getInfo() {
  return request({
    url: '/getInfo',
    method: 'get'
  })
}

// 退出方法
export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

// 获取验证码
export function getCodeImg() {
  return request({
    url: '/captchaImage',
    method: 'get'
  })
}

// 验证谷歌验证码
export function checkGoogleAuth(data) {
  return request({
    url: '/checkGoogleAuth',
    method: 'post',
    data: data
  })
}

