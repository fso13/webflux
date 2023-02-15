package ru.fso13.webflux.service.project.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.fso13.webflux.service.project.dao.ProjectRepository;
import ru.fso13.webflux.service.project.dto.ProjectDto;
import ru.fso13.webflux.service.project.mapper.ProjectMapper;
import ru.fso13.webflux.service.project.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Override
    public Flux<ProjectDto> getAll() {
        return repository.findAll().map(ProjectMapper::fromEntity);
    }

    @Override
    public Mono<ProjectDto> create(ProjectDto projectDto) {
        return repository.save(ProjectMapper.fromDto(projectDto)).map(ProjectMapper::fromEntity);
    }
}
