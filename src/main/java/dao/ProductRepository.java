package dao;

import db.JdbcTemplate;
import db.config.DataSourceConfiguration;
import domain.Product;

import java.util.List;

public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository() {
        this.jdbcTemplate = new JdbcTemplate(
                DataSourceConfiguration.getDataSource()
        );
    }

    public List<Product> findAll() {

        String sql = """
        SELECT
            *
        FROM
            product
        """;

        return jdbcTemplate.queryForList(
                sql,
                null,
                rs ->
                        new Product(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getInt("price"),
                                rs.getInt("stock")
                        )

        );

    }


}
