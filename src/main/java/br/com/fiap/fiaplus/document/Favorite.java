package br.com.fiap.fiaplus.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

@Data
@With
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {

    @Id
    private String id;

    @DBRef
    private User user;

    @DBRef
    private Video video;

    private LocalDateTime dateAdded;

}