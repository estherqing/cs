package com.test.manager;

import com.test.util.OssUtil;
import com.test.util.PropertyUtil;
import com.test.util.ResultDO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.InputStream;

public class ZaUploadManager {
    private static final String ZACDN_DOMAIN = "http://zacdn.cgw360.com/";

    //For Images
    private static final String OLD_ZHONGAN_IMAGE_DOMAIN = "http://zacdn-image.cgw360.com/";
    private static String ZHONGAN_IMAGE_DOMAIN = "http://zacdn-image.cgw360.com/";
    private static final String CHEGUO_IMAGE_DOMAIN = "http://cheguo-image.cheguo.com/";

    //oss bukcets
    private static final String OLD_ZHONGAN_BUCKET = "zhongan";//兼容老的下载
    private static String ZHONGAN_BUCKET = "zhongan";
    private static final String CHEGUO_BUCKET = "cheguo";

    private static final Integer WIDTH = 1024;//图片裁剪宽度
    private static final Integer HEIGHT = 1024;//图片裁剪高度

    static {
        useBucket();
    }

    private static void useBucket(){
        ZHONGAN_BUCKET = PropertyUtil.getValue(PropertyUtil.IMAGE_BUCKET_NAME);
        ZHONGAN_IMAGE_DOMAIN = PropertyUtil.getValue(PropertyUtil.IMAGE_DOMAIN_CDN);
    }

