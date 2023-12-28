package br.com.fiap.fiaplus.controller;

import br.com.fiap.fiaplus.document.User;
import br.com.fiap.fiaplus.model.UserCriteria;
import br.com.fiap.fiaplus.model.UserRequest;
import br.com.fiap.fiaplus.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<Mono<User>> create(@RequestBody @Valid UserRequest request){
        log.info("[UserController] - create");
        return ResponseEntity.status(CREATED)
                .body(userService.create(request));
    }

    @GetMapping
    public ResponseEntity<Mono<PageImpl<User>>> listAll(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "DESC") String direction, UserCriteria userCriteria){

        log.info("[UserController] - listAll");

        var pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(direction), "dateRegister");

        return ResponseEntity.status(OK)
                .body(userService.listAll(pageRequest, userCriteria));

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Mono<User>> listById(@PathVariable String id){
        log.info("[UserController] - listById");
        return ResponseEntity.status(OK)
                .body(userService.listById(id));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Mono<User>> updateById(@PathVariable String id, @RequestBody @Valid UserRequest request){
        log.info("[UserController] - updateById");
        return ResponseEntity.status(OK)
                .body(userService.update(id, request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        log.info("[UserController] - listById");
        userService.deleteById(id);
        return ResponseEntity.status(OK).build();
    }

}