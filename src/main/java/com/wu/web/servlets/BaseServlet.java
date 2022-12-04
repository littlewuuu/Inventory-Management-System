package com.wu.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 1. 作为 dispatcher，根据不同的请求路径调用不同的 Servlet 及其方法
 * 2. 当资源请求到对应的 Servlet 的时候，会实例化对应的 Servlet 比如 BrandServlet，然后会先调用 service 方法，那么就进入到 BaseServlet 里面
 * 3. 然后调用 service 方法，通过反射，拿到需要执行的方法并执行。
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取方法
        //1.1 获取资源路径
        String requestURI = req.getRequestURI();
        //1.2  获取最后一段路径，方法名
        int lastIndex = requestURI.lastIndexOf("/");
        String methodName = requestURI.substring(lastIndex+1);

        //2. 调用方法
        //2.1 获取BrandServlet /UserServlet 字节码对象 Class
        Class<? extends BaseServlet> clazz =this.getClass();

        try {
            //2.2 得到方法
            Method method = clazz.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            //2.3 执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }
}
