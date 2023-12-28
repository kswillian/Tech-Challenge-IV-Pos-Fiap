package br.com.fiap.fiaplus.service;

import org.springframework.data.domain.PageImpl;
import reactor.core.publisher.Mono;

import org.springframework.data.domain.Pageable;
import java.io.Serializable;

public interface GenericService<T, D> extends Serializable {

    Mono<T> create(final D request);
    Mono<PageImpl<T>> listAll(final Pageable pageable, final Object criteria);
    Mono<T> listById(final String id);
    Mono<T> update(final String id, final D request);
    void deleteById(final String id);

}