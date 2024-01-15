import Vue from 'vue'
import App from './App.vue'
import * as echarts from 'echarts'//在import的后面，echarts的前面加一个 * as
import router from './components/router'
import 'iview/dist/styles/iview.css'
import 'element-ui/lib/theme-chalk/index.css';
//import 'font-awesome/css/font-awesome.min.css'
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import httpRequest from "@/components/utils/httpRequest";
import {
Container,
  Aside,
  Header,
  Main,
  Footer,
  Table,
  TableColumn,
  Button,
  Pagination,
  Dialog,
  Form,
  FormItem,
  Input,
  Select,
  Option,
  RadioGroup,
  Message,
  InputNumber,
  Radio,
  Card,
  DatePicker,
  Switch,
  Upload,
  Avatar,
  DropdownMenu,
  DropdownItem,
  Dropdown,
  Empty,
    Collapse,
  CollapseItem,
    Tag,
  Loading

} from "element-ui";
import { Row, Col,Menu,Submenu,Icon,MenuItem,BreadcrumbItem,Breadcrumb,Layout,Content,Sider} from 'iview'
import axios from "axios";
Vue.config.productionTip = false

Vue.component("i-content",Content)
Vue.component('i-layout',Layout)
Vue.component('i-row',Row)
Vue.component('i-col',Col)
Vue.component('i-menu',Menu)
Vue.component('i-submenu',Submenu)
Vue.component('i-icon',Icon)
Vue.component('i-breadcrumb',Breadcrumb)
Vue.component('i-menu-item',MenuItem)
Vue.component('i-breadcrumb-item',BreadcrumbItem)
Vue.component('i-sider',Sider)

Vue.component('el-dropdown',Dropdown)
Vue.component('el-dropdown-item',DropdownItem)
Vue.component('el-dropdown-menu',DropdownMenu)
Vue.component('el-avatar',Avatar)
Vue.component('el-empty',Empty)
Vue.component('el-upload',Upload)
Vue.component('el-switch',Switch)
Vue.component('el-date-picker',DatePicker)
Vue.component('el-card',Card)
Vue.component('el-input-number',InputNumber)
Vue.component('el-radio-group',RadioGroup)
Vue.component('el-radio',Radio)
Vue.component('el-option',Option)
Vue.component('el-select',Select)
Vue.component('el-input',Input)
Vue.component('el-form-item',FormItem)
Vue.component('el-form',Form)
Vue.component('el-dialog',Dialog)
Vue.component('el-table',Table);
Vue.component('el-button',Button);
Vue.component('el-pagination',Pagination)
Vue.component('el-table-column',TableColumn)
Vue.component('el-container',Container);
Vue.component('el-aside',Aside);
Vue.component('el-header',Header);
Vue.component('el-main',Main);
Vue.component('el-footer',Footer);
Vue.component('el-collapse',Collapse);
Vue.component('el-collapse-item',CollapseItem)
Vue.component('el-tag',Tag)


Vue.use(VueQuillEditor)
Vue.use(Loading.directive);
Vue.prototype.$http = httpRequest // Ajax 请求方法
//vue全局注入echarts
Vue.prototype.$echarts = echarts;
Vue.prototype.$message = Message
Vue.prototype.$axios = axios//未封装的axios请求
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

//Vue.use(ElementUI)



