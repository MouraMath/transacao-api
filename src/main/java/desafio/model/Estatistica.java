package desafio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Representa as estatísticas calculadas de transações.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estatistica {
    private BigDecimal soma = BigDecimal.ZERO;
    private BigDecimal min = BigDecimal.ZERO;
    private BigDecimal max = BigDecimal.ZERO;
    private BigDecimal media = BigDecimal.ZERO;
    private long count = 0;
}
