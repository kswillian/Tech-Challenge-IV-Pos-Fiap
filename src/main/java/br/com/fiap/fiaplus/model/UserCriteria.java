package br.com.fiap.fiaplus.model;

import br.com.fiap.fiaplus.document.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserCriteria {

    public String name;
    public LocalDateTime dateRegister;

    public User toUser() {
        return new User()
                .withDateRegister(this.dateRegister != null ? this.dateRegister : null)
                .withName(this.name != null ? this.name : null);
    }

}
