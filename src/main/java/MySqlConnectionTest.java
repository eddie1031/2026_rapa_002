import util.ConnectionUtil;

import java.sql.Connection;

void main() {

    try (Connection conn = ConnectionUtil.MySql.getConnection()) {

        String name = "김민수";
        String pwd = "1234";
        String email = "user1@example.com";
        String balance = "10000";

        String memberInsert = genMemberInsertQuery(name, pwd, email, balance);

    } catch ( Exception e ) {

    }

}

private static String genMemberInsertQuery(String name, String pwd, String email, String balance) {
    return """
            INSERT INTO member
              ( name, password, email, balance )
            VALUES
              ( '%s', '%s', '%s', '%s' )
            """.formatted(name, pwd, email, balance);
}