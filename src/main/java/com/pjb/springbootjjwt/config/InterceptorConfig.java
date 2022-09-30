package com.pjb.springbootjjwt.config;

import com.pjb.springbootjjwt.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册拦截器
 *
 * @author jinbin
 * @date 2018-07-08 22:33
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //定义排除swagger访问的路径配置
        String[] swaggerExcludes = new String[]{
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/webjars/**",
                "/v2/**"
        };

        //自定义去除的路径
        String[] myExcludes = new String[]{
//                "/page/**"
        };

        registry.addInterceptor(authenticationInterceptor())
                // addPathPatterns 用于添加拦截规则
                .addPathPatterns("/**")
                //自己定义的不拦截的规则
                .excludePathPatterns(myExcludes)
                //去除拦截springboot的静态文件
                .excludePathPatterns("/html/*")
                .excludePathPatterns("/demo")
                .excludePathPatterns("/")
                .excludePathPatterns("/error")
                //下面是固定格式,如果不配置swagger页面将会访问不了
//                .excludePathPatterns(swaggerExcludes)
//放行swagger
                .excludePathPatterns("/user/login", "/user/register", "/doc.html")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**")
        ;
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html", "doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

}
