package org.txazo.java.tools.mvc.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tuxiaozhou
 * @date 2020/9/28
 */
@Data
public class Response<T> implements Serializable {

    public static final int SUCCESS = 200;

    public static final int ERROR = 500;

    private Integer code;

    private T data;

    private String message;

    private Response(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static <T> Response<T> success() {
        return new Response<T>(SUCCESS, null, null);
    }

    public static <T> Response<T> success(T data) {
        return new Response<T>(SUCCESS, null, null);
    }

    public static <T> Response<T> error() {
        return new Response<T>(ERROR, null, null);
    }

    public static <T> Response<T> error(String message) {
        return new Response<T>(ERROR, null, message);
    }

}
