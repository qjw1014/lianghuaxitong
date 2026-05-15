<template>
    <div ref="grecaptcha"></div>
</template>
<script>
export default {
  props: ["sitekey"], // 所要传的秘钥
  methods: {
    loaded() {
      window.grecaptcha.render(this.$refs.grecaptcha, {
        sitekey: this.sitekey,
        /**
        * res返回的是goole的验证情况，
        * 成功返回字符串
        * 失败不返回字符串
        * 所以根据字符串判断验证情况
        */
        callback: res => {
            res.length > 0 ? 
            // true  - 验证成功
            // false - 验证失败
            this.$emit("getValidateCode", true) : 
            this.$emit("getValidateCode", false)
        }
      });
    }
  },
  mounted() {
    window.ReCaptchaLoaded = this.loaded;
    var script = document.createElement("script");
    script.src =
      "https://recaptcha.net/recaptcha/api.js?onload=ReCaptchaLoaded&render=explicit";
    document.head.appendChild(script);
  }
};
</script>