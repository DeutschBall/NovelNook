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
      <router-link to="/result" >
        <button class="button" @click="jump(key, option)">Search</button>
      </router-link>

    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      key:"",
      option:"bookid",
    };
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
    }
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
  background-image: url('https://images8.alphacoders.com/116/1164611.jpg');
  background-size: cover;
  background-position: center;
}

.title {
  font-size: 4rem;
  color: white;
  margin-bottom: 3rem;
  text-shadow: 2px 2px #000;
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
  index:
}

.button:hover {
  background-color: #FF5733 ;
}
</style>
