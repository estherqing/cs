package com.test;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import org.apache.http.entity.mime.MultipartEntity;
//import org.apache.http.entity.mime.content.FileBody;

/**
 * Created by zhangjw on 2016/7/9.
 */
public class HttpClientUtil {

	private static final String APPLICATION_JSON = "application/json";
	private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

	@SuppressWarnings("deprecation")
	public static String sendHttpsGet(String url) {

		String content = null;
		HttpClient client = wrapClient(new DefaultHttpClient());
		try {

			HttpGet get = new HttpGet();
			get.setURI(new URI(url));
			HttpResponse response = client.execute(get);
			if (null != response) {
				content = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return content;
	}

	@SuppressWarnings("deprecation")
	private static HttpClient wrapClient(HttpClient httpClient) {

		try {
			SSLContext ctx = SSLContext.getInstance("TLS");

			X509TrustManager tm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};

			ctx.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx);
			ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = httpClient.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			// 设置要使用的端口，默认是443
			sr.register(new Scheme("https", ssf, 443));
			return new DefaultHttpClient(ccm, httpClient.getParams());

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}


	/**
	 * 发送普通http的post请求
	 *
	 * @param url
	 *            请求地址
	 * @param jsonParam
	 *            请求参数（Json格式）
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String sendPost(String url, String jsonParam) {
		HttpClient httpClient = null;
		HttpPost httpPost;
		String result = null;
		try {
			httpClient = new DefaultHttpClient();
			httpPost = new HttpPost(url);
			httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);

			// 设置参数
			StringEntity se = new StringEntity(jsonParam, HTTP.UTF_8);
			se.setContentType(CONTENT_TYPE_TEXT_JSON);
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
			httpPost.setEntity(se);

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

	/**
	 * 发送普通http的post请求
	 *
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数（map格式）
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String sendHttpPost(String url, Map<String, Object> params) {
		HttpClient httpClient = null;
		HttpPost httpPost;
		String result = null;
		try {
			httpClient = new DefaultHttpClient();
			httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				nvps.add(new BasicNameValuePair(key, params.get(key).toString()));
			}

			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

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

	/**
	 * 发送普通http的post请求
	 *
	 * @param url
	 *            请求地址
	 * @param fileName
	 *            文件名
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String sendFilePost(String url, String fileName) {
		HttpClient httpClient = null;
		HttpPost httpPost;
		String result = null;
		try {
			httpClient = new DefaultHttpClient();
			httpPost = new HttpPost(url);

			FileBody file = new FileBody(new File(fileName));
			MultipartEntity reqEntity = new MultipartEntity();
			reqEntity.addPart("file", file);// file1为请求后台的File upload;属性
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

	/**
	 * 发送普通http的post请求
	 *
	 * @param url
	 *            请求地址
	 * @param fileNames
	 *            文件名
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String sendFilesPost(String url, String fileNames) {
		HttpClient httpClient = null;
		HttpPost httpPost;
		String result = null;
		try {
			httpClient = HttpClients.createDefault(); // new DefaultHttpClient();
			httpPost = new HttpPost(url);

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
