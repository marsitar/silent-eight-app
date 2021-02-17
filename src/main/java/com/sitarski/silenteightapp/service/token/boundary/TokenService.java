package com.sitarski.silenteightapp.service.token.boundary;

import com.sitarski.silenteightapp.common.entity.GenderType;
import com.sitarski.silenteightapp.repository.token.boundary.TokenRepository;
import com.sitarski.silenteightapp.repository.token.entity.Token;
import com.sitarski.silenteightapp.rest.token.entity.TokenDto;
import com.sitarski.silenteightapp.service.token.control.TokenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequestScope
public class TokenService {

    private final TokenRepository tokenRepository;
    private final TokenMapper tokenMapper;

    @Autowired
    public TokenService(TokenRepository tokenRepository, TokenMapper tokenMapper) {
        this.tokenRepository = tokenRepository;
        this.tokenMapper = tokenMapper;
    }

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);


    public List<TokenDto> getTokens() {
        List<Token> femaleTokens = tokenRepository.findAllByGender(GenderType.FEMALE);
        List<Token> maleTokens = tokenRepository.findAllByGender(GenderType.MALE);
        List<TokenDto> tokens = Stream.concat(femaleTokens.stream(), maleTokens.stream())
                .map(tokenMapper::mapToTokenDto)
                .collect(Collectors.toList());

        logger.debug("getTokens() - tokens has been get");

        return tokens;
    }

}
