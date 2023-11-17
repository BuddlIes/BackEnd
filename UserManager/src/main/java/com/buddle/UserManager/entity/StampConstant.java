package com.buddle.UserManager.entity;

public class StampConstant {

    //stamp_type = 0x|-접속형-|-게시글형-|-댓글형-|-봉사하기형-|-리뷰형-|-기타형-|
    public static final Integer STAMP_TYPE_LOGIN = 0b100000;
    public static final Integer STAMP_TYPE_POST = 0b010000;
    public static final Integer STAMP_TYPE_COMMENT = 0b001000;
    public static final Integer STAMP_TYPE_DO_VOL = 0b000100;
    public static final Integer STAMP_TYPE_REVIEW = 0b000010;

}
