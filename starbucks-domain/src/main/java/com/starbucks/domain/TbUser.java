package com.starbucks.domain;

import com.starbucks.commons.utils.RegexpUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Data
public class TbUser implements Serializable {

    private long id;
    @NotBlank(message = "用户名不能为空")
    @Length(min = 6, max = 20, message = "用户名长度必须在6-20之间")
    private String username;

    @Length(min = 6, max = 20, message = "密码长度必须在6-20之间")
    private String password;

    @Pattern(regexp = RegexpUtils.PHONE, message = "手机号码格式不正确")
    private String phone;

    @Pattern(regexp = RegexpUtils.EMAIL, message = "邮箱格式不正确")
    private String email;
    private Date created;
    private Date updated;
}
