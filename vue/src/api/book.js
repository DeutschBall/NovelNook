import axios from "axios"
import base from "./base";

const book = {

    // 获取图书列表
    searchForBook(key, option) {
        return axios.get(`${base.baseURL}/search/${key}/${option}`);
    },
    //获取图书信息
    getBookInfo(bookId) {
        return axios.get(`${base.baseURL}/info/book/${bookId}`);
    },
    //借书
    updateBorrow(userId, bookId) {
        return axios.get(`${base.baseURL}/borrow/${userId}/${bookId}`);
    },
    //获取借阅记录
    getBorrowList(userId) {
        return axios.get(`${base.baseURL}/info/borrow/${userId}`);
    },

    /*---------------------------------------------------------------------------*/

    //
    //release 2
    //

    //登录
    login(userId, pwd) {
        return axios.get(`${base.baseURL}/login/${userId}/${pwd}`);
    },

    //获取借书数量
    getBorrowCount(userId) {
        return axios.get(`${base.baseURL}/count/borrow/${userId}`);
    },

    //获取逾期图书数量
    getOverdueCount(userId) {
        return axios.get(`${base.baseURL}/count/overdue/${userId}`);
    },

    //获取罚单列表
    getTicketList(userId) {
        return axios.get(`${base.baseURL}/info/ticket/${userId}`);
    },

    //获取罚款金额
    getFineAmount(userId) {
        return axios.get(`${base.baseURL}/count/ticket/${userId}`);
    },

    //预约图书
    reserveBook(userId, bookId) {
        return axios.get(`${base.baseURL}/reserve/${userId}/${bookId}`);
    },

    //获取预约列表
    getReservationList(userId) {
        return axios.get(`${base.baseURL}/info/reserve/${userId}`);
    },

    //更新并检查预约状态
    noticeMessage(userId) {
        return axios.get(`${base.baseURL}/notice/${userId}`);
    },

    //取消预约
    cancelReservation(userId, bookId) {
        return axios.get(`${base.baseURL}/cancel/${userId}/${bookId}`);
    },

    /*---------------------------------------------------------------------------*/

    //
    //release 3
    //

    //获取旧密码
    getOldPassword(userId) {
        return axios.get(`${base.baseURL}/getoldpwd/${userId}`)
    },

    //修改密码
    updatePassword(userId, newPassword) {
        return axios.get(`${base.baseURL}/updatepwd/${userId}/${newPassword}`)
    }
}

export default book;