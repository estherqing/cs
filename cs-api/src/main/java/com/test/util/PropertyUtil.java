package com.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by chen on 17-6-2.
 */
public class PropertyUtil {

    public final static String IMAGE_DOMAIN_CDN = "image.domain.cdn";

    public final static String IMAGE_BUCKET_NAME = "image.bucket.name";

    public static Map<String,String> map = new HashMap<>();

    public static String getValue(String key){
        if (map.isEmpty()){
            loadMap();
        }

        return map.get(key);
    }

    static {
        loadMap();
    }

    public static void loadMap(){
        if (map.isEmpty()){
            Properties p = readFile("za-api.properties");
            map.put(IMAGE_DOMAIN_CDN,p.getProperty(IMAGE_DOMAIN_CDN));
            map.put(IMAGE_BUCKET_NAME,p.getProperty(IMAGE_BUCKET_NAME));
        }
    }

    public static Properties readFile(String name){
        Properties p = new Properties();

        //String path = PropertyUtil.class.getClassLoader().getResourceAsStream(name);
        try {
            InputStream in = PropertyUtil.class.getClassLoader().getResourceAsStream(name);
            //InputStream in = new BufferedInputStream(new FileInputStream(path));
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }

}
