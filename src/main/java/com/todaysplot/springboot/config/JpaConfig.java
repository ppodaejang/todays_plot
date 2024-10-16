package com.todaysplot.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // SpringbootApplication.java에 있던 Auditing 활성화
public class JpaConfig {
}
