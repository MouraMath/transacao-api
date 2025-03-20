package desafio.controller;

import desafio.model.Estatistica;
import desafio.model.Transacao;
import desafio.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransacaoController.class)
public class TransacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransacaoService transacaoService;

    @Test
    void deveAdicionarTransacaoComSucesso() throws Exception {
        // Arrange
        when(transacaoService.adicionarTransacao(any(Transacao.class))).thenReturn(true);

        // Act & Assert
        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"valor\":100.00,\"dataHora\":\"2025-03-20T06:30:00-03:00\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void deveRejeitarTransacaoInvalida() throws Exception {
        // Arrange
        when(transacaoService.adicionarTransacao(any(Transacao.class))).thenReturn(false);

        // Act & Assert
        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"valor\":100.00,\"dataHora\":\"2025-03-20T06:30:00-03:00\"}"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveLimparTransacoes() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());

        verify(transacaoService, times(1)).limparTransacoes();
    }

    @Test
    void deveRetornarEstatisticas() throws Exception {
        // Arrange
        Estatistica estatistica = new Estatistica(
                new BigDecimal("600.0"),
                new BigDecimal("100.0"),
                new BigDecimal("300.0"),
                new BigDecimal("200.0"),
                3
        );

        when(transacaoService.calcularEstatisticas(60)).thenReturn(estatistica);

        // Act & Assert
        mockMvc.perform(get("/estatistica"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.soma").value("600.0"))
                .andExpect(jsonPath("$.min").value("100.0"))
                .andExpect(jsonPath("$.max").value("300.0"))
                .andExpect(jsonPath("$.media").value("200.0"))
                .andExpect(jsonPath("$.count").value(3));
    }
}
