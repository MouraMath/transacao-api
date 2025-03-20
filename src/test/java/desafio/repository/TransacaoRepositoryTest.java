package desafio.repository;

import desafio.model.Transacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransacaoRepositoryTest {

    private TransacaoRepository repository;

    @BeforeEach
    void setUp() {
        repository = new TransacaoRepository();
    }

    @Test
    void deveAdicionarTransacao() {
        // Arrange
        Transacao transacao = new Transacao(
                new BigDecimal("100.00"),
                OffsetDateTime.now()
        );

        // Act
        repository.adicionar(transacao);
        List<Transacao> transacoes = repository.buscarPorIntervalo(60);

        // Assert
        assertEquals(1, transacoes.size());
        assertEquals(transacao, transacoes.get(0));
    }

    @Test
    void deveLimparTodasTransacoes() {
        // Arrange
        repository.adicionar(new Transacao(
                new BigDecimal("100.00"),
                OffsetDateTime.now()
        ));

        // Act
        repository.limparTudo();
        List<Transacao> transacoes = repository.buscarPorIntervalo(60);

        // Assert
        assertTrue(transacoes.isEmpty());
    }

    @Test
    void deveBuscarApenasTransacoesNoIntervalo() {
        // Arrange
        OffsetDateTime agora = OffsetDateTime.now();
        Transacao transacaoRecente = new Transacao(
                new BigDecimal("100.00"),
                agora
        );
        Transacao transacaoAntiga = new Transacao(
                new BigDecimal("200.00"),
                agora.minusSeconds(61)
        );

        repository.adicionar(transacaoRecente);
        repository.adicionar(transacaoAntiga);

        // Act
        List<Transacao> transacoes = repository.buscarPorIntervalo(60);

        // Assert
        assertEquals(1, transacoes.size());
        assertEquals(transacaoRecente, transacoes.get(0));
    }
}
