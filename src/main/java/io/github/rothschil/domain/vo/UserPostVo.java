package io.github.rothschil.domain.vo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserPostVo {

    @NotNull(message = "用户id不能为空")
    private String id;

    @NotNull(message = "用户账号不能为空")
    @Size(min = 3, max = 11, message = "账号长度必须是6-11个字符")
    private String name;

    @NotNull(message = "用户密码不能为空")
    @Size(min = 3, max = 11, message = "密码长度必须是6-16个字符")
    private String pass;
}
