package com.sitarski.silenteightapp.service.gender.control;

import com.sitarski.silenteightapp.common.entity.GenderType;
import com.sitarski.silenteightapp.repository.gender.entity.Gender;
import com.sitarski.silenteightapp.rest.gender.entity.GenderDto;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Optional;

@Component
@RequestScope
public class GenderMapper {

    public GenderDto mapToGenderDto(Gender gender) {

        GenderType genderType = Optional.ofNullable(gender)
                .map(Gender::getGenderType)
                .orElse(null);

        GenderDto genderDto = new GenderDto();

        genderDto.setGenderType(genderType);

        return genderDto;
    }
}
