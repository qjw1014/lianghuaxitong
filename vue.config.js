
'use strict'
const path = require('path')
const defaultSettings = require('./src/settings.js')

function resolve(dir) {
  return path.join(__dirname, dir)
}

const name = defaultSettings.title || 'BMS Wallet' // 鏍囬
const port = process.env.port || process.env.npm_config_port || 80 // 绔彛
// vue.config.js 閰嶇疆璇存槑
//瀹樻柟vue.config.js 鍙傝€冩枃妗?https://cli.vuejs.org/zh/config/#css-loaderoptions
// vue-cli config
module.exports = {
  publicPath: process.env.NODE_ENV === 'production' ? '/' : '/',
  outputDir: 'dist',
  assetsDir: 'static',
  // 閮ㄧ讲鐢熶骇鐜鍜屽紑鍙戠幆澧冧笅鐨刄RL銆?  // 榛樿鎯呭喌涓嬶紝Vue CLI 浼氬亣璁句綘鐨勫簲鐢ㄦ槸琚儴缃插湪涓€涓煙鍚嶇殑鏍硅矾寰勪笂
  // 渚嬪 https://www.ruoyi.vip/銆傚鏋滃簲鐢ㄨ閮ㄧ讲鍦ㄤ竴涓瓙璺緞涓婏紝浣犲氨闇€瑕佺敤杩欎釜閫夐」鎸囧畾杩欎釜瀛愯矾寰勩€備緥濡傦紝濡傛灉浣犵殑搴旂敤琚儴缃插湪 https://www.ruoyi.vip/admin/锛屽垯璁剧疆 baseUrl 涓?/admin/銆?  publicPath: process.env.NODE_ENV === "production" ? "/" : "/",
  // 鍦╪pm run build 鎴?yarn build 鏃?锛岀敓鎴愭枃浠剁殑鐩綍鍚嶇О锛堣鍜宐aseUrl鐨勭敓浜х幆澧冭矾寰勪竴鑷达級锛堥粯璁ist锛?  outputDir: 'dist',
  // 鐢ㄤ簬鏀剧疆鐢熸垚鐨勯潤鎬佽祫婧?(js銆乧ss銆乮mg銆乫onts) 鐨勶紱锛堥」鐩墦鍖呬箣鍚庯紝闈欐€佽祫婧愪細鏀惧湪杩欎釜鏂囦欢澶逛笅锛?  assetsDir: 'static',
  // 鏄惁寮€鍚痚slint淇濆瓨妫€娴嬶紝鏈夋晥鍊硷細ture | false | 'error'
  lintOnSave: process.env.NODE_ENV === 'development',
  // 濡傛灉浣犱笉闇€瑕佺敓浜х幆澧冪殑 source map锛屽彲浠ュ皢鍏惰缃负 false 浠ュ姞閫熺敓浜х幆澧冩瀯寤恒€?  productionSourceMap: false,
  // webpack-dev-server 鐩稿叧閰嶇疆
  devServer: {
    host: '0.0.0.0',
    port: port,
    open: true,
    proxy: {
      // detail: https://cli.vuejs.org/config/#devserver-proxy
      [process.env.VUE_APP_BASE_API]:
      {
        target: `http://localhost:8090/`,
         //target: `http://120.24.146.68:8095/`,
        changeOrigin: true,
        pathRewrite: {
          ['^' + process.env.VUE_APP_BASE_API]: ''
        }
      }
    },
    allowedHosts: 'all'
  },
  configureWebpack: {
    name: name,
    resolve: {
      alias: {
        '@': resolve('src')
      },
      fallback: {
        path: require.resolve('path-browserify'),
        util: require.resolve('util/')
      }
    }
  },
  chainWebpack(config) {
    config.plugins.delete('preload') // TODO: need test
    config.plugins.delete('prefetch') // TODO: need test
    // set svg-sprite-loader
    config.module
      .rule('svg')
      .exclude.add(resolve('src/assets/icons'))
      .end()
    config.module
      .rule('icons')
      .test(/\.svg$/)
      .include.add(resolve('src/assets/icons'))
      .end()
      .use('svg-sprite-loader')
      .loader('svg-sprite-loader')
      .options({
        symbolId: 'icon-[name]'
      })
      .end()

    config
      .when(process.env.NODE_ENV !== 'development',
        config => {
          config
            .plugin('ScriptExtHtmlWebpackPlugin')
            .after('html')
            .use('script-ext-html-webpack-plugin', [{
            // `runtime` must same as runtimeChunk name. default is `runtime`
              inline: /runtime\..*\.js$/
            }])
            .end()
          config
            .optimization.splitChunks({
              chunks: 'all',
              cacheGroups: {
                libs: {
                  name: 'chunk-libs',
                  test: /[\\/]node_modules[\\/]/,
                  priority: 10,
                  chunks: 'initial' // only package third parties that are initially dependent
                },
                elementUI: {
                  name: 'chunk-elementUI', // split elementUI into a single package
                  priority: 20, // the weight needs to be larger than libs and app or it will be packaged into libs or app
                  test: /[\\/]node_modules[\\/]_?element-ui(.*)/ // in order to adapt to cnpm
                },
                commons: {
                  name: 'chunk-commons',
                  test: resolve('src/components'), // can customize your rules
                  minChunks: 3, //  minimum common number
                  priority: 5,
                  reuseExistingChunk: true
                }
              }
            })
          config.optimization.runtimeChunk('single')
        }
      )
  }
}


