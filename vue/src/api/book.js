import axios from "axios"
import base from "./base";

const book = {

    // 获取图书列表
    searchForBook(key,option){
        return axios.get(`${base.baseURL}/search/${key}/${option}`);
    },
    //获取图书 信息
    getBookInfo(bookId){
        return axios.get(`${base.baseURL}/info/book/${bookId}`);
    },
    //借书
    updateBorrow(userId,bookId){
        return axios.get(`${base.baseURL}/borrow/${userId}/${bookId}`);
    },
    //获取借阅记录
    getBorrowList(userId){
        return axios.get(`${base.baseURL}/info/borrow/${userId}`);
    }
}



export default book;