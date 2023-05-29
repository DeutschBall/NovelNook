<template>
  <div class="book-list">
    <h1>Search Results</h1>
    <table>
      <thead>
      <tr>
        <th>Book ID</th>
        <th>Book Name</th>
        <th>Author</th>
        <th>Press</th>
        <th>Publictime</th>
        <th>Catagory</th>
        <th>Location</th>
        <th>Remain</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(book, index) in bookList" :key="index" :class="{ 'available': book.remain, 'not-available': !book.remain }">
        <!--界面跳转-->
        <td><router-link :to="'/'+$route.params.userid+'/'+book.bookid+'/bookinfo'" >
          {{book.bookid}}
        </router-link></td>
        <td>{{ book.bookname}}</td>
        <td>{{ book.author }}</td>
        <td>{{ book.press}}</td>
        <td>{{ book.publishtime}}</td>
        <td>{{ book.catagory}}</td>
        <td>{{ book.location}}</td>
        <td>{{ book.remain}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>

import api from "@/api/api";

export default {
  data() {
    return {
      bookList: []
    };
  },
  mounted() {
    api.book.searchForBook(this.$route.query.key, this.$route.query.option).then(res =>{
      console.log(res.data);
      this.bookList = res.data;
    })
  },
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

.available td {
  background-color: #c8e6c9;
}

.not-available td {
  background-color: #ffcdd2;
}
</style>