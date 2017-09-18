package com.test.exception;


import java.util.Arrays;

/**
 * Created by jiyanbin on 15/7/2.
 */
public class I18NSupportException extends Exception {

    private Code code = Code.COMMON_ERROR;

    private String i18nKey;
    private String defaultMessage;
    private Object[] placeHolders;

    public I18NSupportException(String i18nKey) {
        this(i18nKey, null, null, null);
    }

    public I18NSupportException(String i18nKey, String defaultMessage) {
        this(i18nKey, null, defaultMessage, null);
    }

    public I18NSupportException(String i18nKey, Object[] placeHolders) {
        this(i18nKey, null, null, placeHolders);
    }

    public I18NSupportException(String i18nKey, Throwable cause) {
        this(i18nKey, cause, null, null);
    }

    public I18NSupportException(String i18nKey, Throwable cause, Object[] placeHolders) {
        this(i18nKey, cause, null, placeHolders);
    }

    public I18NSupportException(String i18nKey, String defaultMessage, Object[] placeHolders) {
        this(i18nKey, null, defaultMessage, placeHolders);
    }

    public I18NSupportException(String i18nKey, Throwable cause, String defaultMessage, Object[] placeHolders) {
        super(defaultMessage, cause);
        this.i18nKey = i18nKey;
        this.defaultMessage = defaultMessage;
        this.placeHolders = placeHolders;
    }

    public String getI18nKey() {
        return i18nKey;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public Object[] getPlaceHolders() {
        return placeHolders;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }


    public static enum Code {
        COMMON_ERROR(20000),
        //用户未登陆
        USER_NO_LOGIN(30000);

        private int code;
        /**
         * message国际化key
         */
        private String messageKey;

        private Code(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{" +
                "i18nKey='" + i18nKey + '\'' +
                ", defaultMessage='" + defaultMessage + '\'' +
                ", placeHolders=" + Arrays.toString(placeHolders) +
                '}';
    }
}
