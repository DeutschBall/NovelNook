<template>
    <div>
      <h3>{{ this.satisfiedCount }} {{ this.satisfiedCount>1 ? 'books you reserved are available:':'book you reserved is available:' }}</h3>
      <ul>
          <li v-for="(book, index) in this.satisfiedList" :key="index">
              {{ book }}
          </li>
      </ul>
    </div>
  </template>
  
  <script>
  import api from "@/api/api";
  export default {
    data() {
      return {
        satisfiedList:[],
        satisfiedCount: 0,
      };
    },
    props: {
      userid: String,
    },
    mounted(){
        api.book.noticeMessage(this.userid).then(res =>{
          this.satisfiedList = res.data;
          this.satisfiedCount = res.data.length;
      });
    },
  }
  </script>
  
  