package io.github.bacellar08.mscartoes.domain.dto;

import io.github.bacellar08.mscartoes.domain.ClienteCartao;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CartoesPorClienteDTO {

    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

    public static CartoesPorClienteDTO fromModel(ClienteCartao model) {

        return new CartoesPorClienteDTO(
                model.getCartao().getNome(),
                model.getCartao().getBandeira().toString(),
                model.getLimite()
        );
    }
}
