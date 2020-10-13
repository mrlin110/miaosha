package com.mrlin.miaosha.vo.input;

import javax.validation.constraints.NotBlank;

public class LoginIVo {
    @NotBlank(message = "密码必填")
    private String password;
    @NotBlank(message = "手机必填")
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
