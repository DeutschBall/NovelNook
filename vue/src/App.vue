<template>
  <div id="app">
    <div style="height: 60px;line-height: 60px;background-color: white; display: flex; justify-content: flex-start; align-items: center;">
      <img src="@/assets/logo.png" style="height: 60px">
      <el-dropdown @command="handleCommand" style="margin-left: auto;">
        <span class="el-dropdown-link" style="margin-left: 20px;">
            <span style="font-family: Arial;">{{this.userid===NULL ? 'Please login':'User Id: '+this.userid}}<i v-if="userid!==NULL" class="el-icon-arrow-down el-icon--right"></i></span>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item v-if="userid!==NULL" command>Logout</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <button class="view-records-btn" @click="jump(userid,'mylog')">View Borrowing Records</button>
      <button class="view-records-btn" @click="jump(userid,'reservation')">View Reservation Records</button>
      <button class="view-records-btn" @click="jump(userid,'finerecord')">View Fine Records</button>
      <button class="view-records-btn" @click="jump(userid,'index')">Home</button>
      <img src="@/assets/user.png" alt="" style="height: 60px; margin-left: 20px;">
    </div>
    <div >
      <router-view></router-view>
    </div>
  </div>



</template>

<style>
.view-records-btn {
  background-color: #008CBA;
  color: white;
  padding: 10px;
  border-radius: 5px;
  border: none;
  margin-left: 20px;
  cursor: pointer;
}
</style>

<script>

export default {
  data(){
    return {
      userid: null
    }
  },
  mounted() {
    this.$bus.$on('userid', value=>{
      console.log(value);
      this.userid = value
    });
    this.$bus.$on('logout', value=>{
      console.log(value);
      this.userid = value
    });
    this.userid = this.$route.params.userid
  },
  beforeCreate() {
    this.$bus.$off('userid');
    this.$bus.$off('logout');
  },

  methods: {
    jump(userid, to) {
      this.$router.push({
        path: "/" + userid + "/" + to
      })
    },
    //when you click log out, this method will be active
    handleCommand() {
      this.$toast.info("Please log in again.", {
        position: "top-center",
        timeout: 1200,
        closeOnClick: true,
        pauseOnFocusLoss: false,
        pauseOnHover: false,
        draggable: false,
        draggablePercent: 0.6,
        showCloseButtonOnHover: false,
        hideProgressBar: true,
        closeButton: "button",
        icon: true,
        rtl: false
      });
      this.logout()
    },
    logout() {
      this.$bus.$emit('logout')
      this.$router.push({path:'/'})
    }
  }

}
</script>
