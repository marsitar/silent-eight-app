package com.sitarski.silenteightapp.rest.gender.boundary;

import com.sitarski.silenteightapp.common.entity.AlgorithmType;
import com.sitarski.silenteightapp.configuration.swagger.entity.SwaggerTags;
import com.sitarski.silenteightapp.rest.gender.entity.GenderDto;
import com.sitarski.silenteightapp.service.gender.boundary.GenderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/genders")
@Api(tags = SwaggerTags.GENDER)
public class GenderRestController {

    private final GenderService genderService;

    @Autowired
    public GenderRestController(GenderService genderService) {
        this.genderService = genderService;
    }

    @GetMapping(value = "/", produces = "application/json")
    public GenderDto getGenderByAlgorithmAndName(@NonNull  @RequestParam("algorithmType") AlgorithmType algorithmType, @NonNull @RequestParam("name") String name) {
        GenderDto genderDto = genderService.getGenderByAlgorithmAndName(algorithmType, name);
        return genderDto;
    }

}
