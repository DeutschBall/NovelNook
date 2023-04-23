<template>
  <div class="book-details">
    <h1>{{ bookInfo.bookname }}</h1>
    <p><strong>Bookid:</strong> {{ bookInfo.bookid }}</p>
    <p><strong>Author:</strong> {{ bookInfo.author }}</p>
    <p><strong>Press:</strong> {{ bookInfo.press }}</p>
    <p><strong>Publishtime:</strong> {{ bookInfo.publishtime }}</p>
    <p><strong>Catagory:</strong> {{ bookInfo.catagory }}</p>
    <p><strong>Remain:</strong> {{ bookInfo.remain }}</p>
    <button class="borrow-button" @click="jump($route.params.userid,bookInfo.bookid)">Borrow Book</button>
    <hr>
    <p><strong>Introduction:</strong></p>
    <p>{{ bookInfo.introduction }}</p>
  </div>
</template>
<script>
import api from "@/api/api";

export default {
  data() {
    return {
      bookInfo: []
    };
  },
  mounted() {
    api.book.getBookInfo(this.$route.params.bookid).then(res =>{
      console.log(res.data);
      this.bookInfo = res.data[0];
      console.log(this.bookInfo);
    })
  },
  methods:{
    jump(userid,bookid){
      this.$router.push({
        path:"/"+userid+"/"+bookid+"/borrowresult"
      })
    }
  }
}
</script>
<style scoped>
.book-details {
  margin-right: 50px;
  margin-left: 50px;
  font-size: 18px;
  line-height: 1.5;
  text-align: justify;
}

.borrow-button {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  margin-bottom: 20px;
}

.borrow-button:hover {
  background-color: #3e8e41;
}

</style>