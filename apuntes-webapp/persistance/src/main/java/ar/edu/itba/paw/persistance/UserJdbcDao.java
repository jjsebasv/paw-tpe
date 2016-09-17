package ar.edu.itba.paw.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.User;

@Repository
public class UserJdbcDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<User> ROW_MAPPER = new RowMapper<User>() {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            // TODO Auto-generated method stub
            return new User(rs.getString("username"), rs.getString("password"), rs.getInt("userid"));
        }

    };

    @Autowired
    public UserJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("userid");
    }

    ;

    public User findById(long id) {
        List<User> list = jdbcTemplate.query("SELECT * FROM users WHERE userid = ?",
                (ResultSet rs, int rowNum) -> {
                    return new User(rs.getString("username"), rs.getString("password"), rs.getInt("userid"));
                }, id);
        System.out.println(list);
        return list.get(0);
    }

    @Override
    public User create(String username, String password) {
        // TODO Auto-generated method stub
        final Map<String, Object> args = new HashMap<>();
        args.put("username", username);
        args.put("password", password);

        final Number userid = jdbcInsert.executeAndReturnKey(args);
        return new User(username, password, userid.longValue());
    }

}
