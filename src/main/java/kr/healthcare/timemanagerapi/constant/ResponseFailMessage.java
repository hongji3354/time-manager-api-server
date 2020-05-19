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
    H000004("아이디 또는 비밀번호를 확인해주세요."),
    H000005("로그인이 필요한 기능입니다."),
    H000006("로그인 정보가 잘못되었습니다. 로그아웃 후 로그인해주세요."),
    H000007("접근 권한이 없습니다."),
    H000008("존재하지 않는 학번입니다."),
    H000009("관리자를 승인할 수 없습니다.");

    private String message;

}