    public static ResultDO<String> uploadFile(UploadConstant.Systype sysType, UploadConstant.FileNamespace fileNamespace, byte[] contents, String fileName) {
        // 参数校验
        if (sysType == null || StringUtils.isEmpty(sysType.name())) {
            return new ResultDO<String>("不允许的系统类型");
        }
        if (fileNamespace == null || StringUtils.isEmpty(fileNamespace.name())) {
            return new ResultDO<String>("不允许的文件空间");
        }
        if (contents == null || contents.length == 0) {
            return new ResultDO<String>("上传内容不能为空");
        }
        if (StringUtils.isEmpty(fileName)) {
            return new ResultDO<String>("文件名不能为空");
        }
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (StringUtils.isEmpty(fileExtension)) {
            return new ResultDO<String>("无法识别的文件后缀名 " + fileExtension);
        }
        // 文件类型校验
        String contentType = getContentType(fileExtension);

        if (StringUtils.isEmpty(contentType)) {
            return new ResultDO<String>("不支持的上传类型");
        }

        String ossPath = "cgw360/" + sysType.getKey() + "/" + fileNamespace.getKey() + "/" + fileName;
        try {
            boolean putRet = OssUtil.putObject(ossPath, contentType, contents);
            if (putRet) {
                return new ResultDO<String>(true, "", getCdnDomain(contentType) + ossPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResultDO<String>("上传失败");
    }

    private static String getCdnDomain(String contentType) {
        if ("image/jpeg".equalsIgnoreCase(contentType) || !"zhongan".equals(ZHONGAN_BUCKET)) {
            return ZHONGAN_IMAGE_DOMAIN;
        }
        return ZACDN_DOMAIN;
    }

    private static String getContentType(String fileExtension) {
        String contentType = "";
        if (fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("png")) {
            contentType = "image/jpeg";
        }
        if (fileExtension.equalsIgnoreCase("pdf")) {
            contentType = "application/pdf";
        }
        if (fileExtension.equalsIgnoreCase("doc") || fileExtension.equalsIgnoreCase("docx")) {
            contentType = "application/msword";
        }
        if (fileExtension.equalsIgnoreCase("zip")) {
            contentType = "application/octet-stream";
        }
        if (fileExtension.equalsIgnoreCase("mp4")) {
            contentType = "video/mpeg";
        }
        //video/mpeg      video/vnd.rn-realvideo
        return contentType;
    }

    public static ResultDO<InputStream> downloadFile(String url) {
        InputStream is = null;
        try {
            if (StringUtils.isEmpty(url)) {
                return new ResultDO<InputStream>("下载地址不能空 ");
            }
            /*
			if (!url.contains(CDN_DOMAIN) && !url.contains(CDN_DOMAIN_IMAGE)) {
				return new ResultDO<InputStream>("下载地址域名不正确");
			}*/

            //http://cheguo-image.cheguo.com/files/2016-10-14/201610141512065592362.JPG
            //http://zacdn-image.cgw360.com/cgw360/cls/credit/560058f7-0536-4c2e-9659-71ac08b6b4ec.png
            if (url.contains(ZACDN_DOMAIN)) {
                is = OssUtil.getObject(url.replace(ZACDN_DOMAIN, ""), OLD_ZHONGAN_BUCKET);
            } else if (url.contains(OLD_ZHONGAN_IMAGE_DOMAIN)) {
                is = OssUtil.getObject(url.replace(OLD_ZHONGAN_IMAGE_DOMAIN, ""), OLD_ZHONGAN_BUCKET);//兼容老的图片
            } else if (url.contains(ZHONGAN_IMAGE_DOMAIN)) {
                is = OssUtil.getObject(url.replace(ZHONGAN_IMAGE_DOMAIN, ""), ZHONGAN_BUCKET);
            } else if (url.contains(CHEGUO_IMAGE_DOMAIN)) {
                is = OssUtil.getObject(url.replace(CHEGUO_IMAGE_DOMAIN, ""), CHEGUO_BUCKET);
            } else if (url.contains(OssConfig.CHERONG_IMAGE_DOMAIN)) {
                is = OssUtil.getObject(url.replace(OssConfig.CHERONG_IMAGE_DOMAIN, ""), OssConfig.CHERONG_BUCKET);
            }else if (url.contains(OssConfig.SHENGAN_IMAGE_DOMAIN)) {
                is = OssUtil.getObject(url.replace(OssConfig.SHENGAN_IMAGE_DOMAIN, ""), OssConfig.SHENGAN_BUCKET);
            }
            return new ResultDO<InputStream>(true, is);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDO<InputStream>(e.getMessage());
        }
		/*finally {
			try {
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
    }

    /**
     * 按高度宽度裁剪图片
     *
     * @param url
     * @param width
     * @param height
     * @return
     */
    public static ResultDO<InputStream> downloadFile(String url, Integer width, Integer height) {
        InputStream is = null;
        try {
            if (StringUtils.isEmpty(url)) {
                return new ResultDO<InputStream>("下载地址不能空 ");
            }
            String fileExtension = url.substring(url.lastIndexOf(".") + 1);
            // 图片压缩
            if (fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("png")) {
                url = url+"?x-oss-process=image/resize,w_"+width+",h_"+height;
            }
            if (url.contains(ZACDN_DOMAIN)) {
                is = OssUtil.getObject(url.replace(ZACDN_DOMAIN, ""), ZHONGAN_BUCKET);
            } else if (url.contains(ZHONGAN_IMAGE_DOMAIN)) {
                is = OssUtil.getObject(url.replace(ZHONGAN_IMAGE_DOMAIN, ""), ZHONGAN_BUCKET);
            } else if (url.contains(CHEGUO_IMAGE_DOMAIN)) {
                is = OssUtil.getObject(url.replace(CHEGUO_IMAGE_DOMAIN, ""), CHEGUO_BUCKET);
            }
            return new ResultDO<InputStream>(true, is);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDO<InputStream>(e.getMessage());
        }
    }

    /**
     * 按系统默认值裁剪
     * height=1024 width=1024
     * @param url
     * @return
     */
    public static ResultDO<InputStream> downloadFileForResize(String url) {
        return downloadFile(url, WIDTH, HEIGHT);
    }

    public static void main(String[] args) throws Exception {
        String url = "http://shengan-image.cheguo.com/cgw360/cls/credit/e6ee52a7-8878-48c2-b086-28aa7f0d5283.png";
        InputStream is = downloadFile(url).getModule();
        File file = new File("/home/chen/aaabbbcc.png");
        FileUtils.copyInputStreamToFile(is, file);
        System.out.println("finished");
    }
}
