package com.wms.common;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DzcGood
 * @date 2023/7/22 - 20:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * “成功” “失败”
     */
    private String message;
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 数据
     */
    private Object data;

    public static Result result(Integer code, String message, Long total, Object data) {
        return new Result(code, message, total, data);
    }

    /**
     * 请求失败
     * @return
     */
    public static Result fail() {
        return new Result(400, "失败", 0L, null);
    }

    /**
     * 请求成功
     * @param total
     * @param data
     * @return
     */
    public static Result suc(Long total, Object data) {
        return new Result(200, "成功", total, data);
    }
}
