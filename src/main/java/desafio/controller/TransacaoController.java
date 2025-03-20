package desafio.controller;

import desafio.model.Estatistica;
import desafio.model.Transacao;
import desafio.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller REST que expõe endpoints para gerenciamento de transações.
 */
@RestController
@RequiredArgsConstructor
public class TransacaoController {
    private final TransacaoService transacaoService;

    /**
     * Endpoint para adicionar uma nova transação.
     */
    @PostMapping("/transacao")
    public ResponseEntity<Void> adicionarTransacao(@RequestBody Transacao transacao) {
        boolean resultado = transacaoService.adicionarTransacao(transacao);
        return resultado ? new ResponseEntity<>(HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint para remover todas as transações.
     */
    @DeleteMapping("/transacao")
    public ResponseEntity<Void> limparTransacoes() {
        transacaoService.limparTransacoes();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint para obter estatísticas das transações.
     */
    @GetMapping("/estatistica")
    public ResponseEntity<Estatistica> obterEstatisticas(
            @RequestParam(value = "intervaloSegundos", defaultValue = "60") int intervaloSegundos) {
        Estatistica estatistica = transacaoService.calcularEstatisticas(intervaloSegundos);
        return new ResponseEntity<>(estatistica, HttpStatus.OK);
    }
}
