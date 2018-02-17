package com.Aspect;


/*
import com.annotation.DataSource;
import com.dao.DynamicDataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.logging.Logger;
*/
/**
 * 线上测试1
 * 注解实现数据读写操作（切面）
 */
/*
@Aspect
@Order(1)
@Component
public class DataSourceAspect {
    @Before("execution(* com.service.Impl..*.*(..))")
    public void before(JoinPoint point){
        System.out.println("执行切面");
        Object target = point.getTarget();
        //获取方法名
        String method = point.getSignature().getName();
        System.out.println("执行方法" + method);
        Class<?> classz = target.getClass();
        //获取参数类型
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        try{
            Method m = classz.getMethod(method,parameterTypes);
            if (m != null && m.isAnnotationPresent(DataSource.class)){
                DataSource data = m.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.putDataSource(data.value());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
*/
