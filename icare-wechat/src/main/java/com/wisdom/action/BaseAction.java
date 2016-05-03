package com.wisdom.action;


import com.wisdom.model.base.REQ_Base;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 *
 * Created by fusj on 15/12/23.
 */
public abstract class BaseAction<T extends REQ_Base> implements IAction {

    /**
     * 微信请求封装参数
     * @param paramMap
     * @return
     * @throws Exception
     */
    public String execute(Map<String, String> paramMap) throws Exception {
        T request;

        // 通过反射获取model的真实类型
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
        // 通过反射创建request的实例
        request = clazz.newInstance();

        // 获取类属性
        initRequest(clazz, paramMap, request);

        return this.doAction(request);
    }

    /**
     * 初始化request
     * @param clazz
     * @param paramMap
     * @throws Exception
     */
    private void initRequest(Class<?> clazz, Map<String, String> paramMap, T request) throws Exception{
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            String setMethodName = "set" + fieldName;

            Method setMethod = clazz.getMethod(setMethodName, new  Class[]{field.getType()});
            setMethod.invoke(request, new Object[]{paramMap.get(fieldName)});
        }

        if(null != clazz.getSuperclass()) {
            this.initRequest(clazz.getSuperclass(), paramMap, request);
        }
    }

    /**
     * 具体业务类需要实现
     * @param request
     * @return
     * @throws Exception
     */
    protected abstract String doAction(T request) throws Exception;
}
