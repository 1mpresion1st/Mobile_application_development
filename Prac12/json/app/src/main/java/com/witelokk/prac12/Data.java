package com.witelokk.prac12;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("text")
    private String text;

    @SerializedName("number")
    private int number;

    @SerializedName("bool")
    private boolean bool;

    public Data() {
    }

    public Data(String text, int number, boolean bool) {
        this.text = text;
        this.number = number;
        this.bool = bool;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }
}
