package com.hbq.teacher_plus.config;
/**
 * @author hbq
 * @createTime 2020/3/19 23:29
 */
public class AlipayConfig {
    // 商户appid
    public static String APPID = "2016101500690876";
    public static String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCtCaBed86efCeKcfNJlWdCxhMGkUhnS+efhhHzMkozz/+51aYyI4VT2l1Q/sfxxgGl5zvcg1dSiZWdaao/seXht+Y55Is5dyeatqIIJ1Zx/TzSQdMo+gX+PtWdcBbuDC3l4xnQt2EDTja9sa+ixngxogiV4/9eIWrHXb2dIjhLFRs5xA039nGcDK+S+jtUnoUpk442UppKBwTOTWC/acpM5ZYd+R2HYyquZzBQmTLk5I6AZCGjXhsWqu8VCGEjhe4MFf60hGEm04v/6qYDsdKsAjqUmIUrcfqSjHxOPev7Zwv8s6JTnbijRQtyUrhHntgL6URUujjOGpn+GZzWRWLLAgMBAAECggEARRrMFg4o0ThqHitF6wQkuhEatgu9a6tvtFC5yHM0GR8fVNsTRYDUFVnm3JrzxQW70MLZMEav0AGsY7I4ULm+QffMlSBFzcxdX7dCXJlBN8LX0GyDZSD81b3IV9jU/3qTtfSNccJalxRcXkOhDhPPGptIbHwilzgnSbmRKTjqXGlG0j495HJjGREkPcQn2ExJ6lnqcF9/XIKutBhm0/oyzSdV1hXUYuN4YA1rbd3XY9i5xZJMmPp2ObMttJg4aN2EcLHGwrHlHeYajba09O3jbIsFPCNjTIy876/nJDIPJ50h0HzOwjFUUMEMaB0GKunUcqDtdYMbEQOzGmSMeVysYQKBgQDw5GOr7umLIYP3+G3UUsXWLOov0cr8nstyFEsfCtf69nLQsmiK6emSzoM9k4ttasF/0fkG5cl/esxxMLhYxwkeTBGozIMrJNs0iiR5j6L8FAhdNxArW4/FoDoYEb+H3Tqt4b6i01XIWlQDM+e9scQboJgmqFWX9R8a+8sh5hXGsQKBgQC3487Ol4VtWGzUDshnPM4vtZqP7lX9RN0TfJ8s1q+JaMvY9WmjshXI3OO/dBqaBdlAy+/cfbgj9xfdEtyJqAwK5OY0llsPzgpLlm45uFTUAdoPD7pWbavoR0MifytYqtyHwb9gNOC7ygJN0yUIGnncWZSBszv7d9HMW1jZdqAYOwKBgHcoYm1P9tJNt9vXVWyrrRTujsEsw34GhphdlXM7h+6qLc0yEyhz4IgwAxdYfqWXIzQ5Bzf0PqICelKbSPURL19/PVUEJPYYex4YH0bKWFLdDe4eKK9p0UpuoY36aYWpVzdf7U7ZvxsmI1yaNk3NOzZ3UAv/RKewJrzQRID2MVthAoGBAIRr3k9TOdhGJwiGJLaDSQ9+PXGMdgvOfU09HvsRFpNjDfiMsagZ5CojNzZ1i3qC37ITqgUj22i61Upz/LB8fT+TGlugMq8s9qj8CSAzp5KMStUxxEPc8kBwoN3EqhhN4kU4tnG3YmaY0BPvCunM9xhAwfqscXNjE4eP+GmRTTRlAoGBAKwols0KxXR48OBbeoX3G7KykzUmn9OjypBobVGsamNw79NgsMvlxkXed/0jbv6W+/uOzX/1GXwEjTmlBeO2wDristanDu9A8RZoJU4rPs/QpvVXvYgLe7NetDkC7Hp/SmpICLHlj7BbeaP9ebN2WSZicFD6sdXdy9sCBDhqL/fY";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://www.idse.top";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://www.idse.top";
    // 请求网关地址,沙箱是:https://openapi.alipaydev.com/gateway.do
    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥(在应用中可以获取)
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnxoj8ib3HmsoTYZvuT/dppTY1ecDZupos+n432cleQ4ndphYt4ZKzn+Y97mTRtCu+7qtyls1/cJ0xHn1WH4+qse2LckJkJWp+G+qsWim7E39ITgldmFGM+6hS/3B+Em0cWhC5WPAr0SklkRrRCkaU/m7nK+QhAzvjl+8vEz2R8vPgy4kTbSTPwa5CC++hUOqu31yZliJibxbIDqgXHNJDniGrzQdh2Apf08Qo67PHwk+zkVatuJHEPrSKgySFQTULnnUliaUHSoC79jTcaSDbHTcxhdyAavm6trd0tV9V7IbgD+1B2XXCtVomUVds32KfTbuGuTBPi+BpVFK2YRb+wIDAQAB";
    // RSA2
    public static String SIGNTYPE = "RSA2";

}
