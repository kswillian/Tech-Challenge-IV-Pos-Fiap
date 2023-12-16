package br.com.fiap.fiaplus.document;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Video { // TODO: adicionar a anotação @Document

    private UUID id;
    private String title;
    private String description;
    private String url;
    private LocalDateTime dateRegister;

}