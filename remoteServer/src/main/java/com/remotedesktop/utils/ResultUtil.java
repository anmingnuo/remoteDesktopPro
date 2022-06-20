package com.remotedesktop.utils;

import com.remotedesktop.entities.Result;

public class ResultUtil {



    public static Result success(Object data) {
        return new Result(data);
    }

    public static Result error(Integer code, String msg) {
        return new Result(code,msg);
    }
}
