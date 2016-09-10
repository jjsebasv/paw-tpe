package ar.edu.itba.paw.persistante;

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

import ar.edu.itba.paw.persistante.User;

@Repository
public class UserJdbcDao implements UserDao {

	private final JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert jdbcInsert;
	
	private final static RowMapper<User> ROW_MAPPER = new RowMapper<User>(){

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new User(rs.getString("username"), rs.getInt("userid"));
		}
		
	};

	@Autowired
	public UserJdbcDao(final DataSource ds){
		jdbcTemplate = new JdbcTemplate(ds);
		jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
				.withTableName("users")
				.usingGeneratedKeyColumns("userid");
		
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users("
				+ "userid SERIAL PRIMARY KEY,"
				+ "username varchar(100)"
				+ ")"
		);
	};

	public User findById(long id) {
		List<User> list = jdbcTemplate.query("SELECT * FROM users WHERE userid = ?",
				(ResultSet rs, int rowNum) -> { 
					return new User(rs.getString("username"), rs.getInt("userid"));
				}, id);
		return null;
	}

	@Override
	public User create(String username) {
		// TODO Auto-generated method stub
		final Map<String, Object> args = new HashMap<>();
		args.put("username", username);
		
		final Number userid = jdbcInsert.executeAndReturnKey(args);
		return new User(username, userid.longValue());
	}
	
}
