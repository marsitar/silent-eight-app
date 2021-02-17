package com.sitarski.silenteightapp.repository.gender.entity;

import com.sitarski.silenteightapp.common.entity.GenderType;

public class Gender {

    private GenderType genderType;

    public Gender() {
    }

    public Gender(GenderType genderType) {
        this.genderType = genderType;
    }

    public GenderType getGenderType() {
        return genderType;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }
}
