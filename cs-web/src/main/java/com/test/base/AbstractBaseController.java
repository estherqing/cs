package com.test.base;

import com.test.exception.I18NSupportException;
import com.test.model.ZaUser;
import com.test.util.DataPage;
import com.test.util.Page;
import com.test.util.logger.LoggerWrapper;
import com.test.util.logger.LoggerWrapperAware;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Author: jiyanbin@zafh.com.cn JDK: 1.8 Created on 15/11/9.
 */
@SuppressWarnings("all")
public abstract class AbstractBaseController implements LoggerWrapperAware {

	protected static final String SESSION_KEY_USER = "CURRENT_USER";
	
	protected static final String MAIL_REG = "^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";
	
	protected static final String MOBILE_PHONE_FAX_REG = "(\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$";
	
	// @Autowired
	// @Qualifier("messageSource")
	protected ResourceBundleMessageSource messageResource;// =
															// ApplicationContextUtils.getBean("messageSource",ResourceBundleMessageSource.class);

	protected LoggerWrapper logger;

	protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	protected DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	protected DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
	protected DateTimeFormatter formatterFullTime = DateTimeFormatter.ofPattern("HH:mm:ss");

	public static final String RESULT_STATUS = "status";
	public static final String RESULT_MESSAGE = "message";
	public static final String RESULT_DATA = "data";
	public static final String RESULT_ROWS = "rows";
	public static final String RESULT_OFFSET = "page";
	public static final String RESULT_TOTAL_COUNT = "totalCount";
	public static final String RESULT_PAGE_SIZE = "pageSize";

	public static final String CT_JSON = "application/json; charset=utf-8";

	@Override
	public void setLogger(LoggerWrapper logger) {
		this.logger = logger;
	}

	protected ZaUser getZaUser(HttpServletRequest request) {
		return (ZaUser) request.getSession().getAttribute(SESSION_KEY_USER);
	}

	protected DataPage convertPage(Page page) {
		DataPage dataPage = new DataPage();
		dataPage.setData(page.getDatas());
		dataPage.setCurrentPage(page.getPageNow());
		dataPage.setPageSize(page.getPageSize());
		dataPage.setTotalItem(page.getDataCount());
		return dataPage;
	}

	/**
	 * 处理返回给client端的值，不包含内容体
	 *
	 * @return
	 */
	protected void responseSuccess(HttpServletResponse response, String... messageI18NKey) throws Exception {
		Map resultMap = new HashMap();
		resultMap.put(RESULT_STATUS, WebConstants.SUCCESS);
		resultMap.put(RESULT_MESSAGE, "");
		if (messageI18NKey.length > 0)
			resultMap.put(RESULT_MESSAGE,
					messageResource.getMessage(messageI18NKey[0], null, messageI18NKey[0], LocaleContextHolder.getLocale()));
		ObjectMapper objectMapper = new ObjectMapper();
		response.setContentType(CT_JSON);
		objectMapper.writeValue(response.getWriter(), resultMap);
		response.getWriter().flush();
	}

	/**
	 * 处理返回给client端的值，包含内容体
	 *
	 * @return
	 */
	protected void responseSuccess(HttpServletResponse response, Object body, String... messageI18NKey) throws Exception {
		Map resultMap = new HashMap();
		resultMap.put(RESULT_STATUS, WebConstants.SUCCESS);
		resultMap.put(RESULT_MESSAGE, "");
		if (messageI18NKey.length > 0)
			resultMap.put(RESULT_MESSAGE,
					messageResource.getMessage(messageI18NKey[0], null, messageI18NKey[0], LocaleContextHolder.getLocale()));
		resultMap.put(RESULT_DATA, body);
		ObjectMapper objectMapper = new ObjectMapper();
		response.setContentType(CT_JSON);
		objectMapper.writeValue(response.getWriter(), resultMap);
		response.getWriter().flush();
	}

