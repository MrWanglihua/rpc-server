package com.gupaoedu.vip;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解的含义为，定义一个标志位，我们可以根据此标志位找到对应的方法或者类
 */
@Target(ElementType.TYPE) //标注该注解使用的位置（TYPE:类、接口）
//注解要保留到什么状态，是编译状态还是运行时状态
@Retention(RetentionPolicy.RUNTIME)  //(RUNTIME 表示为运行时状态)
@Component//被Spring扫描
public @interface RpcService {
    Class<?> value();//拿到服务的接口

    /**
     * 版本号
     */
    String version() default "";
}
