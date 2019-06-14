package com.gupaoedu.vip;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 目的：为暴露服务
 * 基于：socket
 */
/*public class RpcProxyServer {

    ExecutorService executorService = Executors.newCachedThreadPool();

    public void publisher(Object service,int port){

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            
            while (true){//不间断的接收请求
                Socket socket = serverSocket.accept();//BIO机制
//                每一个socket交个一个ProcessorHandler来处理
                executorService.execute(new ProcessorHandler(socket,service));

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

}*/
