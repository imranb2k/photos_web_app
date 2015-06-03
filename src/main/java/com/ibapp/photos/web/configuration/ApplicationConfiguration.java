package com.ibapp.photos.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.mustache.MustacheViewResolver;
import org.springframework.web.servlet.view.mustache.java.MustacheJTemplateFactory;

/**
 * Created by imranbordiwala on 02/06/2015.
 */

@Configuration
@ComponentScan(basePackages = "com.ibapp.photos.web")
@EnableWebMvc
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver getViewResolver (ResourceLoader resourceLoader) {

        MustacheViewResolver mustacheViewResolver = new MustacheViewResolver();
        mustacheViewResolver.setPrefix("classpath:views/");
        mustacheViewResolver.setSuffix(".mustache");
        mustacheViewResolver.setCache(false);
        mustacheViewResolver.setContentType("text/html;charset=utf-8");

        MustacheJTemplateFactory mustacheJTemplateFactory = new MustacheJTemplateFactory();
        mustacheJTemplateFactory.setResourceLoader(resourceLoader);

        mustacheViewResolver.setTemplateFactory(mustacheJTemplateFactory);

        return mustacheViewResolver;

    }

}
