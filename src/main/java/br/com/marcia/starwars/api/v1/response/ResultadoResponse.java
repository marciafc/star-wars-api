package br.com.marcia.starwars.api.v1.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResultadoResponse {

    private String resultado;
}
