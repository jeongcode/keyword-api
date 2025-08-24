package com.sj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")						// 모든 경로에 대해 CORS 허용
				.allowedOrigins("*")					// 모든 Origin 허용
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용 HTTP 메서드 명시
				.allowedHeaders("*")					// 모든 헤더 허용
				.allowCredentials(false)				// 자격증명(쿠키, 인증정보) 불허 (보안 필요에 따라 설정)
				.maxAge(3600);							// preflight 요청 캐시 시간 (초)
	}
}
