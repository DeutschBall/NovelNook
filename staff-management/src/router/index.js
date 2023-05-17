import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
 
  {
    path: '/admin/staff',
    name: 'admin-staff',
    component: () => import('../views/AdminStaff.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
