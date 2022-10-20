package kr.co._29cm.homework.consts;

public enum InputType {
    ORDER("O"), QUIT("Q");


    private final String value;

    InputType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
