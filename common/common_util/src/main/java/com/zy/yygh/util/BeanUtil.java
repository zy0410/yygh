package com.zy.yygh.util;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * @author Zhanye
 * @since 2024/11/15 16:04
 */

public class BeanUtil {

    public static void copyNotNull(Object source, Object target) {
        // 获取源对象和目标对象的属性描述
        PropertyDescriptor[] targetDescriptors = BeanUtils.getPropertyDescriptors(target.getClass());
        for (PropertyDescriptor targetDescriptor : targetDescriptors) {
            // 只复制 getter 和 setter 存在的属性
            Method readMethod = targetDescriptor.getReadMethod();
            Method writeMethod = targetDescriptor.getWriteMethod();
            if (readMethod != null && writeMethod != null) {
                try {
                    Object value = readMethod.invoke(source); // 获取源对象属性值
                    if (value != null) {
                        writeMethod.invoke(target, value); // 只复制非空值
                    }
                } catch (Exception e) {
                    // 处理异常
                }
            }
        }
    }
}
