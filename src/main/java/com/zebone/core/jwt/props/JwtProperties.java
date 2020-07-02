package com.zebone.core.jwt.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("nhis.token")
public class JwtProperties {

    private Boolean state;
    private Boolean single;
    private String signKey;

    public JwtProperties() {
        this.state = Boolean.FALSE;
        this.single = Boolean.FALSE;
        this.signKey = "nhis";
    }
}
