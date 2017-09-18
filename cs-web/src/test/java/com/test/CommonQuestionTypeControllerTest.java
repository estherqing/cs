package com.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author esther
 * @create 2017-09-05 10:33
 * $DESCRIPTION
 */

public class CommonQuestionTypeControllerTest extends AbstractControllerTest {

    @Test
    public void typeAdd() throws Exception {
        String url = "http://localhost:9098/cs/question/type/add";
        Map<String, String> map = new HashMap<>();
        map.put("name", "车贷");
        System.out.println("body=" + test(url, map));
    }

    @Test
    public void typeUpdate() throws Exception {
        String url = "http://localhost:9098/question/type/update";
        Map<String, String> map = new HashMap<>();
        map.put("id","1");
        map.put("name", "征信1");
        map.put("remark","remark");
        System.out.println("body=" + test(url, map));
    }

    @Test
    public void typeDelete() throws Exception {
        String url = "http://localhost:9098/question/type/delete";
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        System.out.println("body=" + test(url, map));
    }

    @Test
    public void typeGet() throws Exception {
        String url = "http://localhost:9098/question/type/get";
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        System.out.println("body=" + test(url, map));
    }

    @Test
    public void typeList() throws Exception {
        String url = "http://localhost:9098/question/type/list";
        Map<String, String> map = new HashMap<>();
       // map.put("name", "");
        map.put("status","1");
        System.out.println("body=" + test(url, map));
    }
    
    @Test
    public void allList() throws Exception{
        String url = "http://localhost:9098/question/type/allList";
        Map<String, String> map = new HashMap<>();
        // map.put("name", "");
       // map.put("status","1");
        System.out.println("body=" + test(url, map));
    }
}
