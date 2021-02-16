package com.sitarski.silenteightapp.service.gender.boundary;

import com.sitarski.silenteightapp.common.entity.GenderType;
import com.sitarski.silenteightapp.repository.gender.boundary.GenderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
public class GenderServiceUnitTest {

    @Mock
    private GenderRepository genderRepository;

    @Mock
    private GenderMapper genderMapper;

    @InjectMocks
    private GenderSevice genderSevice;


    @Test
    void getGenderByAlgorithmAndName_inputFirstAsAlgorithmTypeAndExistingFemaleName_assertResultIsFemale() throws Exception {
        //given
        AlgoithmType algorithmType = AlgoithmType.FIRST;
        String name = "Alexine";
        Gender gender = new Gender(GenderType.FEMALE);
        GenderDto genderDto = new GenderDto(GenderType.FEMALE);
        when(tokenRepository.findGenderByName(name)).thenReturn(gender);
        when(genderMapper.mapToGenderDto(gender)).thenReturn(genderDto);
        //when
        GenderDto genderDto = genderSevice.findGenderByNameAndAlgorithmType(algorithmType, name);
        //then
        assertThat(genderDto.getGenderType()).isEqualTo(GenderType.FEMALE);
    }

    @Test
    void getGenderByAlgorithmAndName_inputFirstAsAlgorithmTypeAndExistingMaleName_assertResultIsMale() throws Exception {
        //given
        AlgoithmType algorithmType = AlgoithmType.FIRST;
        String name = "Roderic";
        Gender gender = new Gender(GenderType.MALE);
        GenderDto genderDto = new GenderDto(GenderType.MALE);
        when(tokenRepository.findGenderByName(name)).thenReturn(gender);
        when(genderMapper.mapToGenderDto(gender)).thenReturn(genderDto);
        //when
        GenderDto genderDto = genderSevice.findGenderByNameAndAlgorithmType(algorithmType, name);
        //then
        assertThat(genderDto.getGenderType()).isEqualTo(GenderType.FEMALE);
    }

    @Test
    void getGenderByAlgorithmAndName_inputFirstAsAlgorithmTypeAndNonExistingName_assertResultIsInconclusive() throws Exception {
        //given
        AlgoithmType algorithmType = AlgoithmType.FIRST;
        String name = "Aaaaa";
        Gender gender = new Gender(GenderType.INCONCLUSIVE);
        GenderDto genderDto = new GenderDto(GenderType.INCONCLUSIVE);
        when(tokenRepository.findGenderByName(name)).thenReturn(gender);
        when(genderMapper.mapToGenderDto(gender)).thenReturn(genderDto);
        //when
        GenderDto genderDto = genderSevice.findGenderByNameAndAlgorithmType(algorithmType, name);
        //then
        assertThat(genderDto.getGenderType()).isEqualTo(GenderType.INCONCLUSIVE);
    }

    @Test
    void getGenderByAlgorithmAndName_inputAllAsAlgorithmTypeAndUncertainNames_assertResultIsInconclusive() throws Exception {
        //given
        AlgoithmType algorithmType = AlgoithmType.ALL;
        String name = "Renado Alyse Rokita";
        String[] dividedName = name.split(" ");
        Gender genderInconclusive = new Gender(GenderType.INCONCLUSIVE);
        Gender genderMale = new Gender(GenderType.MALE);
        Gender genderFemale = new Gender(GenderType.FEMALE);
        GenderDto genderInconclusiveDto = new GenderDto(GenderType.INCONCLUSIVE);
        GenderDto genderMaleDto = new GenderDto(GenderType.MALE);
        GenderDto genderFemaleDto = new GenderDto(GenderType.FEMALE);
        when(tokenRepository.findGenderByName(dividedName[0])).thenReturn(genderMale);
        when(tokenRepository.findGenderByName(dividedName[1])).thenReturn(genderFemale);
        when(tokenRepository.findGenderByName(dividedName[2])).thenReturn(genderInconclusive);
        when(genderMapper.mapToGenderDto(genderInconclusive)).thenReturn(genderInconclusiveDto);
        when(genderMapper.mapToGenderDto(genderMale)).thenReturn(genderMaleDto);
        when(genderMapper.mapToGenderDto(genderFemale)).thenReturn(genderFemaleDto);
        //when
        GenderDto genderDto = genderSevice.findGenderByNameAndAlgorithmType(algorithmType, name);
        //then
        assertThat(genderDto.getGenderType()).isEqualTo(GenderType.INCONCLUSIVE);
    }

    @Test
    void getGenderByAlgorithmAndName_inputAllAsAlgorithmTypeAndExistingMainlyMenNames_assertResultIsMale() throws Exception {
        //given
        String algorithmType = AlgoithmType.ALL.toString();
        String name = "Rodrigo Roosevelt Rokita";
        String[] dividedName = name.split(" ");
        Gender genderInconclusive = new Gender(GenderType.INCONCLUSIVE);
        Gender genderMale = new Gender(GenderType.MALE);
        GenderDto genderInconclusiveDto = new GenderDto(GenderType.INCONCLUSIVE);
        GenderDto genderMaleDto = new GenderDto(GenderType.MALE);
        when(tokenRepository.findGenderByName(dividedName[0])).thenReturn(genderMale);
        when(tokenRepository.findGenderByName(dividedName[2])).thenReturn(genderInconclusive);
        when(genderMapper.mapToGenderDto(genderInconclusive)).thenReturn(genderInconclusiveDto);
        when(genderMapper.mapToGenderDto(genderMale)).thenReturn(genderMaleDto);
        //when
        GenderDto genderDto = genderSevice.findGenderByNameAndAlgorithmType(algorithmType, name);
        //then
        assertThat(genderDto.getGenderType()).isEqualTo(GenderType.MALE);
    }

}
