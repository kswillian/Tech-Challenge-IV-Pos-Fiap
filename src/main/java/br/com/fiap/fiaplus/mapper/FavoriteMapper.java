package br.com.fiap.fiaplus.mapper;

import br.com.fiap.fiaplus.document.Favorite;
import br.com.fiap.fiaplus.model.FavoriteRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface FavoriteMapper {

    @Mapping(target = "id", ignore = true)
    Favorite toEntity(final FavoriteRequest request);

    @Mapping(target = "id", ignore = true)
    Favorite toEntity(final FavoriteRequest request, @MappingTarget final Favorite favorite);

}