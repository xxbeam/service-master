package com.platform.service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface RedisService<T> {

    /**
     * 插入key值
     * @param key
     * @param value
     * @param mills
     * @param timeUnit
     */
    public void setForValue(String key, T value, long mills, TimeUnit timeUnit);

    /**
     * 查询
     * @param key
     * @return
     */
    public T getForValue(String key);

    /**
     * 删除
     * @param key
     */
    public void removeKey(String key);

    /**
     * 新增set
     * @param key
     * @param value
     */
    public void setForSet(String key, T value);

    /**
     * 查询set
     * @param key
     * @return
     */
    public Set<T> getForSet(String key);

}
