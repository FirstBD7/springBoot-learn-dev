package com.example.springbootlearn.manage.vo;

/**
 * 前后端交互实体
 */
public class Result<T> {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示消息
     */
    private String msg;
    /**
     * 数据json
     */
    private T data;

    public static Result ok() {
        Result result = new Result<>();
        result.setCode(0);
        result.setMsg("ok");
        return result;
    }

    public static Result ok(Object data) {
        Result result = new Result<>();
        result.setCode(0);
        result.setMsg("ok");
        result.setData(data);
        return result;
    }

    public static Result ok(Integer code, Object data) {
        Result result = new Result<>();
        result.setCode(code);
        result.setMsg("ok");
        result.setData(data);
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result<>();
        result.setCode(999);
        result.setMsg(msg);
        return result;
    }

    public static Result unauthorized(String msg) {
        Result result = new Result<>();
        result.setCode(401);
        result.setMsg(msg);
        return result;
    }

    public static Result builder(Integer code, String msg) {
        Result result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result builder(Integer code, String msg, Object data) {
        Result result = new Result<>();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
