package kr.healthcare.timemanagerapi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  ResponseFailMessage {

    H000001("이미 가입된 학번입니다."),
    H000002("이미 사용된 이메일 입니다.");

    private String message;

}
