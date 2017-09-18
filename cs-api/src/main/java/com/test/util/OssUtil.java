package com.test.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class OssUtil {
//	private static final String ACCESS_KEY_ID = "JK1zoMfYOwiUHPBu";
//	private static final String ACCESS_KEY_SECRET = "zHFD0lI16ojDBO6uP3j1EJmDzQ5ZcS";
	private static final String ACCESS_KEY_ID = "LTAIosleaFcBGiBP";
	private static final String ACCESS_KEY_SECRET = "qtfzprwW4fjs4PfI5mMxrwifEwyxaW";

	private static final String NODE_HANGZHOU = ".oss-cn-hangzhou.aliyuncs.com"; // 公网节点
	private static final String NODE_HANGZHOU_INTERNAL = ".oss-cn-hangzhou-internal.aliyuncs.com"; // 私网节点
	private static String NODE_USING = NODE_HANGZHOU_INTERNAL; // 正在使用的节点

	private static String BUCKET_NAME = "zhongan"; // oss命名空间
	private static String HOST = "http://" + BUCKET_NAME + NODE_USING + "/"; // 请求主机
	private static String BASE_RESOURCE_PATH = "/" + BUCKET_NAME + "/"; // 请求资源根路径
	private static final String charset = "UTF-8"; // 默认使用编码

	static {
		usePublicNode();
	}

	/**
	 * 将使用节点换为公网节点,用于测试
	 */
	public static void usePublicNode() {
		BUCKET_NAME = PropertyUtil.getValue(PropertyUtil.IMAGE_BUCKET_NAME);
		NODE_USING = NODE_HANGZHOU;
		HOST = "http://" + BUCKET_NAME + NODE_USING + "/";
		BASE_RESOURCE_PATH = "/" + BUCKET_NAME + "/";
	}

	public static String getPublicHost() {
		return "http://" + BUCKET_NAME + NODE_HANGZHOU + "/";
	}

	public static String getPublicPrivate() {
		return "http://" + BUCKET_NAME + NODE_HANGZHOU_INTERNAL + "/";
	}

	/**
	 * 获得签名(简易版,忽视了2个参数——md5、header)
	 * 
	 * @param method
	 *            http动作（PUT,DELETE,POST,GET...）
	 * @param contentType
	 *            请求内容的类型,可为null
	 * @param date
	 *            此次操作的时间,且必须为 HTTP1.1 中支持的 GMT 格式
	 * @param ossHeaders
	 *            以"x-oss-”为前缀的http header
	 * @param resourcePath
	 *            用户想要访问的 OSS 资源
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	private static String computeSignature(String method, String contentType, String date, Map<String, String> ossHeaders,
			String resourcePath) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {

		if (contentType == null) {
			contentType = "";
		}
		if (ossHeaders == null) {
			ossHeaders = new HashMap<String, String>();
		}

		resourcePath = resourcePath.replaceAll("//", "/");

		StringBuffer data = new StringBuffer(method.toUpperCase()).append("\n\n").append(contentType).append("\n").append(date)
				.append("\n").append(buildCanonicalizedOSSHeaders(ossHeaders)).append(resourcePath);

		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(new SecretKeySpec(ACCESS_KEY_SECRET.getBytes(charset), "HmacSHA1"));

		return "OSS " + ACCESS_KEY_ID + ":" + new String(Base64.encodeBase64(mac.doFinal(data.toString().getBytes(charset))));
	}

	private static String buildCanonicalizedOSSHeaders(Map<String, String> ossHeaders) {
		List<String> keys = new ArrayList<String>(ossHeaders.keySet());
		Collections.sort(keys);

		StringBuffer ret = new StringBuffer();
		for (String key : keys) {
			ret.append(key.toLowerCase().replaceAll(" ", "")).append(":").append(ossHeaders.get(key).replaceAll(" ", "")).append("\n");
		}

		return ret.toString();
	}

	/**
	 * 上传单个文件到oss
	 * 
	 * @param key
	 *            文件路径
	 * @param contentType
	 *            内容类型
	 * @param contents
	 *            内容
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	public static boolean putObject(String key, String contentType, byte[] contents) throws InvalidKeyException, NoSuchAlgorithmException,
			MalformedURLException, IOException {

		String date = TimeUtil.getRfc822DateFormat(new Date());

		HttpURLConnection httpURLConnection = HttpUtil.getHttpURLConnection(HOST + key);
		httpURLConnection.setRequestProperty("Date", date);
		httpURLConnection.setRequestProperty("Authorization", computeSignature("PUT", contentType, date, null, BASE_RESOURCE_PATH + key));
		httpURLConnection.setRequestProperty("Cache-Control", null);

		httpURLConnection.setRequestMethod("PUT");
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setRequestProperty("Content-Length", contents.length + "");
		if (contentType != null && contentType.length() > 0) {
			httpURLConnection.setRequestProperty("Content-Type", contentType);
		}

		OutputStream os = null;
		try {
			os = httpURLConnection.getOutputStream();
			os.write(contents);
		} catch (Exception e) {
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (Exception e2) {
				}
			}
		}

		httpURLConnection.connect();

		InputStream inputStream = httpURLConnection.getInputStream();
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (Exception e) {
			}
		}

		if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			return true;
		}

		return false;
	}

	/**
	 * 复制文件
	 * 
	 * @param key
	 *            目标路径
	 * @param copySource
	 *            源文件
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */
	public static boolean copyObject(String key, String copySource) throws MalformedURLException, IOException, InvalidKeyException,
			NoSuchAlgorithmException {
		String date = TimeUtil.getRfc822DateFormat(new Date());
		Map<String, String> ossHeaders = new HashMap<String, String>();
		ossHeaders.put("x-oss-copy-source", BASE_RESOURCE_PATH + copySource);

		HttpURLConnection httpURLConnection = HttpUtil.getHttpURLConnection(HOST + key);
		httpURLConnection.setRequestProperty("Date", date);
		httpURLConnection.setRequestProperty("Authorization", computeSignature("PUT", null, date, ossHeaders, BASE_RESOURCE_PATH + key));
		httpURLConnection.setRequestProperty("x-oss-copy-source", BASE_RESOURCE_PATH + copySource);
		httpURLConnection.setRequestProperty("Cache-Control", null);
		httpURLConnection.setRequestProperty("Content-Length", "0");

		httpURLConnection.setRequestMethod("PUT");
		httpURLConnection.setDoOutput(true);

		OutputStream os = null;
		try {
			os = httpURLConnection.getOutputStream();
			os.write("".getBytes(Charset.forName("UTF-8")));
		} catch (Exception e) {
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (Exception e2) {
				}
			}
		}

		httpURLConnection.connect();

		InputStream inputStream = httpURLConnection.getInputStream();
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (Exception e) {
			}
		}

		if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			return true;
		}

		return false;
	}

	/**
	 * 删除oss上的一个文件
	 * 
	 * @param key
	 *            文件路径
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */
	public static boolean deleteObject(String key) throws MalformedURLException, IOException, InvalidKeyException, NoSuchAlgorithmException {
		String date = TimeUtil.getRfc822DateFormat(new Date());

		HttpURLConnection httpURLConnection = HttpUtil.getHttpURLConnection(HOST + key);
		httpURLConnection.setRequestProperty("Date", date);
		httpURLConnection.setRequestProperty("Authorization", computeSignature("DELETE", null, date, null, BASE_RESOURCE_PATH + key));

		httpURLConnection.setRequestMethod("DELETE");

		httpURLConnection.connect();

		InputStream is = httpURLConnection.getInputStream();
		if (is != null) {
			try {
				is.close();
			} catch (Exception e) {
			}
		}

		if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT) {
			return true;
		}
		return false;
	}

	/**
	 * 获得oss一个文件的流
	 * 
	 * @param key
	 *            文件路径
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	@Deprecated
	public static InputStream getObject(String key) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		String date = TimeUtil.getRfc822DateFormat(new Date());

		HttpURLConnection httpURLConnection = HttpUtil.getHttpURLConnection(HOST + key);
		httpURLConnection.setRequestProperty("Date", date);
		httpURLConnection.setRequestProperty("Authorization", computeSignature("GET", null, date, null, BASE_RESOURCE_PATH + key));

		httpURLConnection.connect();

		return httpURLConnection.getInputStream();
	}

	public static InputStream getObject(String key, String bucketName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		String date = TimeUtil.getRfc822DateFormat(new Date());

		HttpURLConnection httpURLConnection = HttpUtil.getHttpURLConnection("http://" + bucketName + NODE_HANGZHOU + "/" + key);
		httpURLConnection.setRequestProperty("Date", date);
		httpURLConnection.setRequestProperty("Authorization", computeSignature("GET", null, date, null, "/" + bucketName + "/" + key));

		httpURLConnection.connect();

		return httpURLConnection.getInputStream();
	}

	/**
	 * 获取oss object 摘要的列表
	 * 
	 * @param prefix
	 *            前缀,可为null
	 * @param marker
	 *            设定结果从Marker之后按字母排序的第一个开始返回, 可为null
	 * @return
	 * @throws Exception
	 */
	public static List<OSSObjectSummary> list(String prefix, String marker) throws Exception {
		String date = TimeUtil.getRfc822DateFormat(new Date());

		StringBuffer param = new StringBuffer("?");
		if (prefix != null) {
			if (prefix.startsWith("/")) {
				prefix = prefix.substring(1);
			}
			param.append("prefix=").append(urlEncode(prefix)).append("&");
		}
		if (marker != null) {
			param.append("marker=").append(urlEncode(marker)).append("&");
		}
		param.deleteCharAt(param.length() - 1);

		HttpURLConnection httpURLConnection = HttpUtil.getHttpURLConnection(HOST + param.toString());
		httpURLConnection.setRequestProperty("Date", date);
		httpURLConnection.setRequestProperty("Authorization", computeSignature("GET", null, date, null, BASE_RESOURCE_PATH));

		httpURLConnection.connect();

		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		List<OSSObjectSummary> ossObjectSummaries = new ArrayList<OSSObjectSummary>();
		try {
			is = httpURLConnection.getInputStream();

			if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				isr = new InputStreamReader(is);
				br = new BufferedReader(isr);

				boolean isTruncated = false;
				String nextMarker = null;
				OSSObjectSummary ossObjectSummary = null;
				String s = null;
				while ((s = br.readLine()) != null) {
					int beginIndex = s.indexOf(">") + 1;
					if (beginIndex == -1) {
						beginIndex = 0;
					}
					int endIndex = s.indexOf("<", beginIndex);
					if (endIndex == -1) {
						endIndex = s.length();
					}
					String text = s.substring(beginIndex, endIndex);
					s = s.toLowerCase();

					if (s.contains("<istruncated>")) {
						isTruncated = Boolean.valueOf(text);
					} else if (s.contains("<nextmarker>")) {
						nextMarker = text;
					} else if (s.contains("<contents>")) {
						ossObjectSummary = new OSSObjectSummary();
					} else if (s.contains("</contents>")) {
						ossObjectSummaries.add(ossObjectSummary);
						ossObjectSummary = null;
					} else if (s.contains("<key>")) {
						ossObjectSummary.setKey(text);
					} else if (s.contains("<lastmodified>")) {
						ossObjectSummary.setLastModified(text);
					} else if (s.contains("<size>")) {
						ossObjectSummary.setSize(text);
					}
				}

				if (isTruncated) {
					ossObjectSummaries.addAll(list(prefix, nextMarker));
				}
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

		return ossObjectSummaries;
	}

	/**
	 * oss的url encode
	 * 
	 * @param value
	 *            要加密的值
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String urlEncode(String value) throws UnsupportedEncodingException {
		return URLEncoder.encode(value, charset).replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
	}

	/**
	 * 调用图片处理服务,并返回处理好的图片的流
	 * 
	 * @param key
	 *            文件路径
	 * @param transform
	 *            转化字符
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */
	public static InputStream callImgService(String key, String transform) throws MalformedURLException, IOException, InvalidKeyException,
			NoSuchAlgorithmException {
		key += "@" + transform;

		String date = TimeUtil.getRfc822DateFormat(new Date());

		HttpURLConnection httpURLConnection = HttpUtil.getHttpURLConnection("http://imgop.trend-data.cn/" + key);
		httpURLConnection.setRequestProperty("Date", date);
		httpURLConnection.setRequestProperty("Authorization", computeSignature("GET", null, date, null, BASE_RESOURCE_PATH + key));

		httpURLConnection.connect();

		return httpURLConnection.getInputStream();
	}
}
