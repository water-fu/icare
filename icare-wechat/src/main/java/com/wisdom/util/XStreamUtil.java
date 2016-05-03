package com.wisdom.util;

import com.thoughtworks.xstream.XStream;

import java.util.Map;

/**
 * 使用XStream转换成XML
 *
 * Created by fusj on 15/12/21.
 */
public class XStreamUtil {

    /**
     * 使用XStream将Obj对象转换成XML
     * @param obj
     * @param map   key表示xml的别名，Class表示需要转换的类
     * @return
     */
    public static String stringToXML(Object obj, Map<String, Class> map) {
        XStream xstream = new XStream();

        for(Map.Entry<String, Class> entry : map.entrySet()) {
            xstream.alias(entry.getKey(), entry.getValue());
        }

        return xstream.toXML(obj);
    }
}
