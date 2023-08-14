package io.renren;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
//        registry.addResourceHandler("/static/**").addResourceLocations("file:"+System.getProperty("user.dir")+"/"+"static/");
        registry.addResourceHandler("/static/**").addResourceLocations("file:D:/lab/exp/springboot-vue-nodejs/renren-security/static/");
    }
}
