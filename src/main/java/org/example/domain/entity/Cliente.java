package org.example.domain.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Integer id;
    @Column (name = "nome", length = 100)
    @NotEmpty(message = "Campo nome é obrigatório.")
    private String nome;
    @Column(name = "cpf", length = 11)
    private String cpf;
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY) // lazy tem melhor desempenho
    private Set<Pedido> pedidos;


}

