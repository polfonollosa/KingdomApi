package com.tecnocampus.examsimulation.Persistence;

import com.tecnocampus.examsimulation.Entities.Kingdom;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class KingdomRepository {

    private final JdbcClient jdbcClient;

    public KingdomRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Kingdom save(Kingdom king) {
        jdbcClient.sql("""
            INSERT INTO KINGDOMS (id, dateOfCreation, gold, citizens, food)
            VALUES (?, ?, ?, ?, ?)
        """).params(
                king.getId(),
                king.getDateOfCreation(),
                king.getGold(),
                king.getCitizens(),
                king.getFood()
        ).update();

        return king;
    }

    public List<Kingdom> findAll() {
        return jdbcClient.sql("SELECT * FROM KINGDOMS")
                .query(this::mapKingdom)
                .list();
    }

    public void updateKingdom(String id, int gold, int citizens, int food) {
        jdbcClient.sql("""
            UPDATE KINGDOMS SET gold = ?, citizens = ?, food = ? WHERE id = ?
        """).params(gold, citizens, food, id).update();
    }

    public void deleteKingdom(String id) {
        jdbcClient.sql("DELETE FROM KINGDOMS WHERE id = ?")
                .param(id)
                .update();
    }

    public Optional<Kingdom> findKingdom(String id) {
        return jdbcClient.sql("SELECT * FROM KINGDOMS WHERE id = ?")
                .param(id)
                .query(this::mapKingdom)
                .optional();
    }

    public boolean existsKingdom(String id) {
        return jdbcClient.sql("SELECT * FROM KINGDOMS WHERE ID = ?").params(id).query(Long.class).single() > 0;
    }

    public Optional<Kingdom> findTheRichest() {
        return jdbcClient
                .sql("SELECT * FROM KINGDOMS ORDER BY gold DESC LIMIT 1")
                .query(this::mapKingdom)
                .optional();
    }


    private Kingdom mapKingdom(ResultSet rs, int rowNum) throws SQLException {
        Kingdom king = new Kingdom();
        king.setParam(rs.getString("id"), rs.getString("dateOfCreation"));
        king.setGold(rs.getInt("gold"));
        king.setCitizens(rs.getInt("citizens"));
        king.setFood(rs.getInt("food"));
        return king;
    }
}
