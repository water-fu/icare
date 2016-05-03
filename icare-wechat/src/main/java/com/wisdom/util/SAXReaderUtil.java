package com.wisdom.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SAXReader工具类
 * Created by fusj on 15/12/21.
 */
public class SAXReaderUtil {
    /**
     * xml转为map集合
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) throws Exception {
        InputStream ins = null;
        try {
            ins = request.getInputStream();
            Map<String, String> map = new HashMap<String, String>();
            SAXReader reader = new SAXReader();

            Document doc = reader.read(ins);

            Element root = doc.getRootElement();

            List<Element> list = root.elements();

            for(Element e : list){
                map.put(e.getName(), e.getText());
            }

            return map;
        } catch (Exception ex) {
            throw  ex;
        } finally {
            if(null != ins) {
                ins.close();
            }
        }
    }

    /**
     * 将request转为XML
     * @param request
     * @return
     * @throws Exception
     */
    public static String toXml(HttpServletRequest request) throws Exception {
        InputStream ins = null;
        try {
            ins = request.getInputStream();

            SAXReader reader = new SAXReader();
            Document document = reader.read(ins);

            return document.asXML();
        } catch (Exception ex) {
            throw  ex;
        } finally {
            if(null != ins) {
                ins.close();
            }
        }
    }
}
