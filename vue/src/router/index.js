import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Login from '../views/Login.vue'
import SearchResult from '../views/SearchResult.vue'
import Bookinfo from '../views/Bookinfo.vue'
import MyLog from '../views/MyLog.vue'
import FineRecord from '../views/FineRecord.vue'
import ReservationRecord from '../views/ReservationRecord.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '',
    name: 'login',
    component: Login
  },
  {
    path: '/:userid/index',
    name: 'home',
    component: HomeView
  },
  {
    path: '/:userid/search',
    name: 'result',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: SearchResult
  },
  {
    path: '/:userid/:bookid/bookinfo',
    name: 'bookinfo',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: Bookinfo
  },
  {
    path: '/:userid/mylog',
    name: 'mylog',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: MyLog
  },
  {
    path: '/:userid/finerecord',
    name: 'finerecord',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: FineRecord
  },
  {
    path: '/:userid/reservation',
    name: 'reservation',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: ReservationRecord
  },
]

const router = new VueRouter(

    {
  mode: 'history',
  base: 'patron',
  routes
})

export default router
