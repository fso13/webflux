package ru.fso13.webflux.service.project;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.fso13.webflux.service.project.dto.ProjectDto;

public interface ProjectService {
    Flux<ProjectDto> getAll();

    Mono<ProjectDto> create(ProjectDto projectDto);
}
