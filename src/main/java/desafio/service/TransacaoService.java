package desafio.service;

import desafio.model.Estatistica;
import desafio.model.Transacao;
import desafio.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Serviço que implementa a lógica de negócio para gerenciar transações.
 */
@Service
@RequiredArgsConstructor
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;

    /**
     * Adiciona uma transação se ela estiver dentro do período válido.
     *
     * @param transacao A transação a ser adicionada
     * @return true se a transação foi aceita, false caso contrário
     */
    public boolean adicionarTransacao(Transacao transacao) {
        OffsetDateTime limiteAntigo = OffsetDateTime.now().minusSeconds(60);

        if (transacao.getDataHora().isBefore(limiteAntigo)) {
            return false;
        }

        transacaoRepository.adicionar(transacao);
        return true;
    }

    /**
     * Remove todas as transações.
     */
    public void limparTransacoes() {
        transacaoRepository.limparTudo();
    }

    /**
     * Calcula estatísticas das transações dentro do intervalo especificado.
     *
     * @param intervaloSegundos O intervalo de tempo em segundos
     * @return Objeto contendo as estatísticas calculadas
     */
    public Estatistica calcularEstatisticas(int intervaloSegundos) {
        List<Transacao> transacoesRecentes = transacaoRepository.buscarPorIntervalo(intervaloSegundos);

        if (transacoesRecentes.isEmpty()) {
            return new Estatistica();
        }

        BigDecimal soma = transacoesRecentes.stream()
                .map(Transacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal min = transacoesRecentes.stream()
                .map(Transacao::getValor)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        BigDecimal max = transacoesRecentes.stream()
                .map(Transacao::getValor)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        long count = transacoesRecentes.size();

        BigDecimal media = count > 0
                ? soma.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;

        return new Estatistica(soma, min, max, media, count);
    }
}
