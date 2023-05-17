package class4.spm.novelnook.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "alipayconfig")
public class AliPayConfig {
    private String appId;
    private String appPrivateKey;
    private String alipayPublicKey;
    private String notifyUrl;
}