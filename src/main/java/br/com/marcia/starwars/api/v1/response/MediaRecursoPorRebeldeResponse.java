package br.com.marcia.starwars.api.v1.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MediaRecursoPorRebeldeResponse {

    private String descricao;

    private Double media;
}
