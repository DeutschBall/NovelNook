<template>
  <div id="app">
    <!-- <Login></Login> -->
    <!-- <AdminStaff></AdminStaff> -->
    <!-- <router-view v-if="isRouterAlive"></router-view> -->
    <router-view></router-view>
  </div>
</template>

<script>
import AdminStaff from '@/views/AdminStaff'
import Login from '@/views/Login'
import Register from '@/views/Register'
export default {
  // name: 'App',
  // provide () {
  //   return {
  //     reload: this.reload
  //   };
  // },
  // methods: {
  //   reload () {
  //     this.isRouterAlive = false
  //     this.$nextTick(function () {
  //       this.isRouterAlive = true
  //     });
  //   }
  // },
  components: {
    AdminStaff,Login,Register
  }
}  
</script>
<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
