<template>
  <div id="app">
    <div style="height: 60px;line-height: 60px;background-color: white; display: flex; justify-content: flex-start; align-items: center;">
      <img src="@/assets/logo.png" style="height: 60px">
      <span style="margin-left: auto; font-family: Arial;">User ID: {{ userid }}</span>
      <button class="view-records-btn" @click="jump(userid)">View Borrowing Records</button>
      <router-link :to="'/'+userid+'/index'" >
        <button class="view-records-btn">Home</button>
      </router-link>
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
    return { userid: "007" }
  },
  methods:{
    jump(userid){
      this.$router.push({
        path:"/"+userid+"/mylog"
      })
    }
  }
}
</script>
