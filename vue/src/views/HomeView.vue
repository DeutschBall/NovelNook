<template>
  <div class="container">
    <h1 class="title">BOOK SEARCH</h1>
    <form class="form">
      <input class="input" type="text" placeholder="Enter Book ID, Book Name or Author" v-model="key">
      <select class="select" v-model="option" @change="changeProduce($event)">
        <option value="bookid">Book ID</option>
        <option value="bookname">Book Name</option>
        <option value="author">Author</option>
      </select>
      <!--界面跳转-->
      <button class="button" @click="jump(key, option)">Search</button>
    </form>

    <p></p>
    <p></p>
    <p></p>
    <p></p>

    <el-row gutter="100" class="rowTitle">
      <el-col :span="8">
        <div>
          <el-statistic title="Number of borrowing book" :value=value1 ></el-statistic>
        </div>
      </el-col>
      <el-col :span="8">
        <div>
          <el-statistic :value=value2 title="Number of overdue book"></el-statistic>
        </div>
      </el-col>
      <el-col :span="8">
        <div>
          <el-statistic group-separator="," :precision="2" :value=value3  title="Expenses to be paid"></el-statistic>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import api from "@/api/api";
import BT from "./BorrowToast.vue";
import RT from "./ReserveToast.vue";
export default {
  computed: {
    style() {
      return style
    }
  },
  data() {
    return {
      key:"",
      option:"bookid",
      value1: 0,
      value2: 0,
      value3: 0
    };
  },
  mounted() {
    var curDate=new Date();
    api.book.getBorrowList(this.$route.params.userid).then(res =>{
        for(let i=0;i<res.data.length;i++){
            let ddl=new Date(res.data[i].deadline);
            let delta=(ddl.getTime()-curDate.getTime())/1000/60/60/24;
            console.log(delta);
            if(delta<7 && delta>=0){
                const myBT = {
                // Your component or JSX template
                component: BT,

                // Props are just regular props, but these won't be reactive
                props: {
                    userid: this.$route.params.userid,
                    },
                };
                this.$toast.warning(myBT);
                break;
            }
        }
    });
    api.book.noticeMessage(this.$route.params.userid).then(res =>{
        console.log(res);
        if(res.data.length!=0){
            const myRT = {
                // Your component or JSX template
                component: RT,

                // Props are just regular props, but these won't be reactive
                props: {
                    userid: this.$route.params.userid,
                    },
                };
                this.$toast.success(myRT);
        }
    });
    api.book.getBorrowCount(this.$route.params.userid).then(res =>{
      console.log(res.data);
      this.value1 = res.data;
    });
    api.book.getOverdueCount(this.$route.params.userid).then(res =>{
      console.log(res.data);
      this.value2 = res.data;
    });
    api.book.getFineAmount(this.$route.params.userid).then(res =>{
      console.log(res.data);
      this.value3 = res.data;
    });
  },
  methods:{
    changeProduce(event) {
      this.option = event.target.value
    },
    jump(key, option){
      this.$router.push({
        path: '/' + this.$route.params.userid + '/search',
        query: { key, option }
      })
    },
  }
}
</script>

<style>
.container {
  min-height: calc(100vh - 60px);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-image: url('../assets/library.jpg');
  background-size: cover;
  background-position: center;
}

.title {
  font-size: 4rem;
  color: white;
  margin-bottom: 3rem;
  text-shadow: 2px 2px #000;
}

.rowTitle .title{
  font-size: 30px!important;
  color: white;
}

.el-statistic .con .number{
  font-size: 3rem!important;
  color:white;
}

.form {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
}

.input {
  width: 300px;
  height: 50px;
  font-size: 2rem;
  margin-right: 1rem;
  padding: 0 1rem;
  border: none;
  border-radius: 0.25rem;
  box-shadow: 2px 2px #000;
}

.select {
  width: 300px;
  height: 50px;
  font-size: 2rem;
  margin-right: 1rem;
  padding: 0 1rem;
  border: none;
  border-radius: 0.25rem;
  box-shadow: 2px 2px #000;
  background-color: white;
}

.button {
  font-size: 2.5rem;
  padding: 0.5rem 2rem;
  border: none;
  border-radius: 0.25rem;
  background-color: #C70039;
  color: white;
  box-shadow: 2px 2px #000;
  cursor: pointer;
}

.button:hover {
  background-color: #FF5733 ;
}
</style>
