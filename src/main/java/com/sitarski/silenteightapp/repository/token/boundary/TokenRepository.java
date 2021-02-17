package com.sitarski.silenteightapp.repository.token.boundary;

import com.sitarski.silenteightapp.common.entity.GenderType;
import com.sitarski.silenteightapp.repository.token.entity.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Repository
@RequestScope
public class TokenRepository {

    private ResourceLoader resourceLoader;

    @Autowired
    public TokenRepository(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    private static final Logger logger = LoggerFactory.getLogger(TokenRepository.class);

    public List<Token> findAllByGender(GenderType genderType) {

        String gender = Optional.ofNullable(genderType)
                .map(Enum::toString)
                .map(String::toLowerCase)
                .orElseThrow();

        List<Token> tokens = new ArrayList<>();

        try (InputStream inputStream = resourceLoader.getResource("classpath:" + gender + ".txt").getInputStream(); Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                Token token = new Token(genderType, scanner.next());
                tokens.add(token);
            }
        } catch (IOException iOException) {
            logger.error("findAllByGender(genderType={}) - tokens file not found", genderType);
        }

        logger.info("findAllByGender(genderType={}) - tokens have been generated", genderType);

        return tokens;
    }
}
