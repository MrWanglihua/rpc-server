package com.gupaoedu.vip;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        /*IHelloService service = new HelloServiceImpl();

        RpcProxyServer rpcProxyServer = new RpcProxyServer();
        rpcProxyServer.publisher(service,8080);*/

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        ((AnnotationConfigApplicationContext) context).start();


    }
}
