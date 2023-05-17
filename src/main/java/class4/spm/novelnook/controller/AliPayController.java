package class4.spm.novelnook.controller;

import class4.spm.novelnook.common.AliPayConfig;
import class4.spm.novelnook.pojo.AliPay;
import class4.spm.novelnook.service.StaffServiceImpl;
import cn.hutool.json.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

// xjlugv6874@sandbox.com
// 9428521.24 - 30 = 9428491.24 + 30 = 9428521.24
@RestController
@RequestMapping("/alipay")
public class AliPayController {
    private HttpServletResponse httpResponse;

    private static final String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";
    private static final String FORMAT = "JSON";
    private static final String CHARSET = "UTF-8";
    //签名方式
    private static final String SIGN_TYPE = "RSA2";

    @Autowired
    AliPayConfig aliPayConfig;

    @Autowired
    StaffServiceImpl staffServiceImpl;



    @GetMapping("/pay") // &subject=xxx&traceNo=xxx&totalAmount=xxx
    @ResponseBody
    public String pay(AliPay aliPay) throws Exception {
        // 1. 创建Client，通用SDK提供的Client，负责调用支付宝的API

        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, aliPayConfig.getAppId(),
                aliPayConfig.getAppPrivateKey(), FORMAT, CHARSET, aliPayConfig.getAlipayPublicKey(), SIGN_TYPE);

        // 2. 创建 Request并设置Request参数
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();  // 发送请求的 Request类
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        JSONObject bizContent = new JSONObject();
        bizContent.set("out_trade_no", aliPay.getTraceNo());  // 我们自己生成的订单编号
        bizContent.set("total_amount", aliPay.getTotalAmount()); // 订单的总金额
        bizContent.set("subject", aliPay.getSubject());   // 支付的名称
        bizContent.set("product_code", "FAST_INSTANT_TRADE_PAY");  // 固定配置
        request.setBizContent(bizContent.toString());

        // 执行请求，拿到响应的结果，返回给浏览器
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return form;
    }

    @PostMapping("/notify")
    public String payNotify(@RequestParam("trade_status") String tradeStatus,
                            @RequestParam("out_trade_no") String outTradeNo,
                            @RequestParam("gmt_payment") String gmtPayment,
                            @RequestParam("trade_no") String alipayTradeNo,
                            @RequestParam("sign") String sign,
                            @RequestParam Map<String, String> params) throws Exception {

        if (tradeStatus.equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            String content = AlipaySignature.getSignCheckContentV1(params);
            boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign, aliPayConfig.getAlipayPublicKey(), "UTF-8"); // 验证签名
            // 支付宝验签
            if (checkSignature) {
                // 验签通过   应该是付款成功的意思

                //这一堆在后台里打印相关信息，也说明了各参数的含义， 目前只用到 outTradeNo 对应我们的 borrowid
//                System.out.println("交易名称: " + params.get("subject"));
//                System.out.println("交易状态: " + tradeStatus);
//                System.out.println("支付宝交易凭证号: " + alipayTradeNo);
//                System.out.println("商户订单号: " + outTradeNo);
//                System.out.println("交易金额: " + params.get("total_amount"));
//                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
//                System.out.println("买家付款时间: " + gmtPayment);
//                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));

//==========================================可以自定义的业务逻辑===================================================//

                //改returned表，如果有其他成功过后的反馈，写在这里就可以
                //outTradeNo对应returned表的borrowid
                int result = staffServiceImpl.UpdateIspay(outTradeNo);



//==========================================可以自定义的业务逻辑===================================================//

            }
        }

        //不需要我们主动访问 /alipay/notify，所以也不需要在乎这个方法的返回
        return "success";
    }
}