package com.example.developedscheduler.domain.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

//유저 요청 dto
@Getter
@AllArgsConstructor
public class UserRequestDto {

    @NotBlank(message = "사용자 이름은 필수 입력 항목입니다.")
    @Size(max=5, message = "이름은 5글자 이내로 작성해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣\\s]+$", message = "사용자 이름에 특수문자를 사용하실 수 없습니다.")
    private final String name;

    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private final String email;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
            message = "비밀번호는 영문, 숫자, 특수문자를 포함해야 합니다.")
    private final String password;

}
