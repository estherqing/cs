package com.test.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 网络工具
 */
public class HttpUtil {
    /**
     * 获得一个http连接
     *
     * @param url 链接地址
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static HttpURLConnection getHttpURLConnection(String url) throws MalformedURLException, IOException {
        if (url.contains("http://")) {
            url = "http://" + url.substring(url.indexOf("http://") + "http://".length()).replaceAll("//", "/");
        } else if (url.contains("https://")) {
            url = "https://" + url.substring(url.indexOf("https://") + "https://".length()).replaceAll("//", "/");
        }

        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
        httpURLConnection.setConnectTimeout(50000);
        httpURLConnection.setReadTimeout(50000);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpURLConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        httpURLConnection.setRequestProperty("Cache-Control", "max-age=0");
        httpURLConnection.setRequestProperty("Host", getHost(url));
        httpURLConnection.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
        httpURLConnection.setRequestProperty("Date", TimeUtil.getRfc822DateFormat(new Date()));
        httpURLConnection.setInstanceFollowRedirects(true);

        return httpURLConnection;
    }

    /**
     * get请求
     *
     * @param httpURLConnection 请求连接
     * @param acceptCharset     接受编码,不传 utf-8
     * @return
     * @throws Exception
     */
    public static String doGet(HttpURLConnection httpURLConnection, Charset acceptCharset) throws Exception {
        if (acceptCharset == null) {
            acceptCharset = Charset.forName("UTF-8");
        }

        StringBuffer resp = new StringBuffer();

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            httpURLConnection.connect();

            is = httpURLConnection.getInputStream();
            isr = new InputStreamReader(is, acceptCharset);
            br = new BufferedReader(isr);

            String s = null;
            while ((s = br.readLine()) != null) {
                if (s.contains("\\u")) {
                    s = UnicodeUtil.decodeUnicode(s);
                }
                s = s.replaceAll("&nbsp;", " ").replaceAll("\\s{2,}", " ").replaceAll("\t|\n|\r|\b|\f", "");

                resp.append(s);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e2) {
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (Exception e2) {
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e2) {
                }
            }
        }

        return resp.toString();
    }

    /**
     * get请求
     *
     * @param url           请求地址
     * @param acceptCharset 接受编码,不传 utf-8
     * @return
     * @throws Exception
     */
    public static String doGet(String url, Charset acceptCharset) throws Exception {
        return doGet(getHttpURLConnection(url), acceptCharset);
    }

    /**
     * get请求
     *
     * @param url 请求地址
     * @return
     * @throws Exception
     */
    public static String doGet(String url) throws Exception {
        return doGet(getHttpURLConnection(url), Charset.forName("UTF-8"));
    }

    /**
     * post请求
     *
     * @param httpURLConnection 请求连接
     * @param params            请求参数
     * @param charset           请求参数的编码,不传 utf-8
     * @param acceptCharset     接受编码,不传 utf-8
     * @return
     * @throws Exception
     */
    public static String doPost(HttpURLConnection httpURLConnection, Map<String, String> params, Charset charset, Charset acceptCharset)
            throws Exception {
        if (charset == null) {
            charset = Charset.forName("UTF-8");
        }
        if (acceptCharset == null) {
            acceptCharset = Charset.forName("UTF-8");
        }

        StringBuffer resp = new StringBuffer();

        OutputStream os = null;
        DataOutputStream dos = null;

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset.toString());
            os = httpURLConnection.getOutputStream();
            dos = new DataOutputStream(os);
            dos.write(urlEncode(params, charset).getBytes(charset));
            dos.flush();

            httpURLConnection.connect();

            is = httpURLConnection.getInputStream();
            isr = new InputStreamReader(is, acceptCharset);
            br = new BufferedReader(isr);

            String s = null;
            while ((s = br.readLine()) != null) {
                if (s.contains("\\u")) {
                    s = UnicodeUtil.decodeUnicode(s);
                }
                s = s.replaceAll("&nbsp;", " ").replaceAll("\\s{2,}", " ").replaceAll("\t|\n|\r|\b|\f", "");

                resp.append(s);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e2) {
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (Exception e2) {
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e2) {
                }
            }
            if (dos != null) {
                try {
                    dos.close();
                } catch (Exception e2) {
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e2) {
                }
            }
        }

        return resp.toString();
    }

    /**
     * post 请求
     *
     * @param url
     * @param params        请求参数
     * @param charset       请求参数的编码,不传 utf-8
     * @param acceptCharset 接受编码,不传 utf-8
     * @return
     * @throws Exception
     */
    public static String doPost(String url, Map<String, String> params, Charset charset, Charset acceptCharset) throws Exception {
        return doPost(getHttpURLConnection(url), params, charset, acceptCharset);
    }

    /**
     * post 请求
     *
     * @param url
     * @param params 请求参数
     * @return
     * @throws Exception
     */
    public static String doPost(String url, Map<String, String> params) throws Exception {
        return doPost(getHttpURLConnection(url), params, Charset.forName("UTF-8"), Charset.forName("UTF-8"));
    }

    /**
     * 获得host
     *
     * @param url 地址
     * @return
     */
    public static String getHost(String url) {
        int startIndex = -1;
        if (url.contains("http://")) {
            startIndex = url.indexOf("http://") + "http://".length();
        } else if (url.contains("https://")) {
            startIndex = url.indexOf("https://") + "https://".length();
        } else {
            return null;
        }

        int endIndex = url.indexOf("/", startIndex);
        if (endIndex == -1) {
            endIndex = url.length();
        }

        return url.substring(startIndex, endIndex);
    }

    /**
     * 参数编码,拼成字符串
     *
     * @param params  参数map
     * @param charset 编码格式
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String urlEncode(Map<String, String> params, Charset charset) throws UnsupportedEncodingException {
        if ((params == null) || (params.size() == 0)) {
            return "";
        }

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        StringBuffer query = new StringBuffer();
        for (String key : keys) {
            query.append(key).append("=").append(URLEncoder.encode(params.get(key), charset.toString())).append("&");
        }
        if (query.length() > 0) {
            query.deleteCharAt(query.length() - 1);
        }

        return query.toString();
    }
}
