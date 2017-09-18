package com.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * @author esther
 * @create 2017-09-11 16:43
 * $DESCRIPTION}
 */

public class CommonQuestionControllerTest extends AbstractControllerTest {

    @Test
    public void add() throws Exception {
        String url = "http://localhost:9098/question/add";
        Map<String, String> map = new HashMap<>();
        map.put("title", "标题");
        map.put("content", "测试2<img src=\"http:\\zacdn-image.cgw360.com\\cgw360\\cls\\car_dealer\\7517bf18-526a-4ba4-ac23-05de09f57a63.jpg\">");
        map.put("typeId", "1");
        map.put("typeName", "征信");
        map.put("phone", "13654561589");
        System.out.println("body=" + test(url, map));
    }

    @Test
    public void update() throws Exception {
        String url = "http://localhost:9098/question/update";
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("title", "测试标题标题");
        map.put("content", "测试图片<img src=\"http:\\zacdn-image.cgw360.com\\cgw360\\cls\\car_dealer\\7517bf18-526a-4ba4-ac23-05de09f57a63.jpg\">");
        map.put("typeId", "2");
        map.put("typeName", "车贷");
        map.put("phone", "13654561588");
        System.out.println("body=" + test(url, map));
    }

    @Test
    public void delete() throws Exception {
        String url = "http://localhost:9098/question/delete";
        Map<String, String> map = new HashMap<>();
        map.put("id", "3");
        System.out.println("body=" + test(url, map));
    }

    @Test
    public void get() throws Exception {
        String url = "http://localhost:9098/question/get";
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        System.out.println("body=" + test(url, map));
    }

    @Test
    public void list() throws Exception {
        String url = "http://localhost:9098/question/list";
        Map<String, String> map = new HashMap<>();
        map.put("title", "测试");
        map.put("content", "测试");
        // map.put("typeId","2");
        map.put("status", "1");
        System.out.println("body=" + test(url, map));
    }

    @Test
    @Deprecated
    public void upload() {
        String url = "http://localhost:9098/question/upload";
        String fileName = "E://head1.jpg;E://head.jpg";
        sendFilesPost(url, fileName);
    }

    @Test
    public void enable() throws Exception {
        String url = "http://localhost:9098/question/enOrDisable";
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("status", "0");
        System.out.println("body=" + test(url, map));
    }

    @Test
    @Deprecated
    public void list2() throws Exception {
        String url = "http://localhost:9098/organization/brachCompany";
        Map<String, String> map = new HashMap<>();
        System.out.println("body=" + test(url, map));
    }
}

