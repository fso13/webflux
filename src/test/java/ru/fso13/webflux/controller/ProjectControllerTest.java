package ru.fso13.webflux.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.fso13.webflux.service.project.controller.ProjectController;
import ru.fso13.webflux.service.project.dao.Project;
import ru.fso13.webflux.service.project.dao.ProjectRepository;
import ru.fso13.webflux.service.project.dto.ProjectDto;
import ru.fso13.webflux.service.project.impl.service.ProjectServiceImpl;
import ru.fso13.webflux.service.project.mapper.ProjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith({SpringExtension.class})
@WebFluxTest(controllers = ProjectController.class)
@Import(ProjectServiceImpl.class)
@AutoConfigureRestDocs
class ProjectControllerTest {
    @MockBean
    ProjectRepository repository;

    @Autowired
    private WebTestClient webClient;

    @Test
    void postProject() {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName("Test");

        Project project = ProjectMapper.fromDto(ProjectDto.builder().build());
        project.setId(1);

        Mockito.when(repository.save(any())).thenReturn(Mono.just(project));

        webClient.post()
                .uri("/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(projectDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id")
                .isEqualTo(project.getId())
                .jsonPath("$.name")
                .isEqualTo(project.getName())
                .consumeWith(WebTestClientRestDocumentation.document("project/{method-name}/{step}",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint())));

        Mockito.verify(repository, times(1)).save(any());
    }

    @Test
    void getAllProject() {

        List<Project> projects = Arrays.asList(Project.builder().id(1).name("Test1").build(), Project.builder().id(2).name("Test2").build());
        Mockito.when(repository.findAll()).thenReturn(Flux.just(Project.builder().id(1).name("Test1").build(), Project.builder().id(2).name("Test2").build()));

        webClient.get()
                .uri("/projects")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id")
                .isEqualTo(projects.get(0).getId())
                .jsonPath("$[1].name")
                .isEqualTo(projects.get(1).getName())
                .consumeWith(WebTestClientRestDocumentation.document("project/{method-name}/{step}",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint())));

        Mockito.verify(repository, times(1)).findAll();
    }
}
