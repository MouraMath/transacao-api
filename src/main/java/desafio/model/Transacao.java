package desafio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Entidade que representa uma transação financeira.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {
    private BigDecimal valor;
    private OffsetDateTime dataHora;
}
