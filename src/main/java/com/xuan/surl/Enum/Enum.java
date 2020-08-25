
package com.xuan.surl.Enum;

public enum Enum {
    CHARS("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    ALREADY("SpringBoot"),
    USERDIR("user.dir");

    private String mapping;

    Enum(String mapping) {
        this.mapping = mapping;
    }

    public String getMapping() {
        return mapping;
    }

    @Override
    public String toString() {
        return "Enum{" +
                "mapping='" + mapping + '\'' +
                '}';
    }


}
