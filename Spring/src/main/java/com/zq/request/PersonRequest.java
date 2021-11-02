package com.zq.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author zqian
 * @date 2021/4/6
 */
@Data
public class PersonRequest {
    @Max(value = 150, message = "用户年龄不能大于150岁")
    @Min(value = 0, message = "用户年龄不能小于0岁")
    private Integer age;
    @NotBlank(message = "用户名不能为空")
    private String name;
    @Pattern(regexp = "^\\\\s*\\\\w+(?:\\\\.{0,1}[\\\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\\\.[a-zA-Z]+\\\\s*$",
            message = "邮箱格式不正确")
    private String email;
}
