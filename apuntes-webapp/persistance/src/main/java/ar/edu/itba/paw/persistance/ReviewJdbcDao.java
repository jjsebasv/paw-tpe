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

import ar.edu.itba.paw.interfaces.ReviewDao;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Review;
import ar.edu.itba.paw.models.User;

@Repository
public class ReviewJdbcDao implements ReviewDao {

	private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public ReviewJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("reviews")
                .usingGeneratedKeyColumns("reviewid");
    };
	
    private final static RowMapper<Review> ROW_MAPPER = new RowMapper<Review>() {
    	
        @Override
        public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
        	return new Review(rs.getInt("reviewid"), rs.getInt("fileid"), rs.getInt("userid"), rs.getDouble("ranking"), rs.getString("review"));
        }

    };
    
    private final static RowMapper<User> USER_ROW_MAPPER = new RowMapper<User>() {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            // TODO Auto-generated method stub
            return new User(rs.getString("username"), rs.getString("password"), rs.getInt("userid"));
        }

    };
    
	@Override
	public Review createReview(int fileid, long userid, double ranking, String review) {
		// TODO Auto-generated method stub
        final Map<String, Object> args = new HashMap<>();
        args.put("fileid", fileid);
        args.put("userid", userid);
        args.put("ranking", ranking);
        args.put("review", review);

        final Number reviewid = jdbcInsert.executeAndReturnKey(args);
        return new Review(reviewid.intValue(), fileid, userid, ranking, review);
	}

	@Override
	public List<Review> findByFileId(int fileid) {
		// TODO Auto-generated method stub
		List<Review> list = jdbcTemplate.query("SELECT * FROM reviews WHERE fileid = ?", ROW_MAPPER, fileid);
		return list;
	}
	
	@Override
	public String getUsername(final int userid) {
		return jdbcTemplate.query("SELECT * FROM users WHERE userid = ?", USER_ROW_MAPPER, userid).get(0).getUsername();
	}

}
