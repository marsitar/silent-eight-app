package com.sitarski.silenteightapp.service.token.boundary;

import com.sitarski.silenteightapp.repository.token.boundary.TokenRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class TokenServiceUnitTest {

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private TokenMapper tokenMapper;

    @InjectMocks
    private TokenService tokenService;

    @Test
    void getTokens_inputNoArguments_assertResultIsCorrect() {
        //given
        List<Token> femaleTokens = prepareFemaleTokens();
        List<Token> maleTokens = prepareMaleTokens();
        List<TokenDto> femaleTokenDtos = prepareFemaleDtoTokens();
        List<TokenDto> maleTokenDtos = prepareMaleDtoTokens();
        when(tokenRepository.findAllByGender(GenderType.FEMALE).thenReturn(femaleTokens);
        when(tokenRepository.findAllByGender(GenderType.MALE).thenReturn(maleTokens);
        when(tokenMapper.mapToTokenDto(femaleTokens.get(0)).thenReturn(femaleTokenDtos.get(0));
        when(tokenMapper.mapToTokenDto(femaleTokens.get(1)).thenReturn(femaleTokenDtos.get(1));
        when(tokenMapper.mapToTokenDto(femaleTokens.get(2)).thenReturn(femaleTokenDtos.get(2));
        when(tokenMapper.mapToTokenDto(maleTokens.get(0)).thenReturn(maleTokenDtos.get(0));
        when(tokenMapper.mapToTokenDto(maleTokens.get(1)).thenReturn(maleTokenDtos.get(1));
        when(tokenMapper.mapToTokenDto(maleTokens.get(2)).thenReturn(maleTokenDtos.get(2));
        //when
        List<TokenDto> tokens = tokenService.getTokens();
        //then
        assertThat(tokens).isNotNull();
        assertThat(tokens.size()).isEqualTo(6);
        assertThat(tokens).isEqualTo(List.of(femaleTokenDtos.get(0), femaleTokenDtos.get(1), femaleTokenDtos.get(2), maleTokenDtos.get(0), maleTokenDtos.get(1), maleTokenDtos.get(2)));

    }

    private List<Token> prepareFemaleTokens() {

        Token femaleToken1 = new Token(GenderType.FEMALE, "Lyssa");
        Token femaleToken2 = new Token(GenderType.FEMALE, "Stephenie");
        Token femaleToken3 = new Token(GenderType.FEMALE, "Janey");

        return List.of(femaleToken1, femaleToken2, femaleToken3);
    }

    private List<Token> prepareMaleTokens() {

        Token maleToken1 = new Token(GenderType.MALE, "Aldric");
        Token maleToken2 = new Token(GenderType.MALE, "Morten");
        Token maleToken3 = new Token(GenderType.MALE, "Shea");

        return List.of(maleToken1, maleToken2, maleToken3);
    }

    private List<TokenDto> prepareFemaleDtoTokens() {

        TokenDto femaleTokenDto1 = new TokenDto(GenderType.FEMALE, "Lyssa");
        TokenDto femaleTokenDto2 = new TokenDto(GenderType.FEMALE, "Stephenie");
        TokenDto femaleTokenDto3 = new TokenDto(GenderType.FEMALE, "Janey");

        return List.of(femaleTokenDto1, femaleTokenDto2, femaleTokenDto3);
    }

    private List<TokenDto> prepareMaleDtoTokens() {

        TokenDto maleTokenDto1 = new TokenDto(GenderType.MALE, "Aldric");
        TokenDto maleTokenDto2 = new TokenDto(GenderType.MALE, "Morten");
        TokenDto maleTokenDto3 = new TokenDto(GenderType.MALE, "Shea");

        return List.of(maleTokenDto1, maleTokenDto2, maleTokenDto3);
    }
}
