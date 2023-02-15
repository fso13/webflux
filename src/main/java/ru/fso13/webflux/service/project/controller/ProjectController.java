package ru.fso13.webflux.service.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.fso13.webflux.service.project.dto.ProjectDto;
import ru.fso13.webflux.service.project.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProjectDto> getAll() {
        return projectService.getAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public Mono<ProjectDto> create(@RequestBody ProjectDto projectDto) {
        return projectService.create(projectDto);
    }
}
