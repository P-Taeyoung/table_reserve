package com.zerobase.table_reserve.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_EXISTS_ID(HttpStatus.BAD_REQUEST, "이미 가입한 아이디입니다."),
    INVALID_EMAIL_AUTH_KEY(HttpStatus.BAD_REQUEST, "유효한 인증코드가 아닙니다."),
    ALREADY_EMAIL_AUTH(HttpStatus.BAD_REQUEST, "이미 인증되었습니다."),
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "잘못된 아이디입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 잘못되었습니다."),



    NOT_FOUND_SHOP(HttpStatus.BAD_REQUEST, "매장이 존재하지 않습니다."),
    IMPOSSIBLE_RESERVE_TIME(HttpStatus.BAD_REQUEST, "예약 불가능한 시간입니다."),
    FULLY_RESERVED(HttpStatus.BAD_REQUEST, "예약 정원 마감입니다."),
    RESERVATION_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 해당시간에 예약된 내역이 있습니다."),

    NOT_FOUND_RESERVATION(HttpStatus.BAD_REQUEST, "예약 정보가 존재하지 않습니다."),
    NOT_FOUND_REVIEW(HttpStatus.BAD_REQUEST, "리뷰 정보가 존재하지 않습니다."),
    ALREADY_REVIEW_EXISTS(HttpStatus.BAD_REQUEST, "이미작성한 리뷰가 존재합니다."),
    RESERVATION_EXPIRED(HttpStatus.BAD_REQUEST, "예약 사용 가능시간이 지났습니다."),

    INVALID_RESERVATION(HttpStatus.BAD_REQUEST, "유효하지 않은 예약입니다."),
    REVIEW_AFTER_RESERVATION_COMPLETE(HttpStatus.BAD_REQUEST, "예약 사용 이후 리뷰작성이 가능합니다."),
    CANNOT_EDIT(HttpStatus.BAD_REQUEST, "수정권한이 없습니다."),
    CANNOT_DELETE(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다."),
    INVALID_RESERVE_TIME_FORMAT(HttpStatus.BAD_REQUEST, "예약 시간 형식이 잘못되었습니다. 올바른 형식: yyyy-MM-dd HH:mm")
    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
