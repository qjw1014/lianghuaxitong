package com.wallet.common.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 系统监听类
 *
 * @author chenle
 */
@WebListener
@Component
public class SystemListener implements ServletContextListener {

    private static ApplicationContext applicationContext;

    /**
     * contextPath目录
     */
    private static String CONTEXT_PATH = "contextPath";

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext application = event.getServletContext();
        applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(application);

        StaticValue.realPath = application.getRealPath("");
        StaticValue.contextPath = application.getContextPath();

        application.setAttribute(CONTEXT_PATH, StaticValue.contextPath);
        this.subclassInit();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

    /**
     * 子类可继承实现
     */
    protected void subclassInit() {

    }

    /**
     * @return 获取spring上下文
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 设置应用上下文
     *
     * @param applicationContext 应用上下文
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {
        if (SystemListener.applicationContext == null) {
            SystemListener.applicationContext = applicationContext;
        }
    }

    /**
     * @param beanName     名字
     * @param requiredType class类型
     * @param <T>          类
     * @return bean对象
     */
    public static <T> T getBean(String beanName, Class<T> requiredType) {
        return applicationContext == null ? null : applicationContext.getBean(beanName, requiredType);
    }

    /**
     * @param requiredType class类型
     * @param <T>          类
     * @return bean对象
     */
    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext == null ? null : applicationContext.getBean(requiredType);
    }
    
    public static Object getBean(String type){
        return applicationContext == null?null : applicationContext.getBean(type);
    }
}
