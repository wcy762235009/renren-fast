package io.renren.modules.app.controller.support.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @Author: wcy
 * @CreateTime: 2025-03-26
 * @Description:
 * @Version: 1.0
 */
@Builder
@Data
@AllArgsConstructor
public class LoginVO {
    long userId;

    String token;
}
