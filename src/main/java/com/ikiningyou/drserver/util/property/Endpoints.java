package com.ikiningyou.drserver.util.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "endpoint")
public class Endpoints {

  private String frontend;
}
