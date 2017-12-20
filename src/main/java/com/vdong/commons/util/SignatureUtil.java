package com.vdong.commons.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class SignatureUtil {


    public static String createMapSignature(Map<String, ? extends Object> map, String key) {
        if(!(map instanceof TreeMap)) {
            map = new TreeMap((Map)map);
        }

        StringBuilder stringBuilder = new StringBuilder();
        Iterator var4 = ((Map)map).entrySet().iterator();

        while(var4.hasNext()) {
            Entry<String, ?> entry = (Entry)var4.next();
            String _key = (String)entry.getKey();
            Object _val = entry.getValue();
            stringBuilder.append(_key).append("=").append(_val).append("&");
        }

        stringBuilder.append("key=").append(key);
        return DigestUtils.md5Hex(stringBuilder.toString()).toUpperCase();
    }
}
