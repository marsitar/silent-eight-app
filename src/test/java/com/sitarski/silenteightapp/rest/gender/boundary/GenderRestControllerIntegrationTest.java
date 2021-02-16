package com.sitarski.silenteightapp.rest.gender.boundary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sitarski.silenteightapp.common.entity.GenderType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.net.URI;

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
        String algorithmType = AlgoithmType.FIRST.toString();
        String name = "Alexine";
        //when
        ResultActions resultAction = mvc.perform(get(urlAddress)
                .param("algorithmType", algorithmType)
                .param("names", name));
        //then
        String resultContent = resultAction
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        GenderDto genderDto = convertGenderDtoJsonToJavaObject(resultContent);

        assertThat(genderDto.geGenderType()).isEqualTo(GenderType.MALE);
    }

    @Test
    void getGender_inputFirstAsAlgorithmTypeAndExistingMaleName_assertResultIsMale() throws Exception {

        //given
        String endpointURL = "/rest/api/genders/";
        URI urlAddress = new URI(endpointURL);
        String algorithmType = AlgoithmType.FIRST.toString();
        String name = "Roderic";
        //when
        ResultActions resultAction = mvc.perform(get(urlAddress)
                .param("algorithmType", algorithmType)
                .param("names", name));
        //then
        String resultContent = resultAction
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        GenderDto genderDto = convertGenderDtoJsonToJavaObject(resultContent);

        assertThat(genderDto.geGenderType()).isEqualTo(GenderType.MALE);
    }

    @Test
    void getGender_inputFirstAsAlgorithmTypeAndNonExistingName_assertResultIsInconclusive() throws Exception {

        //given
        String endpointURL = "/rest/api/genders/";
        URI urlAddress = new URI(endpointURL);
        String algorithmType = AlgoithmType.FIRST.toString();
        String name = "Aaaaa";
        //when
        ResultActions resultAction = mvc.perform(get(urlAddress)
                .param("algorithmType", algorithmType)
                .param("names", name));
        //then
        String resultContent = resultAction
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        GenderDto genderDto = convertGenderDtoJsonToJavaObject(resultContent);

        assertThat(genderDto.geGenderType()).isEqualTo(GenderType.INCONCLUSIVE);
    }

    @Test
    void getGender_inputAllAsAlgorithmTypeAndUncertainNames_assertResultIsInconclusive() throws Exception {

        //given
        String endpointURL = "/rest/api/genders/";
        URI urlAddress = new URI(endpointURL);
        String algorithmType = AlgoithmType.ALL.toString();
        String name = "Jan Maria Rokita";
        //when
        ResultActions resultAction = mvc.perform(get(urlAddress)
                .param("algorithmType", algorithmType)
                .param("names", name));
        //then
        String resultContent = resultAction
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        GenderDto genderDto = convertGenderDtoJsonToJavaObject(resultContent);

        assertThat(genderDto.geGenderType()).isEqualTo(GenderType.INCONCLUSIVE);
    }

    @Test
    void getGender_inputAllAsAlgorithmTypeAndExistingMainlyMenNames_assertResultIsMale() throws Exception {

        //given
        String endpointURL = "/rest/api/genders/";
        URI urlAddress = new URI(endpointURL);
        String algorithmType = AlgoithmType.ALL.toString();
        String name = "Jan Andrzej Rokita";
        //when
        ResultActions resultAction = mvc.perform(get(urlAddress)
                .param("algorithmType", algorithmType)
                .param("names", name));
        //then
        String resultContent = resultAction
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        GenderDto genderDto = convertGenderDtoJsonToJavaObject(resultContent);

        assertThat(genderDto.geGenderType()).isEqualTo(GenderType.MALE);
    }


    private GenderDto convertGenderDtoJsonToJavaObject(String content) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        GenderDto genderDto = objectMapper.readValue(content, GenderDto.class);

        return genderDto;
    }

}
