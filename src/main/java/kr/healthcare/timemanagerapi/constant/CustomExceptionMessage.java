package kr.healthcare.timemanagerapi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomExceptionMessage {

    TokenEmptyException("Token 값이 안들어 왔다!!!!!!!!!!!!!");

    private String message;
}
