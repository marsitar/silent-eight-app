package com.sitarski.silenteightapp.service.gender.boundary;

import com.sitarski.silenteightapp.common.entity.AlgorithmType;
import com.sitarski.silenteightapp.common.entity.GenderType;
import com.sitarski.silenteightapp.repository.gender.boundary.GenderRepository;
import com.sitarski.silenteightapp.rest.gender.entity.GenderDto;
import com.sitarski.silenteightapp.service.gender.control.AlgorithmHelper;
import com.sitarski.silenteightapp.service.gender.control.GenderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.*;
import java.util.stream.Collectors;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

@Service
@RequestScope
public class GenderService {

    private final GenderMapper genderMapper;
    private final GenderRepository genderRepository;
    private final AlgorithmHelper algorithmHelper;
    private static final Logger logger = LoggerFactory.getLogger(GenderService.class);

    @Autowired
    public GenderService(GenderMapper genderMapper, GenderRepository genderRepository, AlgorithmHelper algorithmHelper) {
        this.genderMapper = genderMapper;
        this.genderRepository = genderRepository;
        this.algorithmHelper = algorithmHelper;
    }

    public GenderDto getGenderByAlgorithmAndName(AlgorithmType algorithmType, String fullName) {
        String[] names = fullName.split(" ");

        return Match(algorithmType).of(
                Case($(AlgorithmType.FIRST), findGenderByFirstAlgorithm(names)),
                Case($(AlgorithmType.ALL), findGenderByAllAlgorithm(names)));
    }

    private GenderDto findGenderByFirstAlgorithm(String[] names) {
        String firstName = names[0];
        GenderDto gender = Optional.of(firstName)
                .map(genderRepository::findGenderByName)
                .map(genderMapper::mapToGenderDto)
                .orElse(null);

        logger.debug("findGenderByFirstAlgorithm(names={}) - found gender={}", names, gender);

        return gender;
    }

    private GenderDto findGenderByAllAlgorithm(String[] names) {
        List<GenderDto> genders = Arrays.stream(names)
                .map(genderRepository::findGenderByName)
                .filter(Objects::nonNull)
                .map(genderMapper::mapToGenderDto)
                .collect(Collectors.toList());

        GenderDto mostPopularGender = algorithmHelper.findTheMostPopularGenderResult(genders);

        logger.debug("findGenderByFirstAlgorithm(names={}) - most popular gender={}", names, mostPopularGender);

        return mostPopularGender;
    }
}
