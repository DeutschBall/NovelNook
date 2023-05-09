package class4.spm.novelnook.common;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AliPayConfig {

    private String appId;
    private String appPrivateKey;
    private String alipayPublicKey;
    private String notifyUrl;

    public AliPayConfig() {
        this.appId = "2021000122683765";
        this.appPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDMozR63zI//a0TVJ7PtGcz4Pl0/xgWVhKlYBjQLcHCu6cHML0B/pLXnQAeJLctV/p6ffkO8o5c7Txwa8gL+C3kR/McEmvrFme4mtqzvJwniYgYGlg/rv/1JqqyXSXDkj7zS3Kdh3jeRiTz9koH1DOh1pMiATck2FdZxRspcXD6qUWo6FI5sVvnpgDo11jIgJyyAAu/K3y8KJ+XwGB9zaFQK/pbmk8jstbS+JTbKpa6mmV3bnmrnnyG5n9OnarF92cHDc1lGwbqz06XFRVu7DWI+w8naQMUAsFcn4zVIZLw7ytEXRPueKbvrR2gJBfq9Ziwq0OfvRiRoyB2cqvra49vAgMBAAECggEAeSYZ+LBQoH+/SlVrm83S12zWYoRvLzIP4eYtanncXzwXG3OoQhkDuDBTlITqCScvu/r+SvIghDVS9b9pdGSeTqql67WgPFJMX4cV+RitXd7XOmXGXxVspScaIXRx2gGn0QOWvFoqnXTrhZ8GnvHrC2HsQ1Jy4hZOSHAfr35gg5RKC9UrsIDkhtFCn2TZekpOauIhA4H6rYMeuSNDE3i4jQfUWuGcCzOp1ZBOZzCL81SyFokR67p319tmo6827/IFs5duUcICImQT4LTPc5xK7iF8SCDuYnr38we4hVtgJDv/uo0PRD3xzarQBpNI6bFLvWUgFzcCGm2anhcueH5mmQKBgQDmtdvb46YlKvxpoHwlLbbJnJr8gHTU/CIXbsP90lDhYKniep4Gd2s0WEftTjT7YFMUEweyXzHNoOmeRj3b/0cwqZS9LzSspQWrTe9kTsvV8FC+vLnPB2xsnat8Tyfq9dUEeHLWzZ6rrvn5BgAQNo1yunYfTllDTEdRPWheu9cYlQKBgQDjEbIJ0ATplKIWA8kdHFtatolowd7ou0vHCCJNqBqTi4654lTsYT+BbYahoPwoaGafieXBjzwDA/l/XfCBuOzJCyrPcukNTZQgGoOMh5RzS6ZA6eoPyIMgsYWVCgsrvbZhvYuzyWYV2965uuDl0SWVbqZ80OyVWHf7q3CHZYHS8wKBgQCia4Fe6oN7Qyn0StFbczVt9lx+Swj1MJPnn0lHlhrusBipot1BfCzy6vuoKxjNp+CVD1ip0K7FLvWJQbYm/S7nKVvXXDZfHMZC9VkFaxvBf6+4uDCveXBxfwbNOwTFO1A+F3Xxl0yPXV4KXJkK0Pz/uhmSrJvW7wp+PRcPKnqhiQKBgQC9TapdTeRai8SZj+xh7ZsnxFhIUqCw7DzfoKNglI4bhdSmmc9tBiI4mAQOybSPR4KO7RrzxKMmbHDbw4hXzcDyVvII4ubyZUPhg9k6FmonuODQ63JsWCEh533TY/0uKUpcr/ZBG92Uyaoks2z9i+DGdOot0sv6MjhNQ39cEb8rCwKBgEqO1pXw2RkxuRZvwuEMQfdNPM/Cvniar6tlYRaLsVr4sal6HaTzXfDQFKhExcQsj5wynm6P2XEKvbWfC+MHmlEgPwd85rYKhmXiruZeFFNS0QNj0pN3MbgXOzQFcvK1F4QXN7S0lDtb7zvEbuZyF5pvSSNafIKYSGLeAao5o494";
        this.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkSCxEzjpS4tIoYa9J2rQeiuMWxFrJmaAecdJ0WfDs7g8WpOSkvAYEdFR+D6NitqP5YYNuCk7dDMquGSpwgOsUN5UF9MGLfoqnaXW4tMCoYs5EDE0+QSwUSQFPnrK9rVmHIdSP60/8NDPKB32RehelwUxi7CejzfhC6yxp/l6gGKnHFChXW8AIsGapQRu6608vNErwgj7JeVnGvy2V3ulucncn+1wiBxsbyiKRCIkCa21nl88zMRgnfrezrorG+nA/Wk+M+dIF7Az7I4+2XcaG3+q6lMOLiecBfTqBpfQq/b4v8MYyWPoe9CZpy883VkcyBItNxMyZL/k4CS6kHIRqQIDAQAB";
        this.notifyUrl = "http://www.baidu.com";
    }

    public String getAppId() {
        return appId;
    }

    public String getAppPrivateKey() {
        return appPrivateKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }
}