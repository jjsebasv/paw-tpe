package ar.edu.itba.paw.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import ar.edu.itba.paw.models.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.interfaces.ReviewDao;
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
    }

    private final static RowMapper<Review> ROW_MAPPER = new RowMapper<Review>() {

        @Override
        public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Review(rs.getInt("reviewid"),
                    new File(rs.getInt("fileid"),
                            null,//FIXME
                            null,//FIXME
                            rs.getString("subject"),
                            rs.getString("fileName"),
                            rs.getInt("fileSize"),
                            null),//FIXME
                    new User(rs.getInt("userid"),
                            rs.getString("username"),
                            null), //FIXME
                    rs.getDouble("ranking"),
                    rs.getString("review"));
        }

    };


    @Override
    public Review createReview(final File file, final User user, final double ranking, final String review) {
        final Map<String, Object> args = new HashMap<>();
        args.put("fileid", file.getFileid());
        args.put("userid", user.getUserid());
        args.put("ranking", ranking);
        args.put("review", review);

        final Number reviewid = jdbcInsert.executeAndReturnKey(args);
        return new Review(reviewid.intValue(), file, user, ranking, review);
    }

    @Override
    public List<Review> findByFileId(int fileid) {
        //TODO Check query
        return jdbcTemplate.query("SELECT reviews.reviewid, reviews.fileid AS fileid, files.subject, files.filename, files.fileSize," +
                "reviews.userid AS userid, users.username, reviews.ranking, reviews.review FROM reviews " +
                "INNER JOIN users ON users.userid=reviews.userid " +
                "INNER JOIN files ON files.fileid=reviews.fileid " +
                "WHERE reviews.fileid = ?", ROW_MAPPER, fileid);
    }

}
