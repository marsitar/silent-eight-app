package com.sitarski.silenteightapp.service.gender.boundary;

import com.sitarski.silenteightapp.common.entity.AlgorithmType;
import com.sitarski.silenteightapp.common.entity.GenderType;
import com.sitarski.silenteightapp.repository.gender.boundary.GenderRepository;
import com.sitarski.silenteightapp.repository.gender.entity.Gender;
import com.sitarski.silenteightapp.rest.gender.entity.GenderDto;
import com.sitarski.silenteightapp.service.gender.control.AlgorithmHelper;
import com.sitarski.silenteightapp.service.gender.control.GenderMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class GenderServiceUnitTest {

    @Mock
    private GenderRepository genderRepository;

    @Mock
    private GenderMapper genderMapper;

    @Mock
    private AlgorithmHelper algorithmHelper;

    @InjectMocks
    private GenderService genderService;


    @Test
    void getGenderByAlgorithmAndName_inputFirstAsAlgorithmTypeAndExistingFemaleName_assertResultIsFemale() throws Exception {
        //given
        AlgorithmType algorithmType = AlgorithmType.FIRST;
        String name = "Alexine";
        Gender gender = new Gender(GenderType.FEMALE);
        GenderDto genderDto = new GenderDto(GenderType.FEMALE);
        when(genderRepository.findGenderByName(name)).thenReturn(gender);
        when(genderMapper.mapToGenderDto(gender)).thenReturn(genderDto);
        when(algorithmHelper.findTheMostPopularGenderResult(List.of(genderDto))).thenReturn(genderDto);
        //when
        GenderDto generatedGenderDto = genderService.getGenderByAlgorithmAndName(algorithmType, name);
        //then
        assertThat(generatedGenderDto.getGenderType()).isEqualTo(GenderType.FEMALE);
    }

    @Test
    void getGenderByAlgorithmAndName_inputFirstAsAlgorithmTypeAndExistingMaleName_assertResultIsMale() throws Exception {
        //given
        AlgorithmType algorithmType = AlgorithmType.FIRST;
        String name = "Roderic";
        Gender gender = new Gender(GenderType.MALE);
        GenderDto genderDto = new GenderDto(GenderType.MALE);
        when(genderRepository.findGenderByName(name)).thenReturn(gender);
        when(genderMapper.mapToGenderDto(gender)).thenReturn(genderDto);
        when(algorithmHelper.findTheMostPopularGenderResult(List.of(genderDto))).thenReturn(genderDto);
        //when
        GenderDto generatedGenderDto = genderService.getGenderByAlgorithmAndName(algorithmType, name);
        //then
        assertThat(generatedGenderDto.getGenderType()).isEqualTo(GenderType.MALE);
    }

    @Test
    void getGenderByAlgorithmAndName_inputFirstAsAlgorithmTypeAndNonExistingName_assertResultIsInconclusive() throws Exception {
        //given
        AlgorithmType algorithmType = AlgorithmType.FIRST;
        String name = "Aaaaa";
        Gender gender = new Gender(GenderType.INCONCLUSIVE);
        GenderDto genderDto = new GenderDto(GenderType.INCONCLUSIVE);
        when(genderRepository.findGenderByName(name)).thenReturn(gender);
        when(genderMapper.mapToGenderDto(gender)).thenReturn(genderDto);
        when(algorithmHelper.findTheMostPopularGenderResult(List.of(genderDto))).thenReturn(genderDto);
        //when
        GenderDto generatedGenderDto = genderService.getGenderByAlgorithmAndName(algorithmType, name);
        //then
        assertThat(generatedGenderDto.getGenderType()).isEqualTo(GenderType.INCONCLUSIVE);
    }

    @Test
    void getGenderByAlgorithmAndName_inputAllAsAlgorithmTypeAndUncertainNames_assertResultIsInconclusive() throws Exception {
        //given
        AlgorithmType algorithmType = AlgorithmType.ALL;
        String name = "Renado Alyse Rokita";
        String[] dividedName = name.split(" ");
        Gender genderInconclusive = new Gender(GenderType.INCONCLUSIVE);
        Gender genderMale = new Gender(GenderType.MALE);
        Gender genderFemale = new Gender(GenderType.FEMALE);
        GenderDto genderInconclusiveDto = new GenderDto(GenderType.INCONCLUSIVE);
        GenderDto genderMaleDto = new GenderDto(GenderType.MALE);
        GenderDto genderFemaleDto = new GenderDto(GenderType.FEMALE);
        when(genderRepository.findGenderByName(dividedName[0])).thenReturn(genderMale);
        when(genderRepository.findGenderByName(dividedName[1])).thenReturn(genderFemale);
        when(genderRepository.findGenderByName(dividedName[2])).thenReturn(genderInconclusive);
        when(genderMapper.mapToGenderDto(genderInconclusive)).thenReturn(genderInconclusiveDto);
        when(genderMapper.mapToGenderDto(genderMale)).thenReturn(genderMaleDto);
        when(genderMapper.mapToGenderDto(genderFemale)).thenReturn(genderFemaleDto);
        when(algorithmHelper.findTheMostPopularGenderResult(List.of(genderMaleDto, genderFemaleDto, genderInconclusiveDto))).thenReturn(genderInconclusiveDto);
        //when
        GenderDto genderDto = genderService.getGenderByAlgorithmAndName(algorithmType, name);
        //then
        assertThat(genderDto.getGenderType()).isEqualTo(GenderType.INCONCLUSIVE);
    }

    @Test
    void getGenderByAlgorithmAndName_inputAllAsAlgorithmTypeAndExistingMainlyMenNames_assertResultIsMale() throws Exception {
        //given
        AlgorithmType algorithmType = AlgorithmType.ALL;
        String name = "Rodrigo Roosevelt Rokita";
        String[] dividedName = name.split(" ");
        Gender genderInconclusive = new Gender(GenderType.INCONCLUSIVE);
        Gender genderMale = new Gender(GenderType.MALE);
        GenderDto genderInconclusiveDto = new GenderDto(GenderType.INCONCLUSIVE);
        GenderDto genderMaleDto = new GenderDto(GenderType.MALE);
        when(genderRepository.findGenderByName(dividedName[0])).thenReturn(genderMale);
        when(genderRepository.findGenderByName(dividedName[1])).thenReturn(genderMale);
        when(genderRepository.findGenderByName(dividedName[2])).thenReturn(genderInconclusive);
        when(genderMapper.mapToGenderDto(genderInconclusive)).thenReturn(genderInconclusiveDto);
        when(genderMapper.mapToGenderDto(genderMale)).thenReturn(genderMaleDto);
        when(algorithmHelper.findTheMostPopularGenderResult(List.of(genderMaleDto, genderMaleDto, genderInconclusiveDto))).thenReturn(genderMaleDto);
        //when
        GenderDto genderDto = genderService.getGenderByAlgorithmAndName(algorithmType, name);
        //then
        assertThat(genderDto.getGenderType()).isEqualTo(GenderType.MALE);
    }

}
