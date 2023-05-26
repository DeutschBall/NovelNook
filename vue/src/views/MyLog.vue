<template>
  <div class="book-list">
    <h1>Borrowing Records</h1>
    <table>
      <thead>
      <tr>
        <th>Book ID</th>
        <th>Book Name</th>
        <th>Location</th>
        <th>Borrowtime</th>
        <th>Deadline</th>
        <th>Status</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(book, index) in bookList" :key="index" :class="{ 'borrowing': book.status==='borrowing', 'returned': book.status==='returned' }">
        <!--界面跳转-->
        <td><router-link :to="'/'+$route.params.userid+'/'+book.bookid+'/bookinfo'" >
          {{ book.bookid }}
        </router-link></td>
        <td>{{ book.bookname }}</td>
        <td>{{ book.location }}</td>
        <td>{{ book.borrowtime }}</td>
        <td>{{ book.deadline }}</td>
        <td>{{ book.status}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import api from '@/api/api';

export default {
  data() {
    return {
      bookList: [],
      dialogVisible: false
    };
  },
  mounted() {
    api.book.getBorrowList(this.$route.params.userid).then(res =>{
      console.log(res.data);
      this.bookList = res.data;
    })
  },
  methods:{
    jump(userid){
      this.$router.push({
        path:"/"+userid+"/bookinfo"
      })
    }
  }
};
</script>

<style>
.book-list {
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

table {
  border-collapse: collapse;
  width: 100%;
}

th,
td {
  border: 1px solid #ddd;
  padding: 10px;
  text-align: left;
}

th {
  background-color: #eee;
  color: #333;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

.borrowing td {
  background-color: #c8e6c9;
}

.returned td {
  background-color: #ffcdd2;
}
</style>