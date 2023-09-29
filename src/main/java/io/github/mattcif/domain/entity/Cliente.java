package io.github.mattcif.domain.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "cliente")
public class Cliente {

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;
    @Column (name = "nome", length = 100)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;
    @Column(name = "cpf", length = 11)
    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY) // lazy tem melhor desempenho
    private Set<Pedido> pedidos;


}

