package br.com.fiap.fiaplus.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document
public class Video {

    @Id
    private String id;
    private String title;
    private String description;
    private String url;
    private LocalDateTime dateRegister;

}