import util.ConnectionUtil;

import java.sql.Connection;

void main() {

    try (Connection conn = ConnectionUtil.H2.getConnection()) {


    } catch ( Exception e ) {

    }


}