package desafio.service;

import desafio.model.Estatistica;
import desafio.model.Transacao;
import desafio.repository.TransacaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @Mock
    private TransacaoRepository transacaoRepository;

    @InjectMocks
    private TransacaoService transacaoService;

    @Test
    void deveAdicionarTransacaoValida() {
        // Arrange
        Transacao transacao = new Transacao(
                new BigDecimal("100.00"),
                OffsetDateTime.now()
        );

        // Act
        boolean resultado = transacaoService.adicionarTransacao(transacao);

        // Assert
        assertTrue(resultado);
        verify(transacaoRepository, times(1)).adicionar(transacao);
    }

    @Test
    void deveRejeitarTransacaoMuitoAntiga() {
        // Arrange
        Transacao transacao = new Transacao(
                new BigDecimal("100.00"),
                OffsetDateTime.now().minusSeconds(61)
        );

        // Act
        boolean resultado = transacaoService.adicionarTransacao(transacao);

        // Assert
        assertFalse(resultado);
        verify(transacaoRepository, never()).adicionar(any());
    }

    @Test
    void deveCalcularEstatisticasCorretamente() {
        // Arrange
        Transacao t1 = new Transacao(new BigDecimal("100.00"), OffsetDateTime.now());
        Transacao t2 = new Transacao(new BigDecimal("200.00"), OffsetDateTime.now());
        Transacao t3 = new Transacao(new BigDecimal("300.00"), OffsetDateTime.now());

        when(transacaoRepository.buscarPorIntervalo(60))
                .thenReturn(Arrays.asList(t1, t2, t3));

        // Act
        Estatistica estatistica = transacaoService.calcularEstatisticas(60);

        // Assert
        assertEquals(new BigDecimal("600.00"), estatistica.getSoma());
        assertEquals(new BigDecimal("100.00"), estatistica.getMin());
        assertEquals(new BigDecimal("300.00"), estatistica.getMax());
        assertEquals(new BigDecimal("200.00"), estatistica.getMedia());
        assertEquals(3, estatistica.getCount());
    }

    @Test
    void deveRetornarEstatisticaVaziaQuandoSemTransacoes() {
        // Arrange
        when(transacaoRepository.buscarPorIntervalo(anyInt()))
                .thenReturn(Collections.emptyList());

        // Act
        Estatistica estatistica = transacaoService.calcularEstatisticas(60);

        // Assert
        assertEquals(BigDecimal.ZERO, estatistica.getSoma());
        assertEquals(BigDecimal.ZERO, estatistica.getMin());
        assertEquals(BigDecimal.ZERO, estatistica.getMax());
        assertEquals(BigDecimal.ZERO, estatistica.getMedia());
        assertEquals(0, estatistica.getCount());
    }
}
