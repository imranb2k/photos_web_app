package com.ibapp.photos.web;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;

/**
 * Created by imranbordiwala on 02/06/2015.
 */

public class StartApplicationPhotosWebUI {

    public static void main (String[] args) throws Exception {

        String webAppDirLocation = "/home/imran/photos_web_app/src/main/webapp";

        Tomcat tomcat = new Tomcat();

        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {

            webPort =  "8089";

        }

        tomcat.setPort(Integer.valueOf(webPort));

        tomcat.addWebapp("/photosWebApp", new File(webAppDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();

    }

}
