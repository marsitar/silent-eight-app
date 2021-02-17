package com.sitarski.silenteightapp.service.gender.control;

import com.sitarski.silenteightapp.rest.gender.entity.GenderDto;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import com.sitarski.silenteightapp.common.entity.GenderType;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequestScope
public class AlgorithmHelper {

    public GenderDto findTheMostPopularGenderResult(List<GenderDto> genders) {

        List<Map.Entry<GenderDto, Long>> groupedGendersResult = genders.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(Map.Entry::getValue)))
                .collect(Collectors.toList());


        return Match(genders.size()).of(
                Case($(0), new GenderDto(GenderType.INCONCLUSIVE)),
                Case($(1), genders.get(0)),
                Case($(), () -> findTheMostPopularGenderInVariedCollection(groupedGendersResult)));
    }

    private GenderDto findTheMostPopularGenderInVariedCollection(List<Map.Entry<GenderDto, Long>> groupedGendersResult){
        boolean gendersQuantityEqualInCollection = checkIfGendersQuantityInResultCollectionIsEqual(groupedGendersResult);

        return Match(gendersQuantityEqualInCollection).of(
                Case($(true), new GenderDto(GenderType.INCONCLUSIVE)),
                Case($(false), Optional.of(groupedGendersResult).map(list -> list.get(0)).map(Map.Entry::getKey).orElse(null)));
    }

    private boolean checkIfGendersQuantityInResultCollectionIsEqual(List<Map.Entry<GenderDto, Long>> groupedGendersResult){
        Long firstElementQuantity = Optional.of(groupedGendersResult)
                .map(list -> list.get(0))
                .map(Map.Entry::getValue)
                .orElse(0L);

        Long secondElementQuantity = Optional.of(groupedGendersResult)
                .map(list -> list.get(1))
                .map(Map.Entry::getValue)
                .orElse(0L);

        return firstElementQuantity.equals(secondElementQuantity);
    }

}
