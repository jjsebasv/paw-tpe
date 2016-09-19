package ar.edu.itba.paw.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.interfaces.ReviewDao;
import ar.edu.itba.paw.models.Review;

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
    
	@Override
	public Review createReview(int reviewid, int fileid, int userid, double ranking, String review) {
		// TODO Auto-generated method stub
		System.out.println("estamo aca");
		return null;
	}

}
