package br.com.fiap.fiaplus.service;

import br.com.fiap.fiaplus.document.enums.Category;
import br.com.fiap.fiaplus.model.VideoCriteria;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.io.Serializable;


public interface VideoService<T, D> extends Serializable {

    Mono<T> create(final D request);
    Mono<PageImpl<T>> listAll(final Pageable pageable, final Object criteria);
    Mono<T> listById(final String id);
    Mono<T> update(final String id, final D request);
    void deleteById(final String id);

    Mono<T> findByTitle(final String title);

    Mono<PageImpl<T>> listAllByCategory(final Pageable pageable, final VideoCriteria criteria, Category category);

    Mono<PageImpl<T>> listAllByDate(final Pageable pageable, final VideoCriteria criteria, String timestamp);
}