package com.sitarski.silenteightapp.repository.gender.boundary;

import com.sitarski.silenteightapp.common.entity.GenderType;
import com.sitarski.silenteightapp.repository.gender.entity.Gender;
import com.sitarski.silenteightapp.repository.token.boundary.TokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@Repository
@RequestScope
public class GenderRepository {

    private ResourceLoader resourceLoader;
    private static final Logger logger = LoggerFactory.getLogger(TokenRepository.class);
    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Autowired
    public GenderRepository(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Gender findGenderByName(String name){

        try {
            CompletableFuture<Gender> cf1 = CompletableFuture.supplyAsync(() -> findNameInFileTask(GenderType.MALE, name), executorService);
            CompletableFuture<Gender> cf2 = CompletableFuture.supplyAsync(() -> findNameInFileTask(GenderType.FEMALE, name), executorService);

            CompletableFuture<Gender> cf5 = anyOf(List.of(cf1, cf2)).thenApply(Gender.class::cast);
            Gender gender = cf5.join();

            logger.info("findGenderByName(name={}) - gender has been found by name", name);

            return gender;
        } catch (RuntimeException runtimeException) {
            logger.info("findGenderByName(name={}) - gender has not been found by name", name);
            return new Gender(GenderType.INCONCLUSIVE);
        }
    }

    private Gender findNameInFileTask(GenderType genderType, String name){

        String fileName = Optional.of(genderType)
                .map(GenderType::toString)
                .map(String::toLowerCase)
                .orElse(null);

        try (InputStream inputStream = resourceLoader.getResource("classpath:" + fileName + ".txt").getInputStream(); Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                String token = scanner.next();
                if (name.equals(token)) {
                    logger.debug("findNameInFileTask(genderType={}, name={}) - gender has been found", genderType, name);
                    return new Gender(genderType);
                }
            }
            throw new RuntimeException();
        } catch (IOException iOException) {
            logger.error("findNameInFileTask(genderType={}, name={}) - tokens file not found", genderType, name);
        }

        throw new NoSuchElementException();
    }

    private <T> CompletableFuture<T> anyOf(List<? extends CompletionStage<? extends T>> completableFutureTasks) {

        CompletableFuture<T> f=new CompletableFuture<>();
        Consumer<T> complete=f::complete;
        CompletableFuture.allOf(
                completableFutureTasks.stream().map(s -> s.thenAccept(complete)).toArray(CompletableFuture<?>[]::new)
        ).exceptionally(ex -> { f.completeExceptionally(ex); return null; });
        return f;
    }
}
