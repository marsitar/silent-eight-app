package com.sitarski.silenteightapp.rest.gender.entity;

import com.sitarski.silenteightapp.common.entity.GenderType;

import java.util.Objects;

public class GenderDto {

    private GenderType genderType;

    public GenderDto() {
    }

    public GenderDto(GenderType genderType) {
        this.genderType = genderType;
    }

    public GenderType getGenderType() {
        return genderType;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenderDto)) return false;
        GenderDto genderDto = (GenderDto) o;
        return genderType == genderDto.genderType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(genderType);
    }

    @Override
    public String toString() {
        return "GenderDto{" +
                "genderType=" + genderType +
                '}';
    }
}
