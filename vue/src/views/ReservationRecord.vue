<template>
  <div class="book-list">
    <h1>Reservation Records</h1>


    <table>
      <thead>
      <tr>
        <th>Reservation ID</th>
        <th>Book ID</th>
        <th>Book Name</th>
        <th>Location</th>
        <th>Reservationtime</th>
        <th>Status</th>
        <th>Cancel</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(book, index) in reservationList" :key="index" :class="{ 'borrowing': book.status==='borrowing', 'returned': book.status==='returned' }">
        <!--界面跳转-->
        <td><router-link :to="'/'+$route.params.userid+'/'+book.bookid+'/bookinfo'" >
          {{book.reservationid}}
        </router-link></td>
        <td>{{ book.bookid }}</td>
        <td>{{ book.bookname }}</td>
        <td>{{ book.location }}</td>
        <td>{{ book.reservationtime }}</td>
        <td>{{ book.status}}</td>
        <td><button v-if="book.status ==='satisfied' || book.status ==='waiting'" class="pay-button" @click="cancel(book.bookid)">Cancel</button></td>
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
      reservationList: [],
      dialogVisible: false
    };
  },
  mounted() {
    api.book.getReservationList(this.$route.params.userid).then(res =>{
      console.log(res.data);
      this.reservationList = res.data;
    })
  },
  methods:{
    jump(userid){
      this.$router.push({
        path:"/"+userid+"/bookinfo"
      })
    },
    cancel(bookid){
      api.book.cancelReservation(this.$route.params.userid,bookid)
      window.location.reload()
    },
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