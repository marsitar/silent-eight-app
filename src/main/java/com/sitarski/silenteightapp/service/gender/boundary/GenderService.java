package com.sitarski.silenteightapp.service.gender.boundary;

import com.sitarski.silenteightapp.common.entity.AlgorithmType;
import com.sitarski.silenteightapp.common.entity.GenderType;
import com.sitarski.silenteightapp.repository.gender.boundary.GenderRepository;
import com.sitarski.silenteightapp.rest.gender.entity.GenderDto;
import com.sitarski.silenteightapp.service.gender.control.GenderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequestScope
public class GenderService {

    private final GenderMapper genderMapper;
    private final GenderRepository genderRepository;

    @Autowired
    public GenderService(GenderMapper genderMapper, GenderRepository genderRepository) {
        this.genderMapper = genderMapper;
        this.genderRepository = genderRepository;
    }

    public GenderDto getGenderByAlgorithmAndName(AlgorithmType algorithmType, String name) {
        String[] names = name.split(" ");

        if(AlgorithmType.FIRST.equals(algorithmType)) {
            String firstName = names[0];
            GenderDto gender = Optional.of(firstName)
                    .map(genderRepository::findGenderByName)
                    .map(genderMapper::mapToGenderDto)
                    .orElse(null);
            return gender;
        } else {
            List<GenderDto> genders = Arrays.stream(names)
                    .map(genderRepository::findGenderByName)
                    .filter(Objects::nonNull)
                    .map(genderMapper::mapToGenderDto)
                    .collect(Collectors.toList());

            List<Map.Entry<GenderDto,Long>> groupedGendersResult = genders.stream()
                    .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted(Collections.reverseOrder(Comparator.comparing(Map.Entry::getValue)))
                    .collect(Collectors.toList());

            if(genders.size() == 0) {
                return new GenderDto(GenderType.INCONCLUSIVE);
            } else if(genders.size() == 1) {
                return genders.get(0);
            } else {
                Long firstElementQuantity = Optional.of(groupedGendersResult)
                        .map(list -> list.get(0))
                        .map(Map.Entry::getValue)
                        .orElse(0L);

                Long secondElementQuantity = Optional.of(groupedGendersResult)
                        .map(list -> list.get(1))
                        .map(Map.Entry::getValue)
                        .orElse(0L);

                if(firstElementQuantity.equals(secondElementQuantity)){
                    return new GenderDto(GenderType.INCONCLUSIVE);
                } else {
                    return Optional.of(groupedGendersResult)
                            .map(list -> list.get(0))
                            .map(Map.Entry::getKey)
                            .orElse(null);
                }
            }
        }
    }
}
