package com.hammertime.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	@Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(false);
        
        // set the resolvable patterns to include the new directory
        resolver.setResolvablePatterns(Collections.singleton("tradie/*"));
        
        return resolver;
    }

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("main");
		registry.addViewController("/main").setViewName("main");
		registry.addViewController("/signup").setViewName("signup");
		registry.addViewController("/login").setViewName("login");
		

		
		

		// registry.addViewController("/payments").setViewName("payments");
		// registry.addViewController("/subscription").setViewName("subscription");
		registry.addViewController("/upload").setViewName("upload");

		// registry.addViewController("/payments").setViewName("payments");
		// registry.addViewController("/subscription").setViewName("subscription");

		registry.addViewController("/home").setViewName("home");
		
		// client pages
		registry.addViewController("/client/home").setViewName("client/C_home");
		registry.addViewController("/client/account").setViewName("client/C_account");
		registry.addViewController("/client/job").setViewName("client/C_job");
		registry.addViewController("/client/request").setViewName("client/C_request");
		registry.addViewController("/client/orders").setViewName("orders");
		registry.addViewController("/client/review").setViewName("client/C_review");
		registry.addViewController("/client/subscription").setViewName("client/C_subscription");
		registry.addViewController("/client/payments").setViewName("client/C_payments");
		registry.addViewController("/client/confirm").setViewName("client/confirm");

		// tradie pages
		registry.addViewController("/tradie/home").setViewName("tradie/T_home");
		registry.addViewController("/tradie/account").setViewName("tradie/T_account");
		registry.addViewController("/tradie/job").setViewName("tradie/T_job");
		registry.addViewController("/tradie/subscription").setViewName("tradie/T_subscription");
		registry.addViewController("/tradie/payments").setViewName("tradie/T_payments");

		// Admin
		registry.addViewController("/admin/home").setViewName("admin/A_Home");
		
	}

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/static/**")
          .addResourceLocations("classpath:/resources/", "classpath:/static/");
    }
    
}