package com.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jiyanbin on 15/8/29.
 */
public class AbstractControllerTest {

    private static String phone = "15000000001";
    private static String password = "15000000002";

    //spring security的URL定死了为：login
    final static String URL = "http://localhost:9098/login";
    //spring security的URL定死了为：logout
    final static String LOGOUT_URL = "http://localhost:9098/logout";
    //protected static HttpClient httpClient = HttpClients.createDefault();

    protected static LoginResult loginResult = new LoginResult();

   // @BeforeClass
    public static void setUp() throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(URL);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userName", phone));
        params.add(new BasicNameValuePair("password", password));
        HttpEntity entity = new UrlEncodedFormEntity(params, Charset.defaultCharset());

        //LogManager.getRootLogger().setLevel(Level.ALL);
        post.setEntity(entity);

        // 发送请求
        HttpResponse httpResponse = httpClient.execute(post);
        HttpEntity respEntity = httpResponse.getEntity();
        int code = httpResponse.getStatusLine().getStatusCode();
        String cookie = httpResponse.getHeaders("Set-Cookie")[0].getElements()[0].getValue();
        loginResult.setCode(code);
        loginResult.setToken(cookie);
        //String cookies = httpResponse.getHeaders("Cookie")[0].getValue();
        if (code == 302) {
            String redirect = httpResponse.getHeaders("Location")[0].getValue();
            HttpGet get = new HttpGet(redirect);
            httpResponse = httpClient.execute(get);
            respEntity = httpResponse.getEntity();
        }

        String body = EntityUtils.toString(respEntity);
        ObjectMapper objectMapper = new ObjectMapper();
        //loginResult = objectMapper.readValue(body,LoginResult.class);
        //System.out.println("body="+body);
        //String body = mapper.readValue(respEntity.getContent(),String.class);
        System.out.println("body=" + body);
        //System.out.println("cookies="+cookies);
        //HttpResponseProxy{HTTP/1.1 302 Found [Server: Apache-Coyote/1.1, Cache-Control: no-cache, no-store, max-age=0, must-revalidate, Pragma: no-cache, Expires: 0, X-XSS-Protection: 1; mode=block, X-Frame-Options: DENY, X-Content-Type-Options: nosniff, Set-Cookie: JSESSIONID=71A72AF00EC83B32634762A8C9C8D254; Path=/ppcp/; HttpOnly, Location: http://121.40.193.167:8180/ppcp/index.do, Content-Length: 0, Date: Sat, 29 Aug 2015 10:39:43 GMT] [Content-Length: 0,Chunked: false]}
    }

   // @AfterClass
    public static void tearDown() {

    }

    public static String test(String url, Map<String, String> map) throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
       // post.addHeader("Cookie", "JSESSIONID=" + loginResult.getToken());
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        HttpEntity entity = new UrlEncodedFormEntity(params, Charset.defaultCharset());
        post.setEntity(entity);
        HttpResponse httpResponse = httpClient.execute(post);
        HttpEntity respEntity = httpResponse.getEntity();
        int code = httpResponse.getStatusLine().getStatusCode();
        if (code == 302) {
            String redirect = httpResponse.getHeaders("Location")[0].getValue();
            HttpGet get1 = new HttpGet(redirect);
            httpResponse = httpClient.execute(get1);
            respEntity = httpResponse.getEntity();
        }
        String body = EntityUtils.toString(respEntity);
        ObjectMapper objectMapper = new ObjectMapper();
        //System.out.println("body=" + body);
        return body;
    }

    @Deprecated
    public static String sendFilesPost(String url, String fileNames) {
        HttpClient httpClient = null;
        HttpPost httpPost;
        String result = null;
        try {
            httpClient = HttpClients.createDefault(); // new DefaultHttpClient();
            httpPost = new HttpPost(url);
            httpPost.addHeader("Cookie", "JSESSIONID=" + loginResult.getToken());

            String[] filenames=fileNames.split(";");
            MultipartEntity reqEntity = new MultipartEntity();
            for(int i=0;i<filenames.length;i++) {
                String fileName=filenames[i];
                FileBody file = new FileBody(new File(fileName));

                reqEntity.addPart("file"+i, file);// file1为请求后台的File upload;属性

            }
            httpPost.setEntity(reqEntity);
            HttpResponse response = httpClient.execute(httpPost);
            if (null != response && response.getStatusLine().getStatusCode() == 200) {
                HttpEntity resEntity = response.getEntity();
                if (null != resEntity) {
                    result = EntityUtils.toString(resEntity, HTTP.UTF_8);
                    System.out.println(result);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接，释放资源
            httpClient.getConnectionManager().shutdown();
        }
        return result;
    }
}