	/**
	 * 处理返回给client端的分页值，包含内容体
	 *
	 * @param response
	 * @param offset
	 * @param totalCount
	 * @param pageSize
	 * @param body
	 * @param messageI18NKey
	 * @throws Exception
	 */
	protected void responseSuccess(HttpServletResponse response, int offset, int totalCount, int pageSize, List<Object> body,
			String... messageI18NKey) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Map resultMap = new HashMap();
		resultMap.put(RESULT_STATUS, WebConstants.SUCCESS);
		resultMap.put(RESULT_OFFSET, offset);
		resultMap.put(RESULT_PAGE_SIZE, pageSize);
		resultMap.put(RESULT_TOTAL_COUNT, totalCount);
		resultMap.put(RESULT_ROWS, body);
		response.setContentType(CT_JSON);
		if (messageI18NKey.length > 0)
			resultMap.put(RESULT_MESSAGE,
					messageResource.getMessage(messageI18NKey[0], null, messageI18NKey[0], LocaleContextHolder.getLocale()));
		objectMapper.writeValue(response.getWriter(), resultMap);
		response.getWriter().flush();
	}

	/**
	 * 返回error message给client
	 *
	 * @param response
	 * @param errorMessage
	 * @throws Exception
	 */
	protected void responseError(HttpServletResponse response, String errorMessage) throws Exception {
		Map resultMap = new HashMap();
		resultMap.put(RESULT_STATUS, WebConstants.ERROR);
		resultMap.put(RESULT_MESSAGE, errorMessage);
		resultMap.put(RESULT_DATA, "");
		ObjectMapper objectMapper = new ObjectMapper();
		response.setContentType(CT_JSON);
		objectMapper.writeValue(response.getWriter(), resultMap);
		response.getWriter().flush();
	}

	// @ExceptionHandler(Exception.class)
	protected <E extends Exception> void handleException(HttpServletResponse response, E ex) throws Exception {
		logger.error(() -> "error:", ex);
		String errorMessage = "";
		if (ex instanceof I18NSupportException) {
			I18NSupportException ex1 = (I18NSupportException) ex;
			errorMessage = messageResource.getMessage(ex1.getI18nKey(), ex1.getPlaceHolders(), ex1.getDefaultMessage(),
					LocaleContextHolder.getLocale());
		} else
			errorMessage = messageResource.getMessage("err.cls.bss.system.error", null, "System Error!", LocaleContextHolder.getLocale());

		Map resultMap = new HashMap();
		resultMap.put(RESULT_STATUS, WebConstants.ERROR);
		resultMap.put(RESULT_MESSAGE, errorMessage);
		resultMap.put(RESULT_DATA, "");
		ObjectMapper objectMapper = new ObjectMapper();
		response.setContentType(CT_JSON);
		objectMapper.writeValue(response.getWriter(), resultMap);
		response.getWriter().flush();
	}

	protected String getI18nMsg(String i18nKey) throws Exception {
		if (Objects.isNull(i18nKey) || i18nKey.isEmpty())
			return "";
		return messageResource.getMessage(i18nKey, null, i18nKey, LocaleContextHolder.getLocale());
	}

	protected String getI18nMsg(String i18nKey, Object[] placeHolders) throws Exception {
		if (Objects.isNull(i18nKey) || i18nKey.isEmpty())
			return "";
		return messageResource.getMessage(i18nKey, placeHolders, i18nKey, LocaleContextHolder.getLocale());
	}

	protected String formatLocalDateTime(LocalDateTime localDateTime, DateTimeFormatter formatter) {
		try {
			if (Objects.isNull(localDateTime))
				return "";
			return localDateTime.format(formatter);
		} catch (Exception e) {
			logger.error(() -> "Error", e);
			return "";
		}
	}

	protected String formatLocalDate(LocalDate localDate, DateTimeFormatter formatter) {
		try {
			if (Objects.isNull(localDate))
				return "";
			return localDate.format(formatter);
		} catch (Exception e) {
			logger.error(() -> "Error", e);
			return "";
		}
	}

	protected String formatLocalTime(LocalTime localTime, DateTimeFormatter formatter) {
		try {
			if (Objects.isNull(localTime))
				return "";
			return localTime.format(formatter);
		} catch (Exception e) {
			logger.error(() -> "Error", e);
			return "";
		}
	}
}
