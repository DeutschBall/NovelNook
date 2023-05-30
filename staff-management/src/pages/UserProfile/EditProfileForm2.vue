<template>
  <form>
    <!--    <user-card  v-if="imagePath" :cardUserImage="imagePath"></user-card>-->
    <md-card>
      <md-card-header :data-background-color="dataBackgroundColor">
        <h4 class="title">Edit Profile</h4>
        <p class="category">Complete your profile</p>
      </md-card-header>

      <md-card class="md-card-profile">
        <div class="md-card-avatar">
          <img class="img" :src="baseUrl + formLabelAlign.cardUserImage" />
        </div>
        <input type="file" ref="file" @change="showimg" hidden />
        <md-button class="md-round md-success" @click="changeimg"
          >Change Avatar</md-button
        >
      </md-card>
      <md-card-content>
        <div class="md-layout">
          <!--          <div class="md-layout-item md-small-size-100 md-size-33">-->
          <!--            <md-field>-->
          <!--              <label>Company (disabled)</label>-->
          <!--              <md-input v-model="disabled" disabled></md-input>-->
          <!--            </md-field>-->
          <!--          </div>-->
<!--          <div class="md-layout-item md-small-size-100 md-size-33">-->
<!--            <md-field>-->
<!--              <label>User Name</label>-->
<!--              <md-input-->
<!--                v-model="formLabelAlign.username"-->
<!--                type="text"-->
<!--              ></md-input>-->
<!--            </md-field>-->
<!--          </div>-->
          <div class="md-layout-item md-small-size-100 md-size-33">
            <md-field>
              <label>PassWord</label>
              <md-input
                v-model="formLabelAlign.password"
                type="text"
              ></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>First Name</label>
              <md-input
                v-model="formLabelAlign.firstname"
                type="text"
              ></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Last Name</label>
              <md-input
                v-model="formLabelAlign.lastname"
                type="text"
              ></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-100">
            <md-field>
              <label>Email Address</label>
              <md-input
                v-model="formLabelAlign.email"
                type="email"
              ></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-33">
            <md-field>
              <label>telephone</label>
              <md-input
                v-model="formLabelAlign.telephone"
                type="text"
              ></md-input>
            </md-field>
          </div>

          <div class="md-layout-item md-size-100 text-right">
            <md-button class="md-info">
              <router-link :to="{ name: 'Dashboard' }"> back </router-link>
            </md-button>
            <md-button class="md-raised md-success" @click="ChangeStaff">
<!--              <router-link :to="{ name: 'Dashboard' }"> </router-link>-->
              Finish

            </md-button>
          </div>
        </div>
      </md-card-content>
    </md-card>
  </form>
</template>
<script>
// import UserCard from "@/pages/UserProfile/UserCard";
import {
  StatsCard,
  ChartCard,
  NavTabsCard,
  NavTabsTable,
  OrderedTable,
} from "@/components";
import axios from "axios";
export default {
  name: "edit-profile-form",
  props: {
    dataBackgroundColor: {
      type: String,
      default: "green",
    },
  },
  data() {
    return {
      hasMountedBeenCalled: false,
      baseUrl: "http://localhost:8080/",
      formLabelAlign: {
        userid: "",
        password: "",
        // disabled: null,
        email: "",
        lastname: "",
        firstname: "",
        telephone: "",
        cardUserImage: "",
        // require("@/assets/img/faces/marc.jpg")
      },
    };
  },
      // address:'',
      // city: '',
      // country: '',
      // code: '',
      // aboutme:
      //   "Lamborghini Mercy, Your chick she so thirsty, I'm in that two seat Lambo.",

  // computed:{
  //   imagePath() {
  //     return import(`${this.formLabelAlign.cardUserImage}`);
  //   }
  // },
  mounted() {
    this.formLabelAlign.password=this.$route.params.password;
    this.formLabelAlign.email=this.$route.params.email;
    this.formLabelAlign.lastname=this.$route.params.lastname;
    this.formLabelAlign.firstname=this.$route.params.firstname;
    this.formLabelAlign.telephone=this.$route.params.telephone;
    this.formLabelAlign.userid=this.$route.params.userid;
    if (!this.hasMountedBeenCalled) {
      // 在这里放置您希望只执行一次的代码
      this.formLabelAlign.cardUserImage=this.$route.params.uml;
      this.hasMountedBeenCalled = true;
    }

  },
  methods: {
    // getImgUrll(src) {
    //   // src的值是你绝对路径下图片的地址,此地址需省略开头的 @/
    //   // 方法一：此处的src是：assets/comic/吾猫当仙.jpg
    //   return require("@/" + src);
    // },
    changeimg() {
      this.$refs.file.dispatchEvent(new MouseEvent("click"));
    },

    showimg() {
      const that = this;
      var fr = new FileReader();
      fr.readAsDataURL(that.$refs.file.files[0]);
      fr.onload = function () {
        that.baseUrl="";
        that.formLabelAlign.cardUserImage = fr.result;
      };

    },

    ChangeStaff() {
      let that = this;
      const file = this.$refs.file.files[0];
      // const file=null;
      // const param = {
      //   avatar: this.formLabelAlign.Avatar,
      //   password: this.formLabelAlign.password,
      //   firstname: this.formLabelAlign.firstname,
      //   lastname: this.formLabelAlign.lastname,
      //   telephone: this.formLabelAlign.telephone,
      //   email: this.formLabelAlign.email,
      // };
      // console.log(file)
      const param = new FormData();
      param.append("file", file);
      // param.append("username", this.formLabelAlign.username);
      param.append("userid", this.formLabelAlign.userid);
      param.append("password", this.formLabelAlign.password);
      param.append("firstname", this.formLabelAlign.firstname);
      param.append("lastname", this.formLabelAlign.lastname);
      param.append("telephone", this.formLabelAlign.telephone);
      param.append("email", this.formLabelAlign.email);
      // const config = {
      //   headers: {
      //     'Content-Type': 'multipart/form-data',
      //     token: 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiMSIsImV4cCI6MTY0NjQ2MjA3NSwiaWF0IjoxNjQ1ODU3Mjc1LCJ1c2VySWQiOiIxIn0.CYQFivU7lcs2aoH-T7QtNvVWbJO8qvM2IqeOX7wPWok'
      //   }
      // }
      axios
        .put("http://localhost:8080/admin/staff",param)
        .then((res) => {
          // console.log(res)
          if (res.data.code === 1) {
            that.$message.success(res.data.object);
            this.$router.push({ name: 'Dashboard' });
            that.formLabelAlign = {};
          }
        })
        .catch((err) => {
          // console.log(err);
        });
    },
  },
};
</script>
<style></style>
