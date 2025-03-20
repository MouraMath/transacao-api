package desafio.repository;

import desafio.model.Transacao;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 * Repositório responsável pelo armazenamento e recuperação de transações.
 */
@Repository
public class TransacaoRepository {
    private final ConcurrentLinkedQueue<Transacao> transacoes = new ConcurrentLinkedQueue<>();

    /**
     * Adiciona uma nova transação.
     */
    public void adicionar(Transacao transacao) {
        transacoes.add(transacao);
    }

    /**
     * Remove todas as transações.
     */
    public void limparTudo() {
        transacoes.clear();
    }

    /**
     * Busca transações dentro de um intervalo de tempo especificado.
     *
     * @param segundos O intervalo de tempo em segundos
     * @return Lista de transações dentro do intervalo
     */
    public List<Transacao> buscarPorIntervalo(int segundos) {
        OffsetDateTime limiteTempo = OffsetDateTime.now().minusSeconds(segundos);

        return transacoes.stream()
                .filter(t -> t.getDataHora().isAfter(limiteTempo))
                .collect(Collectors.toList());
    }
}
