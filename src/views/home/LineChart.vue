<template>
  <div :class="className" :style="{height:height}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'
import { getRevenueCurveData } from "@/api/strategy/revenueCurve";

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '500px'
    },
    autoResize: {
      type: Boolean,
      default: true
    },
    chartData: {
      type: Object,
      required: true
    }
  },
  props: {
    accountId: { type: Number, default: null }
  },
  data() {
    return {
      minNum:0,
      chart: null,
      datas: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
      expectedDatas : [1, 1.3, 3, 2, 0.5, 100, 3],
    }
  },
  watch: {
    chartData: {
      deep: true,
      handler(val) {
        this.setOptions(val)
      }
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  created() {
    if (!this.accountId) {
      if(this.$route.path=="/"){
        this.accountId=8;
      }else{
        const str = this.$route.path.split("/");
        this.accountId=str[str.length-1];
      }
    }
    // const str = this.$route.path.split("/");
    // this.accountId=str[str.length-1];
    this.getListDate();
  },
  methods: {
    /** 查询策略仓位列表 */
    getListDate() {
      getRevenueCurveData(this.accountId).then(response => {
        this.datas = response.data.datas;
        this.expectedDatas = response.data.data;
        if(this.expectedDatas.length>0){
          var min  = Math.min.apply(null,this.expectedDatas);
          this.minNum = parseFloat(min).toFixed(1);
          console.log("========"+this.minNum);
        }
        // this.expectedDatas.forEach(item => {
        //   console.log("========"+item);
        // })
        this.setOptions(this.chartData)
      });
    },
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')
      // this.setOptions(this.chartData)
    },
    setOptions({ expectedData, actualData } = {}) {
      if (!this.chart) return
      this.chart.setOption({
        xAxis: {
          data: this.datas,
          boundaryGap: false,
          axisTick: {
            show: false
          },

        },
        grid: {
          left: 10,
          right: 40,
          bottom: 50,
          top: 30,
          containLabel: true
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          },
          padding: [5, 10]
        },
        yAxis: {
          // axisTick: {
          //   show: false
          // },
          type:'value',
          min:this.minNum,
          minInterval:0.02,

          // axisLabel : {

            // formatter: function(value){
            //   if (value>0.8)
            //     return value;
            //   else return '';
            // }
          // },
        },
        // legend: {
        //   data: ['expected', 'actual']
        // },
        dataZoom:[
          {
            type:"slider",
            show:true,
            realtime:true,
            height:30,
            bottom:10,
            start:10,
            end:90
          },
          {
            type:"inside",
            start:10,
            end:90
          }
        ],
        series: [{
          name: 'income', itemStyle: {
            normal: {
              color: '#FF005A',
              lineStyle: {
                color: '#FF005A',
                width: 2
              }
            }
          },
          smooth: true,
          type: 'line',
          data: this.expectedDatas,
          animationDuration: 2800,
          animationEasing: 'cubicInOut'
        },
        // {
        //   name: 'actual',
        //   smooth: true,
        //   type: 'line',
        //   itemStyle: {
        //     normal: {
        //       color: '#3888fa',
        //       lineStyle: {
        //         color: '#3888fa',
        //         width: 2
        //       },
        //       areaStyle: {
        //         color: '#f3f8ff'
        //       }
        //     }
        //   },
        //   data: actualData,
        //   animationDuration: 2800,
        //   animationEasing: 'quadraticOut'
        // }
        ]
      })
    },

  }
}
</script>
