package com.sitarski.silenteightapp.repository.token.entity;

import com.sitarski.silenteightapp.common.entity.GenderType;

public class Token {

    private GenderType genderType;
    private String name;

    public Token() {
    }

    public Token(GenderType genderType, String name) {
        this.genderType = genderType;
        this.name = name;
    }

    public GenderType getGenderType() {
        return genderType;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
