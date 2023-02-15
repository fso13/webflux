package ru.fso13.webflux.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import ru.fso13.webflux.service.project.dao.Project;
import ru.fso13.webflux.service.user.controller.UserController;
import ru.fso13.webflux.service.user.dto.UserDto;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;

@ExtendWith({SpringExtension.class})
@WebFluxTest(controllers = UserController.class)
@AutoConfigureRestDocs
class UserControllerTest {
    @Autowired
    private WebTestClient webClient;

    @Test
    void postUser() {
        UserDto userDto = UserDto.builder().age((short) 24).name("Test").build();

        webClient.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(userDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id")
                .isNotEmpty()
                .jsonPath("$.name")
                .isEqualTo(userDto.getName())
                .consumeWith(WebTestClientRestDocumentation.document("user/{method-name}/{step}",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint())));
    }

    @Test
    void getUserById() {
        webClient.get()
                .uri("/users/{id}", 1)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id")
                .isEqualTo(1)
                .consumeWith(WebTestClientRestDocumentation.document("user/{method-name}/{step}",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint())));
    }

    @Test
    void getUserByIdBad() {
        webClient.get()
                .uri("/users/{id}", "not number")
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .consumeWith(WebTestClientRestDocumentation.document("user/{method-name}/{step}",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint())));
    }
}
