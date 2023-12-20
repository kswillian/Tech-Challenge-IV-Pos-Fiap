package br.com.fiap.fiaplus.mapper;

import br.com.fiap.fiaplus.document.Video;
import br.com.fiap.fiaplus.model.VideoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface VideoMapper {

    @Mapping(target = "id", ignore = true)
    Video toEntity(final VideoRequest request);

    @Mapping(target = "id", ignore = true)
    Video toEntity(final VideoRequest request, @MappingTarget final Video user);

}