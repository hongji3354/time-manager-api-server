package kr.healthcare.timemanagerapi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.omg.CORBA.PRIVATE_MEMBER;

@Getter
@AllArgsConstructor
public enum Authority {

    S("최고관리자"),
    A("관리자"),
    U("사용자");

    private String auth;
}
