import axios from "axios"
import base from "./base";

const alipay = {
    pay(borrowId, amount) {
        return axios.get(`${base.aliURL}/pay?subject=Fine&traceNo=${borrowId}&totalAmount=${amount}`);
    }
}

export default alipay;