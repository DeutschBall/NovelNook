<template>
  <div class="loginbBody">
    <div class="loginDiv">
      <div class="login-content">
        <h1 class="login-title">Login</h1>
        <el-form :model="loginForm" label-width="100px"
                 :rules="rules" ref="loginForm">
          <el-form-item label="UserID" prop="name">
            <el-input style="width: 200px" type="text" v-model="loginForm.name"
                      autocomplete="off" size="small"></el-input>
          </el-form-item>
          <el-form-item label="Password" prop="password">
            <el-input style="width: 200px" type="password" v-model="loginForm.password"
                      show-password autocomplete="off" size="small"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="confirm">Log In</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import api from "@/api/api";

export default {
  name: "login",
  data(){
    return{
      loginForm:{
        name:'',
        password:''
      },
      rules:{

      },
      userid:''
    }
  },
  methods:{
    jump(userid){
      this.$router.push({
        path:"/"+userid+"/index"
      })
    },
    confirm(){
      api.book.login(this.loginForm.name, this.loginForm.password).then(res => {
        console.log(res.data);
        if(res.data === "Password is wrong") {
          this.$toast.error(res.data,{
            position: 'top-center',
            timeout: 800,
            hideProgressBar: true,
            closeButton: false,
          });
        }
        else {
          this.userid = res.data
          sessionStorage.setItem("loginID",this.loginForm.name);
          this.$bus.$emit('userid', res.data)
          this.jump(res.data);
        }
      })
    }
  }
}
</script>

<style scoped >
.loginbBody {
  width: 100%;
  height: 100%;
  background-color: #B3C0D1;
}
.loginDiv {
  position: absolute;
  top: 50%;
  left: 50%;
  margin-top: -200px;
  margin-left: -250px;
  width: 450px;
  height: 330px;
  background: #fff;
  border-radius: 5%;

}
.login-title {
  margin: 20px 0;
  text-align: center;
}
.login-content {
  width: 400px;
  height: 250px;
  position: absolute;
  top: 25px;
  left: 25px;
}
</style>