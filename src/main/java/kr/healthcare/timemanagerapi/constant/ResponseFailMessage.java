package kr.healthcare.timemanagerapi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  ResponseFailMessage {

    FAIL("FAIL"),
    SUCCESS("SUCCESS"),
    H000001("이미 가입된 학번입니다."),
    H000002("이미 사용된 이메일 입니다."),
    H000003("이미 가입된 학번이거나 이미 사용되고 있는 이메일 입니다."),
    H000004("아이디 또는 비밀번호를 확인해주세요.");

    private String message;

}
