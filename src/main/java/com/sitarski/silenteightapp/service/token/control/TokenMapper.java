package com.sitarski.silenteightapp.service.token.control;

import com.sitarski.silenteightapp.common.entity.GenderType;
import com.sitarski.silenteightapp.repository.token.entity.Token;
import com.sitarski.silenteightapp.rest.token.entity.TokenDto;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Optional;

@Component
@RequestScope
public class TokenMapper {

    public TokenDto mapToTokenDto(Token token) {

        GenderType genderType = Optional.ofNullable(token)
                .map(Token::getGenderType)
                .orElse(null);

        String name = Optional.ofNullable(token)
                .map(Token::getName)
                .orElse(null);

        TokenDto tokenDto = new TokenDto();

        tokenDto.setGenderType(genderType);
        tokenDto.setName(name);

        return tokenDto;
    }
}
