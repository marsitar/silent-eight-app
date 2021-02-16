package com.sitarski.silenteightapp.rest.gender.boundary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TokenRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getTokens_inputNoArguments_assertResultIsCorrect() throws Exception {

        //given
        String endpointURL = "/rest/api/tokens/";
        URI urlAddress = new URI(endpointURL);
        //then
        mvc.perform(get(urlAddress))
                .andExpect(status().isOk());
    }


}
