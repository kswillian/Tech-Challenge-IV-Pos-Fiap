package br.com.fiap.fiaplus.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collection;

@With
@Data
@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String name;

    @Indexed(unique = true)
    private String email;
    private Collection<Video> favorites;
    private LocalDateTime dateRegister;

}