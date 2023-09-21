package org.example.domain.repositorio;

import org.example.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;
    @Transactional
    public Cliente salvar(Cliente cliente) {
        entityManager.persist(cliente);
        return cliente;
    }
    @Transactional
    public Cliente atualizar(Cliente cliente){
        entityManager.merge(cliente);
        return cliente;
    }
    @Transactional
    public void deletar(Cliente cliente){
        if(!entityManager.contains(cliente)) {
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }
    @Transactional
    public void deletar(Integer id) {
        Cliente cliente = entityManager.find(Cliente.class, id);
        deletar(cliente);
    }
    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNome(String nome) {
        String jpql = " select c from Cliente c where c.nome like :nome "; //: define o parametro
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%" + nome +"%");
        return query.getResultList();
    }
    @Transactional(readOnly = true)
    public List<Cliente> obterTodos() {
        return entityManager
                .createQuery("from Cliente", Cliente.class)
                .getResultList();
    }

    private RowMapper<Cliente> obterClienteMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }
}
