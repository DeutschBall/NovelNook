import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)


//添加以下代码
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: '/login'
  },
 
  {
    path: '/admin/staff',
    name: 'admin-staff',
    component: () => import('@/views/AdminStaff.vue')
  },
  {
    path:'/login',
    name:'login',
    component: () => import('@/views/Login.vue')
  },
  {
    path:'/register',
    name:'register',
    component: () => import('@/views/Register.vue')
  },
]

const router = new VueRouter({
  routes
})

export default router
