package com.gupaoedu.vip;

import javafx.fxml.Initializable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class GpRpcServer implements ApplicationContextAware, InitializingBean {

    ExecutorService executorService = Executors.newCachedThreadPool();
    private int port;

    private Map<String,Object> handlerMap = new HashMap<>();

    public GpRpcServer(int port) {
        this.port = port;
    }

    @Override
    public void afterPropertiesSet() throws Exception {


        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);

            while (true){//不间断的接收请求
                Socket socket = serverSocket.accept();//BIO机制
//                每一个socket交个一个ProcessorHandler来处理
                executorService.execute(new ProcessorHandler(socket,handlerMap));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket !=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        if(!serviceBeanMap.isEmpty()){
            for(Object serviceBean:serviceBeanMap.values()){

                RpcService rpcService = serviceBean.getClass().getAnnotation(RpcService.class);
                String name = rpcService.value().getName();
                String version = rpcService.version();
                if(!StringUtils.isEmpty(version)){
                    name+="-"+version;
                }

                handlerMap.put(name,serviceBean);
            }

        }
    }
}
