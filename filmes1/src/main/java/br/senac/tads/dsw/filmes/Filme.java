package br.senac.tads.dsw.filmes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório.")
    private String titulo;

    @NotBlank(message = "O gênero é obrigatório.")
    private String genero;

    @NotNull(message = "O ano de lançamento é obrigatório.")
    @Min(value = 1888, message = "Ano inválido.")
    private Integer ano;

    // Método setId, caso necessário
    public void setId(Long id) {
        this.id = id;
    }
}
