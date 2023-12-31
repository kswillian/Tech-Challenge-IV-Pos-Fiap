package br.com.fiap.fiaplus.document;

import br.com.fiap.fiaplus.document.enums.Category;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@With
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Video {

    @Id
    private String id;
    private String title;
    private String description;
    private Category category;
    private String url;
    private LocalDateTime dateRegister;

}