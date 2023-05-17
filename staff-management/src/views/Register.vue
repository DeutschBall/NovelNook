<template>
    <el-row type="flex" justify="center">
      <el-form ref="formData" :model="formData" :rules="rules" label-width="80px" @keyup.enter.native="register()">
        <el-form-item prop="userName" label="UserName"><el-input v-model="formData.userName" placeholder="Please input your username" prefix-icon="icon-login_user" clearable></el-input></el-form-item>
        <el-form-item prop="password" label="PassWord"><el-input v-model="formData.password" placeholder="Please input your password" type="password" prefix-icon="icon-login_pwd" clearable></el-input></el-form-item>
        <el-form-item prop="cheackPassword" label="Confirm Password"><el-input v-model="formData.cheackPassword" placeholder="Input your password again" type="password" prefix-icon="icon-login_pwd" clearable></el-input></el-form-item>
        
        <el-form-item>
            <el-button type="primary" @click="register('formData')" icon="el-icon-upload">Register</el-button>
            <el-button @click="resetForm('formData')">Reset</el-button></el-form-item>
         <router-link to="login">If you have an account, Please Log In</router-link>
 
      </el-form>
    </el-row>
</template>
<script>
export default {
  data() {
      var validatePass = (rule, value, callback) => {
			if (value === '') {
				callback(new Error('Please input your password again'));
			} else if (value !== this.formData.password) {
				callback(new Error('You input different password!'));
			} else {
				callback();
			}
		};
 
    return {
      formData: {
        userName: '',
        password: '',
        cheakPassword:''
      },
      rules: {
        userName: [{ required: true, message: 'Username can not be empty!', trigger: 'blur' }],
        password: [{ required: true, message: 'Password can not be empty!', trigger: 'blur' }],
        cheakPassword: [{ required: true, validator: validatePass, trigger: 'blur' }]
 
      }
    };
  },
  methods: {
    register(formName) {
      this.$refs[formName].validate(valid => {
				if (valid) {
					this.$message({
						type: 'success',
						message: 'Register succseefully'
                    });
                     this.$router.push({name:'login'});
				} else {
					console.log('error submit!!');
					return false;
				}
			});
    },
    resetForm(formName) {
			this.$refs[formName].resetFields();
		}
 
  }
};
</script>