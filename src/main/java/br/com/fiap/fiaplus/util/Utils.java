package br.com.fiap.fiaplus.util;

import br.com.fiap.fiaplus.document.Video;
import br.com.fiap.fiaplus.exception.ObjectNotFoundException;
import lombok.experimental.UtilityClass;
import reactor.core.publisher.Mono;

@UtilityClass
public class Utils {

    public static  <T> Mono<T> handleNotFound(Mono<T> mono, String id){

        var message = String.format(
                "Object not found. Id: %s, Type: %s", id, Video.class);

        return mono.switchIfEmpty(Mono.error(
                new ObjectNotFoundException(message)
        ));
    }

}