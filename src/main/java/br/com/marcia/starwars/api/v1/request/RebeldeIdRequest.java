package br.com.marcia.starwars.api.v1.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RebeldeIdRequest {

    @ApiModelProperty(example = "2", required = true)
    private Long rebeldeId;
}
