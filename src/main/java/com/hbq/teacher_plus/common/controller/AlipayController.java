package com.hbq.teacher_plus.common.controller;


import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.hbq.teacher_plus.common.model.AlipayVo;
import com.hbq.teacher_plus.config.AlipayConfig;
import com.hbq.teacher_plus.util.AlipayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author hbq
 * @createTime 2020/3/19 23:29
 */
@Slf4j
@RestController("/alipay")
public class AlipayController {

    /**
     * 支付宝完成回调页面(不可信回调)
     */
    @GetMapping("/return")
    @ResponseBody
    private String alipayReturn(HttpServletRequest request) {

        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> handleParams = AlipayUtil.handleParams(parameterMap);

        // 这里的校验没有多大的意思,不可信,直接获取out_trade_no跳转到对应的payed controller也可
        boolean rsaCheck = AlipayUtil.rsaCheck(handleParams);
        if (rsaCheck){
            System.out.println("验证通过");
        }else {
            System.out.println("验证失败");
        }

        // 获取订单号
        String out_trade_no = handleParams.get("out_trade_no");
        System.out.println("out_trade_no:" + out_trade_no);
        // 这里一般都是 重定向 payed的controller, 然后携带对应的信息如:return "redirect:/alipay/success?out_trade_no=" + out_trade_no;
        // payed的controller根据out_trade_no获取支付结果,并且给出页面提示

        return "支付完成";
    }


    /**
     * 支付宝完成结果异步的回调(可信回调)
     * @param request
     */
    @PostMapping("/notify")
    @ResponseBody
    private String alipayNotify(HttpServletRequest request) {

        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> handleParams = AlipayUtil.handleParams(parameterMap);

        boolean rsaCheck = AlipayUtil.rsaCheck(handleParams);
        if (rsaCheck){
            System.out.println("验证通过");

            // 处理业务逻辑,更改支付状态等骚操作
            // ...
        }else {
            System.out.println("验证失败");
        }
        return rsaCheck ? "success" : "failure";
    }
    @RequestMapping("/pay/phone")
    public String pay() {

        AlipayVo alipayVo = new AlipayVo();
        // String out_trade_no = UUID.randomUUID().toString().replace("-", "");
        String out_trade_no = AlipayUtil.get().nextId() + "";
        System.out.println("out_trade_no:" + out_trade_no);
        // 设置订单单号,需要保证唯一性
        alipayVo.setOut_trade_no(out_trade_no);
        // 设置支付金额
        alipayVo.setTotal_amount("0.01");
        // 设置支付标题
        alipayVo.setSubject("houyu-test-title");
        // 设置订单有效时长(30分钟)
        alipayVo.setTimeout_express("30m");
        // 商品码(必须是QUICK_WAP_WAY),可以看文档 see: https://docs.open.alipay.com/203/107090/
        alipayVo.setProduct_code("QUICK_WAP_WAY");

        // 对象转为json字符串
        String json = JSONObject.toJSONString(alipayVo);

        // 建立连接
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);

        // 创建请求
        AlipayTradeWapPayRequest alipayTradeWapPayRequest = new AlipayTradeWapPayRequest();

        // 设置异步通知地址
        alipayTradeWapPayRequest.setNotifyUrl(AlipayConfig.notify_url);
        // 设置对调地址,就是说支付成功之后回调你的页面,你可以继续进行你的业务操作,但是这个是不可信任的,需要根据notify_url这边的回调确定支付是否成功
        alipayTradeWapPayRequest.setReturnUrl(AlipayConfig.return_url);

        // 封装请求支付信息
        alipayTradeWapPayRequest.setBizContent(json);

        String pageString;
        try {
            pageString = client.pageExecute(alipayTradeWapPayRequest).getBody();
        } catch (AlipayApiException e) {
            pageString = "request aliapy has error";
            e.printStackTrace();
        }
        return pageString;
    }
    @RequestMapping("/pay/web")
    public   void   doPost (HttpServletRequest httpRequest, HttpServletResponse httpResponse)   throws ServletException, IOException {
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest(); //创建API对应的request
        alipayRequest.setReturnUrl( AlipayConfig.return_url );
        alipayRequest.setNotifyUrl( AlipayConfig.notify_url ); //在公共参数中设置回跳和通知地址
        AlipayVo alipayVo = new AlipayVo();
        // String out_trade_no = UUID.randomUUID().toString().replace("-", "");
        String out_trade_no = AlipayUtil.get().nextId() + "";
        System.out.println("out_trade_no:" + out_trade_no);
        // 设置订单单号,需要保证唯一性
        alipayVo.setOut_trade_no(out_trade_no);
        // 设置支付金额
        alipayVo.setTotal_amount("0.01");
        // 设置支付标题
        alipayVo.setSubject("hbq-test-title");
        // 设置订单有效时长(30分钟)
        alipayVo.setTimeout_express("30m");
        // 商品码(必须是QUICK_WAP_WAY),可以看文档 see: https://docs.open.alipay.com/203/107090/
        alipayVo.setProduct_code("FAST_INSTANT_TRADE_PAY");
        //备注
        alipayVo.setBody("Iphone6 16G");

        alipayVo.setPassback_params("merchantBizType%3d3C%26merchantBizNo%3d2016010101111");
        // 对象转为json字符串
        String json = JSONObject.toJSONString(alipayVo);

        alipayRequest.setBizContent(json); //填充业务参数*/
        String form= "" ;
        try  {
            form = client.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        }  catch  (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType( "text/html;charset="  + AlipayConfig.CHARSET);
        httpResponse.getWriter().write(form); //直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

}


