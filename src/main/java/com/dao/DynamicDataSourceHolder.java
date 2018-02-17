package com.dao;
/**
 * mysql 读写分离配置
 * 使用ThreadLocal技术来记录当前线程中的数据源的key
 */
public class DynamicDataSourceHolder {
    public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    public static void putDataSource(String name) {
        holder.set(name);
    }

    public static String getDataSouce() {
        return holder.get();
    }
}
