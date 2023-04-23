<template>
  <div class="borrow-result">
    <h1>Borrow result</h1>
    <p>{{result}}</p>
    <button class="return-button" @click="jumphome()">
        Return Home
    </button>
  </div>
</template>

<script>
import api from "@/api/api";
export default {
  data() {
    return {
      result:null
    };
  },
  mounted() {
    api.book.updateBorrow(this.$route.params.userid,this.$route.params.bookid).then(res =>{
      console.log(res.data);
      this.result = res.data;
    })
  },
  methods:{
    jumphome(){
      this.$router.push({
        path:"/"+ this.$route.params.userid + "/index"
      })
    }
  }
}
</script>

<style scoped>
.borrow-result {
  font-family: Arial, sans-serif;
  margin: 0 auto;
  padding: 20px;
}

h1 {
  margin-top: 30px;
  margin-bottom: 20px;
  font-size: 28px;
  font-weight: bold;
}
.return-button {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  margin-bottom: 20px;
}
</style>