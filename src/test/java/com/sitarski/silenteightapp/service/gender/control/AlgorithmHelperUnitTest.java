package com.sitarski.silenteightapp.service.gender.control;

import com.sitarski.silenteightapp.rest.gender.entity.GenderDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import com.sitarski.silenteightapp.common.entity.GenderType;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AlgorithmHelperUnitTest {

    @InjectMocks
    private AlgorithmHelper algorithmHelper;

    @Test
    void findTheMostPopularGenderResult_inputMainlyMaleGenderObjects_assertResultIsMaleGender() throws Exception {
        //given
        List<GenderDto> genders = List.of(new GenderDto(GenderType.MALE), new GenderDto(GenderType.FEMALE), new GenderDto(GenderType.MALE));
        //when
        GenderDto mostPopularGender = algorithmHelper.findTheMostPopularGenderResult(genders);
        //then
        assertThat(mostPopularGender.getGenderType()).isEqualTo(GenderType.MALE);
    }

    @Test
    void findTheMostPopularGenderResult_inputMainlyFemaleGenderObjects_assertResultIsFemaleGender() throws Exception {
        //given
        List<GenderDto> genders = List.of(new GenderDto(GenderType.FEMALE), new GenderDto(GenderType.FEMALE), new GenderDto(GenderType.MALE));
        //when
        GenderDto mostPopularGender = algorithmHelper.findTheMostPopularGenderResult(genders);
        //then
        assertThat(mostPopularGender.getGenderType()).isEqualTo(GenderType.FEMALE);
    }

    @Test
    void findTheMostPopularGenderResult_inputEmptyList_assertResultIsInconclusiveGender() throws Exception {
        //given
        List<GenderDto> genders = new ArrayList<>();
        //when
        GenderDto mostPopularGender = algorithmHelper.findTheMostPopularGenderResult(genders);
        //then
        assertThat(mostPopularGender.getGenderType()).isEqualTo(GenderType.INCONCLUSIVE);
    }
}
