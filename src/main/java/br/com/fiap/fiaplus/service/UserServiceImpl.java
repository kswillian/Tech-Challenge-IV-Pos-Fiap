package br.com.fiap.fiaplus.service;

import br.com.fiap.fiaplus.builder.CriteriaBuilder;
import br.com.fiap.fiaplus.document.User;
import br.com.fiap.fiaplus.mapper.UserMapper;
import br.com.fiap.fiaplus.model.UserCriteria;
import br.com.fiap.fiaplus.model.UserRequest;
import br.com.fiap.fiaplus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static br.com.fiap.fiaplus.util.Utils.handleNotFound;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements GenericService<User, UserRequest> {

    private final ReactiveMongoTemplate mongoTemplate;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Mono<User> create(UserRequest request) {
        log.info("[UserService] - create");
        var video = userMapper.toEntity(request);
        return userRepository.save(video);
    }

    @Override
    public Mono<PageImpl<User>> listAll(Pageable pageable, Object criteria) {

        log.info("[UserService] - listAll");
        Query query = new Query()
                .addCriteria(CriteriaBuilder.buildUserCriteria((UserCriteria) criteria))
                .with(pageable);

        Flux<User> users = mongoTemplate.find(query, User.class);

        return users.collectList()
                .zipWith(userRepository.count())
                .map(video -> new PageImpl<>(video.getT1(), pageable, video.getT2()));

    }

    @Override
    public Mono<User> listById(String id) {
        log.info("[UserService] - listById");
        return handleNotFound(userRepository.findById(id), id);
    }

    @Override
    public Mono<User> update(String id, UserRequest request) {
        log.info("[UserService] - update");
        return listById(id)
                .map(video -> userMapper.toEntity(request, video))
                .flatMap(userRepository::save);
    }

    @Override
    public void deleteById(String id) {
        log.info("[UserService] - deleteById");
        var user = handleNotFound(userRepository.findById(id), id);
        userRepository.delete(user.block()).block();
    }

}