package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.ClientDao;
import ar.edu.itba.paw.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ClientJdbcDao implements ClientDao {

    public static final String CLIENT_TABLE_NAME = "clients";
    public static final String CLIENT_COLUMN_ID = "client_id";
    public static final String CLIENT_COLUMN_USERNAME = "username";
    public static final String CLIENT_COLUMN_PASSWORD = "password";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<Client> ROW_MAPPER = new RowMapper<Client>() {

        @Override
        public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Client(rs.getInt(CLIENT_COLUMN_ID), rs.getString(CLIENT_COLUMN_USERNAME), rs.getString(CLIENT_COLUMN_PASSWORD));
        }

    };

    @Autowired
    public ClientJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(CLIENT_TABLE_NAME)
                .usingGeneratedKeyColumns(CLIENT_COLUMN_ID);
    }

    public Client findById(int id) {
        List<Client> list = jdbcTemplate.query("SELECT * FROM " + CLIENT_TABLE_NAME + " WHERE " + CLIENT_COLUMN_ID + " = ?", ROW_MAPPER, id);
        return list.get(0);
    }

    @Override
    public Client create(String username, String password) {
        final Map<String, Object> args = new HashMap<>();
        args.put("username", username);
        args.put("password", password);

        final Number userid = jdbcInsert.executeAndReturnKey(args);
        return new Client(userid.intValue(), username, password);
    }

}
