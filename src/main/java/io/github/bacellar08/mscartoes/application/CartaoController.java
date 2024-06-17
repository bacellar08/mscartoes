package io.github.bacellar08.mscartoes.application;

import io.github.bacellar08.mscartoes.domain.Cartao;
import io.github.bacellar08.mscartoes.domain.ClienteCartao;
import io.github.bacellar08.mscartoes.domain.dto.CartaoDTO;
import io.github.bacellar08.mscartoes.domain.dto.CartoesPorClienteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class CartaoController {


    private final CartaoService Cartaoservice;
    private final ClienteCartaoService ClienteCartaoservice;
    private final ClienteCartaoService clienteCartaoService;


    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity saveCartao(@RequestBody CartaoDTO request) {

        Cartao novoCartao = request.toModel();
        Cartaoservice.saveCartao(novoCartao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCartao);
    }
    // /cartoes?renda={renda}
    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaMenorIgual(@RequestParam("renda") Long renda) {
        List<Cartao> listaCartoes = Cartaoservice.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(listaCartoes);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteDTO>> getCartoesByCliente(@RequestParam("cpf") String cpf) {

        List<ClienteCartao> list = clienteCartaoService.listCartoesByCpf(cpf);
        List<CartoesPorClienteDTO> resultList = list.stream()
                .map(CartoesPorClienteDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);

    }
}
