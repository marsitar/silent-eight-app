package com.sitarski.silenteightapp.service.gender.control;

import com.sitarski.silenteightapp.common.entity.GenderType;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GenderMapperUnitTest {

    @InjectMocks
    private GenderMapper genderMapper;

    @Test
    void mapToGenderDto_inputNullArgument_assertFieldsAreNull() {
        //given
        Gender gender = null;
        //when
        GenderDto genderDto = genderMapper.mapToGenderDto(gender);
        //then
        assertThat(genderDto.getGenderType()).isNull();
    }

    @Test
    void mapToGenderDto_inputEmptyObject_assertFieldsAreNull() {
        //given
        Gender gender = new Gender();
        //when
        GenderDto genderDto = genderMapper.mapToGenderDto(gender);
        //then
        assertThat(genderDto.getGenderType()).isNull();
    }

    @Test
    void mapToGenderDto_inputFirstCorrectObject_assertFieldsAreMapped() {
        //given
        GenderType genderType = GenderType.FEMALE;
        Gender gender = new Gender(genderType);
        //when
        GenderDto genderDto = genderMapper.mapToGenderDto(gender);
        //then
        assertThat(tokenDto.getGenderType()).isEqualTo(genderType);
    }

}
