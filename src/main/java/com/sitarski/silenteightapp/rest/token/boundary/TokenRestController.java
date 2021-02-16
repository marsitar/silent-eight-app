package com.sitarski.silenteightapp.rest.token.boundary;

import com.sitarski.silenteightapp.configuration.swagger.entity.SwaggerTags;
import com.sitarski.silenteightapp.rest.token.entity.TokenDto;
import com.sitarski.silenteightapp.service.token.boundary.TokenService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/api/tokens")
@Api(tags = SwaggerTags.TOKEN)
public class TokenRestController {

    private final TokenService tokenService;

    @Autowired
    public TokenRestController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<TokenDto> getTokens() {
        List<TokenDto> tokens = tokenService.getTokens();
        return tokens;
    }

}
