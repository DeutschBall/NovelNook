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
    component: HomeView,
    meta: {
        requireAuthorization: true,
    },
  },
  {
    path: '/:userid/search',
    name: 'result',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: SearchResult,
    meta: {
        requireAuthorization: true,
    },
  },
  {
    path: '/:userid/:bookid/bookinfo',
    name: 'bookinfo',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: Bookinfo,
    meta: {
        requireAuthorization: true,
    },
  },
  {
    path: '/:userid/mylog',
    name: 'mylog',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: MyLog,
    meta: {
        requireAuthorization: true,
    },
  },
  {
    path: '/:userid/finerecord',
    name: 'finerecord',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: FineRecord,
    meta: {
        requireAuthorization: true,
    },
  },
  {
    path: '/:userid/reservation',
    name: 'reservation',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: ReservationRecord,
    meta: {
        requireAuthorization: true,
    },
  },
]

const router = new VueRouter(

    {
  mode: 'history',
  base: 'patron',
  routes
})

router.beforeEach((to, from, next) => {
    if (to.meta.requireAuthorization) { 
        if (sessionStorage.getItem("loginID")==to.params.userid) {  
            next();
        }
		else {
            next({
                path: '/',
            })
            alert("Access Denied!");
        }
    }
    else {
        next();
    }
});

export default router
