package ru.fso13.webflux.service.project.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("TB_PROJECTS")
public class Project {

    @Id
    @Column("ID")
    private Integer id;

    @Column("NAME")
    private String name;


}
