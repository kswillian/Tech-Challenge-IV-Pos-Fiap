package br.com.fiap.fiaplus.model;

import br.com.fiap.fiaplus.document.Video;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VideoCriteria {

    public String title;
    public LocalDateTime dateRegister;

    public Video toVideo() {
        return new Video()
                .withDateRegister(this.dateRegister != null ? this.dateRegister : null)
                .withTitle(this.title != null ? this.title : null);
    }

}
