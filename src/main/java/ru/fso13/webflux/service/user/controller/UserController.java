package ru.fso13.webflux.service.user.controller;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import ru.fso13.webflux.service.user.dto.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<UserDto> getById(@PathVariable("id") String id) {
        if (!NumberUtils.isCreatable(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return Mono.just(UserDto.builder().id(NumberUtils.createLong(id)).age((short) 24).name("Tester").build());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public Mono<UserDto> create(@RequestBody UserDto dto) {
        dto.setId(RandomUtils.nextLong());
        return Mono.just(dto);
    }
}
