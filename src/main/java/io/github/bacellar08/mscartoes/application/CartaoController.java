package io.github.bacellar08.mscartoes.application;

import io.github.bacellar08.mscartoes.domain.Cartao;
import io.github.bacellar08.mscartoes.domain.dto.CartaoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CartaoController {


    private final CartaoService service;

    @GetMapping
    public String status() {
        return "ok";
    }

    public ResponseEntity saveCartao(@RequestBody CartaoDTO request) {

        Cartao novoCartao = request.toModel();
        service.saveCartao(novoCartao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCartao);
    }
}
