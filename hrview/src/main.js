import Vue from 'vue'
import App from './App.vue'
import router from './components/router'
import 'iview/dist/styles/iview.css'
import { Container,Aside,Header,Main,Footer} from "element-ui";
import { Row, Col,Menu,Submenu,Icon,MenuItem,BreadcrumbItem,Breadcrumb} from 'iview'
Vue.config.productionTip = false

Vue.component('i-row',Row)
Vue.component('i-col',Col)
Vue.component('i-menu',Menu)
Vue.component('i-submenu',Submenu)
Vue.component('i-icon',Icon)
Vue.component('i-breadcrumb',Breadcrumb)
Vue.component('i-menu-item',MenuItem)
Vue.component('i-breadcrumb-item',BreadcrumbItem)



Vue.component('el-container',Container);
Vue.component('el-aside',Aside);
Vue.component('el-header',Header);
Vue.component('el-main',Main);
Vue.component('el-footer',Footer);

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
