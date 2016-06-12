package com.co.cmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.messageresolver.AbstractMessageResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.dialect.SpringStandardDialect;
import org.thymeleaf.spring4.messageresolver.SpringMessageResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author by Cyprian Omenuko on 6/7/16.
 */
@SpringBootApplication
public class Application implements WebApplicationInitializer {

    private static SpringTemplateEngine templateEngine;

    static {
        initializeTemplateEngine();

    }

    public static void main(String args[]) {
        ApplicationContext context = SpringApplication.run(Application.class);

    }

    private static void initializeTemplateEngine() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();

        templateResolver.setTemplateMode("HTML5");
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(3600000L);
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setTemplateMode("HTML5");
        AbstractDialect dialect = new SpringStandardDialect();
        AbstractMessageResolver messageResolver = new SpringMessageResolver();

        templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setDialect(dialect);
        templateEngine.setMessageResolver(messageResolver);

    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

    }


}
