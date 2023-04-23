import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
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
    component: () => import(/* webpackChunkName: "about" */ '../views/SearchResult.vue')
  },
  {
    path: '/:userid/:bookid/bookinfo',
    name: 'bookinfo',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Bookinfo.vue')
  },
  {
    path: '/:userid/mylog',
    name: 'mylog',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/MyLog.vue')
  },
  {
    path: '/:userid/:bookid/borrowresult',
    name: 'borrowresult',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/BorrowResult')
  },
  {
    path: 'goHome',
    redirect: '/:userid/index'
  }
]

const router = new VueRouter(

    {
  mode: 'history',
  base: 'patron',
  routes
})

export default router
