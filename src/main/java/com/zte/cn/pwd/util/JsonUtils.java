package com.zte.cn.pwd.util;

import com.alibaba.fastjson.JSONObject;

public final class JsonUtils {

    private JsonUtils() {
        super();
    }

    public static <T> T toObject(final String json, final Class<T> clazz) {

        try {
            if (json == null || json.isEmpty() || clazz == null) {
                return null;
            }
            return JSONObject.parseObject(json, clazz);
        } catch (Exception e) {
            System.out.println("Json to Object failed");
        }
        return null;
    }

    public static String toPrettyString(final Object value) {
        try {
            if (value == null) {
                return null;
            }
            if (value instanceof String) {
                return (String) value;
            }
            return JSONObject.toJSONString(value);
        } catch (Exception e) {
            System.out.println("Object to Json failed");
        }
        return null;
    }

    public static String toString(final Object value) {
        try {
            if (value == null) {
                return null;
            }
            if (value instanceof String) {
                return (String) value;
            }
            return JSONObject.toJSONString(value);
        } catch (Exception e) {
            System.out.println("Object to Json failed");
        }

        return null;
    }

    public static String toStringQuietly(final Object value) {
        try {
            return toString(value);
        } catch (Exception e) {
            System.out.println("Object to Json failed");
            return "";
        }
    }

}
