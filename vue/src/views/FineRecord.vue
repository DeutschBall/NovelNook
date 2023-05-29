<template>
    <div class="Fine Page">
        <h1>Fine Records</h1>
        <table>
            <thead>
            <tr>
                <th>Borrow ID</th>
                <th>Return time</th>
                <th>Fine Amount</th>
                <th>IsPay</th>
                <th>Pay</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(returned, index) in returnlist" :key="index" :class="{ 'no': returned.ispay==='false', 'yes': returned.ispay==='true' }">

                <td>{{returned.borrowid}}</td>
                <td>{{returned.returntime}}</td>
                <td>{{returned.fineamount}}</td>
                <td>{{returned.ispay}}</td>
                <td><button v-if="!returned.ispay" class="pay-button" @click="jump(returned.borrowid, returned.fineamount)">Pay</button></td>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import api from '@/api/api';

export default {
    data(){
        return {
            returnlist: []
        };
    },
    mounted() {
        api.book.getTicketList(this.$route.params.userid).then(res =>{
            console.log(res.data);
            this.returnlist = res.data;
        })
    },
    methods: {
      pay(borrowId ,amount) {
        api.alipay.pay(borrowId, amount).then(res=> {
          console.log(res.data);
        })
      },
      jump(borrowId, amount) {
        window.open("http://localhost:8080/alipay/pay?subject=Fine&traceNo="+borrowId+"&totalAmount="+amount, "_blank");
      }
    }
}
</script>

<style>
Fine Page {
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

.yes td {
    background-color: #c8e6c9;
}

.no td {
    background-color: #ffcdd2;
}

.pay-button {
  background-color: #4CAF50;
  color: white;
  padding: 8px 15px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  margin-bottom: 20px;
}

</style>