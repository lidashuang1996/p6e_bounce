package com.p6e.bounce.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copy 的帮助类
 */
public final class CopyUtil {

    public static <T> T run(Object data, Class<T> c) {
        // 判断是否为 null
        if (data == null) return null;
        try {
            boolean b1 = true, b2 = true;
            Class<?> dataClass = data.getClass();
            // 判断是否接口于 java.io.Serializable
            for (Class<?> anInterface : dataClass.getInterfaces())
                if (anInterface == Serializable.class) { b1 = false; break; }
            for (Class<?> anInterface : c.getInterfaces())
                if (anInterface == Serializable.class) { b2 = false; break; }
            if (b1 || b2) throw new Error("no interface java.io.Serializable");
            Field[] dataFields = obtainFields(dataClass);
            Field[] cFields = obtainFields(c);
            T t = c.newInstance();
            for (Field f1 : dataFields) {
                for (Field f2 : cFields) {
                    if (f1.getName().equals(f2.getName())) {
                        f1.setAccessible(true);
                        f2.setAccessible(true);
                        Object o = f1.get(data);
                        boolean bool = f1.getGenericType().equals(f2.getGenericType());
                        if (bool && o != null) f2.set(t, o);
                        if (!bool && o != null) {
                            if (f1.getGenericType().getTypeName().startsWith("java.util.List") &&
                                    f2.getGenericType().getTypeName().startsWith("java.util.List")) {
                                List list = new ArrayList<>();
                                ((List) o).forEach(item -> list.add(item));
                                f2.set(t, list);
                            } else f2.set(t, run(o, Class.forName(f2.getGenericType().getTypeName())));
                        }
                        break;
                    }
                }
            }
            return t;
        } catch (IllegalAccessException | InstantiationException | Error | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T, W> List<T> run(List<W> data, Class<T> c) {
        if (data == null) return null;
        List<T> list = new ArrayList<>();
        data.forEach(item -> list.add(run(item, c)));
        return list;
    }

    private static Field[] obtainFields(Class<?> cl) {
        List<Field> fields = new ArrayList<>(Arrays.asList(cl.getDeclaredFields()));
        Class<?> cls = cl.getSuperclass();
        while (cls != null && cls != Object.class) {
            fields.addAll(Arrays.asList(cls.getDeclaredFields()));
            cls = cls.getSuperclass();
        }
        return fields.toArray(new Field[0]);
    }

}


