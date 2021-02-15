package com.sitarski.silenteightapp.service.token.control;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class TokenMapperUnitTest {

    @InjectMocks
    private TokenMapper tokenMapper;

    @Test
    void getTokens_inputNullArgument_assertFieldsAreNull() {
        //given
        Token token = null;
        //when
        TokenDto tokenDto = tokenMapper.mapToTokenDto(token);
        //then
        assertThat(tokenDto.getGenderType()).isNull();
        assertThat(tokenDto.getName()).isNull();
    }

    @Test
    void getTokens_inputEmptyObject_assertFieldsAreNull() {
        //given
        Token token = new Token();
        //when
        TokenDto tokenDto = tokenMapper.mapToTokenDto(token);
        //then
        assertThat(tokenDto.getGenderType()).isNull();
        assertThat(tokenDto.getName()).isNull();
    }

    @Test
    void getTokens_inputFirstCorrectObject_assertFieldsAreMapped() {
        //given
        GenderType genderType = GenderType.FEMALE;
        String name = "Lyssa";
        Token token = new TokenDto(genderType, name);
        //when
        TokenDto tokenDto = tokenMapper.mapToTokenDto(token);
        //then
        assertThat(tokenDto.getGenderType()).isEqualTo(genderType);
        assertThat(tokenDto.getName()).isEqualTo(name);
    }

    @Test
    void getTokens_inputSecondCorrectObject_assertFieldsAreMapped() {
        //given
        GenderType genderType = GenderType.MALE;
        String name = "Aldric";
        Token token = new TokenDto(genderType, name);
        //when
        TokenDto tokenDto = tokenMapper.mapToTokenDto(token);
        //then
        assertThat(tokenDto.getGenderType()).isEqualTo(genderType);
        assertThat(tokenDto.getName()).isEqualTo(name);
    }

}
