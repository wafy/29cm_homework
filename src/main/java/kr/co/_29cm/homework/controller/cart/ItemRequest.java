package kr.co._29cm.homework.controller.cart;

import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class ItemRequest {

    @Pattern(regexp = "(^[0-9]*$)", message = "숫자만 입력해주세요.")
    private Long itemNo;

    @Pattern(regexp = "(^[0-9]*$)", message = "숫자만 입력해주세요.")
    private int quantity;

}
