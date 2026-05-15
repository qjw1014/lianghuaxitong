<template>
  <div class="login">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form">
      <h3 class="title">板车资管平台</h3>
      <el-form-item prop="username">
        <el-input v-model="loginForm.username" type="text" auto-complete="off" placeholder="账号" autocomplete="off">
          <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
<!--        <el-button slot="append"  v-show="showTime"  @click="sendMail(loginForm.username)"  >获取验证码</el-button>-->
        <el-button slot="append"  v-show="!showTime"  @click="sendMail(loginForm.username)"  >{{sendTime}}秒</el-button>
        </el-input>

      </el-form-item>
     <el-form-item prop="password">
        <el-input v-model="loginForm.password" type="password" auto-complete="off" placeholder="密码"
          @keyup.enter.native="handleLogin">
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>


      <el-form-item prop="code">
        <el-input v-model="loginForm.code" auto-complete="off" placeholder="请输入谷歌验证码" style="width: 100%"
          @keyup.enter.native="handleLogin">
          <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" />
        </el-input>
        <!-- <div class="login-code">
          <img :src="codeUrl" @click="getCode" class="login-code-img"/>
        </div> -->
      </el-form-item>
      <!-- 人机验证 -->
      <!-- <el-form-item prop='validateCode'>
        <el-row :span="24">
          <el-col :span="24">
            <reCaptcha :sitekey="key" @getValidateCode='getValidateCode' v-model="loginForm.validateCode"></reCaptcha>
          </el-col>
        </el-row>
      </el-form-item> -->
      <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
      <el-form-item style="width:100%;">
        <el-button :loading="loading" size="medium" type="waming" style="width:100%;"
          @click.native.prevent="handleLogin">
          <span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
      </el-form-item>
    </el-form>

    <!--  底部  -->
    <div class="el-login-footer">
      <span></span>

    </div>
  </div>
</template>

<script>
  import {
    getCodeImg,send
  } from "@/api/login";
  import Cookies from "js-cookie";

  import {
    encrypt,
    decrypt
  } from '@/utils/jsencrypt'
  import reCaptcha from '@/components/Verification';

  export default {
    name: "Login",
    data() {
      var checkCode = (rule, value, callback) => {
        if (value == false) {
          callback(new Error('请进行人机验证'));
        } else {
          callback();
        }
      };
      return {
         showTime: true, /* 布尔值，通过v-show控制显示‘获取按钮’还是‘倒计时’ */
                sendTime: '', /* 倒计时 计数器 */
                timer: null,

        codeUrl: "",
        cookiePassword: "",
        key: '6LcBoGUaAAAAABUnZINfh4j6FgqpQR-yHakZepIR',
        loginForm: {
          username: "",
          password: "",
          rememberMe: false,
          code: "",
          uuid: "",
          getValidateCode: false
        },
        loginRules: {
          username: [{
            required: true,
            trigger: "blur",
            message: "用户名不能为空"
          }],
          password: [{
            required: true,
            trigger: "blur",
            message: "密码不能为空"
          }],
          code: [{
            required: true,
            trigger: "change",
            message: "验证码不能为空"
          }],
          validateCode: [{
            validator: checkCode,
            trigger: 'change'
          }]
        },
        loading: false,
        redirect: undefined
      };

    },
    watch: {
      $route: {
        handler: function(route) {
          this.redirect = route.query && route.query.redirect;
        },
        immediate: true
      }
    },




    created() {
      this.getCode();
      this.getCookie();
    },
    methods: {
      getValidateCode(value) {
        this.loginForm.validateCode = value
      },


      getCode() {
        getCodeImg().then(res => {
          this.codeUrl = "data:image/gif;base64," + res.img;
          this.loginForm.uuid = res.uuid;
        });
      },
      /**发送验证码 */
      sendMail(row) {
        const TIME_COUNT = 60; //  更改倒计时时间
          if (!this.timer) {
            this.sendTime = TIME_COUNT;
            this.showTime = false;
            this.timer = setInterval(() => {
              if (this.sendTime > 0 && this.sendTime <= TIME_COUNT) {
                this.sendTime--;
              } else {
                this.showTime = true;
                clearInterval(this.timer); // 清除定时器
                this.timer = null;
              }
            }, 1000);
          }


        send(row).then(res => {
          this.num = row;
          this.auditOpen = true;
          this.title = "发送成功";
          });
      },

      getCookie() {
        const username = Cookies.get("username");
        const password = Cookies.get("password");
        const rememberMe = Cookies.get('rememberMe')
        this.loginForm = {
          username: username === undefined ? this.loginForm.username : username,
          password: password === undefined ? this.loginForm.password : decrypt(password),
          rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
        };
      },
      handleLogin() {
        this.$refs.loginForm.validate(valid => {
          if (valid) {
            this.loading = true;
            if (this.loginForm.rememberMe) {
              Cookies.set("username", this.loginForm.username, {
                expires: 30
              });
              Cookies.set("password", encrypt(this.loginForm.password), {
                expires: 30
              });
              Cookies.set('rememberMe', this.loginForm.rememberMe, {
                expires: 30
              });
            } else {
              Cookies.remove("username");
              Cookies.remove("password");
              Cookies.remove('rememberMe');
            }
            this.$store.dispatch("Login", this.loginForm).then(() => {
              this.$router.push({
                path: this.redirect || "/"
              }).catch(() => {});
            }).catch(() => {
              this.loading = false;
              this.getCode();
            });
          }
        });
      }
    },
    components: {
      reCaptcha
    }
  };
</script>

<style rel="stylesheet/scss" lang="scss">
  .login {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    background-image: url("../assets/images/login-background.jpg");
    background-size: cover;
  }

  .title {
    margin: 0px auto 30px auto;
    text-align: center;
    color: #0A1146;
  }

  .login-form {
    position: fixed;
    top: 50%;
    -webkit-transform: translateY(-50%);
    transform: translateY(-50%);
    right: 12%;
    border-radius: 6px;
    width: 450px !important;
    padding: 70px 30px;
    background-image: url(../assets/images/logo-bg.png);
    background-size: cover;
    background-repeat: no-repeat;

    .el-input {
      height: 38px;

      input {
        height: 38px;
      }
    }

    .input-icon {
      height: 39px;
      width: 14px;
      margin-left: 2px;
    }
  }

  .login-tip {
    font-size: 13px;
    text-align: center;
    color: #bfbfbf;
  }

  .login-code {
    width: 33%;
    height: 38px;
    float: right;

    img {
      cursor: pointer;
      vertical-align: middle;
    }
  }

  .el-login-footer {
    height: 40px;
    line-height: 40px;
    position: fixed;
    bottom: 0;
    width: 100%;
    text-align: center;
    color: #fff;
    font-family: Arial;
    font-size: 12px;
    letter-spacing: 1px;
  }

  .login-code-img {
    height: 38px;
  }
</style>
