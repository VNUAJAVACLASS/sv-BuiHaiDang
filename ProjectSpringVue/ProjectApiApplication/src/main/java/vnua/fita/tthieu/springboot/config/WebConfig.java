package vnua.fita.tthieu.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration: Lớp cấu hình
// nếu có lớp cấu hình bảo mật sau này cần viết gộp
// chức năng này vào lớp cấu hình bảo mật
@Configuration
public class WebConfig {
    @Bean  // @Bean: Định nghĩa một tiêu chí cấu hình (qua một method)
    public WebMvcConfigurer corsConfigurer() {
    	// Implement trực tiếp interface WebMvcConfigurer
    	// (Implement và tạo luôn đối tượng)
    	return new WebMvcConfigurer() { 
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173") // Cho phép ứng dụng Vue khác cổng truy cập
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
}

