package com.mrlin.miaosha.vo.input;

import com.mrlin.miaosha.validator.IsMobile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginIVo {
    @NotBlank(message = "密码必填")
    private String password;

    @NotNull(message = "手机必填")
    @IsMobile
    private String mobile;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
