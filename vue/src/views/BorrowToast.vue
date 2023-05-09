<template>
  <div>
    <h4>Soon Overdue</h4>
    <ul>
        <li v-for="(book, index) in soonList" :key="index">
            {{ book.bookname }}: {{ book.deadline }}
        </li>
    </ul>
  </div>
</template>

<script>
import api from "@/api/api";
export default {
  data() {
    return {
      bookList:[],
      soonList:[],
      overdueList:[]
    };
  },
  props: {
    userid: String,
  },
  mounted(){
    var curDate=new Date();
    api.book.getBorrowList(this.userid).then(res =>{
        this.bookList = res.data;

        for(let i=0;i<res.data.length;i++){
            let ddl=new Date(res.data[i].deadline);
            let delta=(ddl.getTime()-curDate.getTime())/1000/60/60/24;
            console.log(delta);
            if(delta<7 && delta>=0){
                this.soonList.push(res.data[i]);
            }
        }
    });
  },
}
</script>

