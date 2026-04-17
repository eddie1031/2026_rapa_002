package db;

import db.streotype.RowMapper;
import db.streotype.StatementPlaceHolderBinder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    // 공통된 부분은 여기서 처리
    // 다른 부분만 받기
    private final DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 다건조회를 처리하는 메서드
    public <T> List<T> queryForList(
            String sql,
            StatementPlaceHolderBinder pss,
            RowMapper<T> mapper
    ) {

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {

            // select * from product where name = ?
            if ( pss != null ) {
                pss.set(stmt);
            }

            try (ResultSet rs = stmt.executeQuery() ) {

                List<T> result = new ArrayList<>();

                while (rs.next()) {
                    result.add(mapper.map(rs));
                }

                return result;

            }

        } catch ( SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
