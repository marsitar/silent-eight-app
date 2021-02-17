package com.sitarski.silenteightapp.rest.gender.boundary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sitarski.silenteightapp.common.entity.AlgorithmType;
import com.sitarski.silenteightapp.common.entity.GenderType;
import com.sitarski.silenteightapp.rest.gender.entity.GenderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GenderRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getGender_inputFirstAsAlgorithmTypeAndExistingFemaleName_assertResultIsFemale() throws Exception {

        //given
        String endpointURL = "/rest/api/genders/";
        URI urlAddress = new URI(endpointURL);
        String algorithmType = AlgorithmType.FIRST.toString();
        String name = "Alexine";
        //when
        ResultActions resultAction = mvc.perform(get(urlAddress)
                .param("algorithmType", algorithmType)
                .param("name", name));
        //then
        String resultContent = resultAction
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        GenderDto genderDto = convertGenderDtoJsonToJavaObject(resultContent);

        assertThat(genderDto.getGenderType()).isEqualTo(GenderType.FEMALE);
    }

    @Test
    void getGender_inputFirstAsAlgorithmTypeAndExistingMaleName_assertResultIsMale() throws Exception {

        //given
        String endpointURL = "/rest/api/genders/";
        URI urlAddress = new URI(endpointURL);
        String algorithmType = AlgorithmType.FIRST.toString();
        String name = "Roderic";
        //when
        ResultActions resultAction = mvc.perform(get(urlAddress)
                .param("algorithmType", algorithmType)
                .param("name", name));
        //then
        String resultContent = resultAction
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        GenderDto genderDto = convertGenderDtoJsonToJavaObject(resultContent);

        assertThat(genderDto.getGenderType()).isEqualTo(GenderType.MALE);
    }

    @Test
    void getGender_inputFirstAsAlgorithmTypeAndNonExistingName_assertResultIsInconclusive() throws Exception {

        //given
        String endpointURL = "/rest/api/genders/";
        URI urlAddress = new URI(endpointURL);
        String algorithmType = AlgorithmType.FIRST.toString();
        String name = "Aaaaa";
        //when
        ResultActions resultAction = mvc.perform(get(urlAddress)
                .param("algorithmType", algorithmType)
                .param("name", name));
        //then
        String resultContent = resultAction
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        GenderDto genderDto = convertGenderDtoJsonToJavaObject(resultContent);

        assertThat(genderDto.getGenderType()).isEqualTo(GenderType.INCONCLUSIVE);
    }

    @Test
    void getGender_inputAllAsAlgorithmTypeAndUncertainNames_assertResultIsInconclusive() throws Exception {

        //given
        String endpointURL = "/rest/api/genders/";
        URI urlAddress = new URI(endpointURL);
        String algorithmType = AlgorithmType.ALL.toString();
        String name = "Renado Alyse Rokita";
        //when
        ResultActions resultAction = mvc.perform(get(urlAddress)
                .param("algorithmType", algorithmType)
                .param("name", name));
        //then
        String resultContent = resultAction
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        GenderDto genderDto = convertGenderDtoJsonToJavaObject(resultContent);

        assertThat(genderDto.getGenderType()).isEqualTo(GenderType.INCONCLUSIVE);
    }

    @Test
    void getGender_inputAllAsAlgorithmTypeAndExistingMainlyMenNames_assertResultIsMale() throws Exception {

        //given
        String endpointURL = "/rest/api/genders/";
        URI urlAddress = new URI(endpointURL);
        String algorithmType = AlgorithmType.ALL.toString();
        String name = "Rodrigo Roosevelt Rokita";
        //when
        ResultActions resultAction = mvc.perform(get(urlAddress)
                .param("algorithmType", algorithmType)
                .param("name", name));
        //then
        String resultContent = resultAction
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        GenderDto genderDto = convertGenderDtoJsonToJavaObject(resultContent);

        assertThat(genderDto.getGenderType()).isEqualTo(GenderType.MALE);
    }


    private GenderDto convertGenderDtoJsonToJavaObject(String content) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        GenderDto genderDto = objectMapper.readValue(content, GenderDto.class);

        return genderDto;
    }

}
