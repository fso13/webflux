package ru.fso13.webflux.service.project.mapper;

import ru.fso13.webflux.service.project.dao.Project;
import ru.fso13.webflux.service.project.dto.ProjectDto;

public final class ProjectMapper {

    ProjectMapper() {
    }

    public static Project fromDto(ProjectDto dto) {
        return Project.builder()
                .name(dto.getName())
                .build();
    }

    public static ProjectDto fromEntity(Project entity) {
        return ProjectDto.builder()
                .name(entity.getName())
                .id(entity.getId())
                .build();
    }
}
