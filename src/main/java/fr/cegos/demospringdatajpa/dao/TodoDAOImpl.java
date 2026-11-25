package fr.cegos.demospringdatajpa.dao;

import fr.cegos.demospringdatajpa.bo.Todo;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // @Repository : permet comme @Component ou @Service de mettre dans le contexte Spring la classe de DAO
@AllArgsConstructor
public class TodoDAOImpl implements TodoDAO {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final String SELECT_ALL = "SELECT id, name, duedate from todo";
    private static final String SEARCH = "SELECT id, name, duedate from todo WHERE name like '%:search%'";
    private static final String DELETE_BY_ID = "DELETE from todo WHERE id = ?";
    private static final String INSERT_INTO = "INSERT INTO todo (name, duedate) VALUES (?,?)";
    private static final String UPDATE = "UPDATE todo SET name = :name, duedate = :duedate WHERE id = :id";

    @Override
    public List<Todo> findAll() {
        return jdbcTemplate.query(SELECT_ALL, new BeanPropertyRowMapper<>(Todo.class));
    }

    @Override
    public List<Todo> search(String search) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("search", search);
        return namedParameterJdbcTemplate.query(SEARCH, params, new BeanPropertyRowMapper<>(Todo.class));
    }

    @Override
    public Todo save(Todo todo) {
        jdbcTemplate.update(INSERT_INTO, todo.getName(), todo.getDueDate());
        return todo;
    }

    @Override
    public void update(Todo todo) {
        // utilisation de paramètres nommés (moins de risque d'erreurs)
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", todo.getName());
        params.addValue("duedate", todo.getDueDate());
        params.addValue("id", todo.getId());
        namedParameterJdbcTemplate.update(UPDATE, params);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(DELETE_BY_ID, id);
    }
}