package com.sitarski.silenteightapp.service.token.control;

import com.sitarski.silenteightapp.common.entity.GenderType;
import com.sitarski.silenteightapp.repository.token.entity.Token;
import com.sitarski.silenteightapp.rest.token.entity.TokenDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TokenMapperUnitTest {

    @InjectMocks
    private TokenMapper tokenMapper;

    @Test
    void getToTokenDto_inputNullArgument_assertFieldsAreNull() {
        //given
        Token token = null;
        //when
        TokenDto tokenDto = tokenMapper.mapToTokenDto(token);
        //then
        assertThat(tokenDto.getGenderType()).isNull();
        assertThat(tokenDto.getName()).isNull();
    }

    @Test
    void getToTokenDto_inputEmptyObject_assertFieldsAreNull() {
        //given
        Token token = new Token();
        //when
        TokenDto tokenDto = tokenMapper.mapToTokenDto(token);
        //then
        assertThat(tokenDto.getGenderType()).isNull();
        assertThat(tokenDto.getName()).isNull();
    }

    @Test
    void getToTokenDto_inputFirstCorrectObject_assertFieldsAreMapped() {
        //given
        GenderType genderType = GenderType.FEMALE;
        String name = "Lyssa";
        Token token = new Token(genderType, name);
        //when
        TokenDto tokenDto = tokenMapper.mapToTokenDto(token);
        //then
        assertThat(tokenDto.getGenderType()).isEqualTo(genderType);
        assertThat(tokenDto.getName()).isEqualTo(name);
    }

    @Test
    void getToTokenDto_inputSecondCorrectObject_assertFieldsAreMapped() {
        //given
        GenderType genderType = GenderType.MALE;
        String name = "Aldric";
        Token token = new Token(genderType, name);
        //when
        TokenDto tokenDto = tokenMapper.mapToTokenDto(token);
        //then
        assertThat(tokenDto.getGenderType()).isEqualTo(genderType);
        assertThat(tokenDto.getName()).isEqualTo(name);
    }

}
