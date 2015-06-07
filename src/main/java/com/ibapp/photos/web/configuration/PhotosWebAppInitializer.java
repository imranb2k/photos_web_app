package com.ibapp.photos.web.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class PhotosWebAppInitializer implements WebApplicationInitializer{

    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(ApplicationConfiguration.class);

        registerServlets(servletContext, rootContext);

    }

    private void registerServlets(ServletContext servletContext, AnnotationConfigWebApplicationContext rootContext) {

        ServletRegistration.Dynamic dispatcher =  servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));

        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/*");

    }

}
