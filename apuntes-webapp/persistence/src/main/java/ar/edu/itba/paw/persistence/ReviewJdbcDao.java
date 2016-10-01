package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.ReviewDao;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Review;
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

import static ar.edu.itba.paw.persistence.ClientJdbcDao.CLIENT_COLUMN_ID;
import static ar.edu.itba.paw.persistence.ClientJdbcDao.CLIENT_COLUMN_USERNAME;
import static ar.edu.itba.paw.persistence.DocumentJdbcDao.*;

@Repository
public class ReviewJdbcDao implements ReviewDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    /*package*/ static final String REVIEW_TABLE_NAME = "reviews";
    /*package*/ static final String REVIEW_COLUMN_ID = "review_id";
    /*package*/ static final String REVIEW_COLUMN_DOCUMENT_ID = "document_id";
    /*package*/ static final String REVIEW_COLUMN_CLIENT_ID = "client_id";
    /*package*/ static final String REVIEW_COLUMN_RANKING = "ranking";
    /*package*/ static final String REVIEW_COLUMN_REVIEW = "review";


    @Autowired
    public ReviewJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(REVIEW_TABLE_NAME)
                .usingGeneratedKeyColumns(REVIEW_COLUMN_ID);
    }

    private final static RowMapper<Review> ROW_MAPPER = new RowMapper<Review>() {

        @Override
        public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Review(rs.getInt(REVIEW_COLUMN_ID),
                    new Document(rs.getInt(REVIEW_COLUMN_DOCUMENT_ID),
                            null,//FIXME
                            null,//FIXME
                            rs.getString(DOCUMENT_COLUMN_SUBJECT),
                            rs.getString(DOCUMENT_COLUMN_NAME),
                            rs.getInt(DOCUMENT_COLUMN_SIZE),
                            null),//FIXME
                    new Client(rs.getInt(CLIENT_COLUMN_ID),
                            rs.getString(CLIENT_COLUMN_USERNAME),
                            null), //FIXME
                    rs.getDouble(REVIEW_COLUMN_RANKING),
                    rs.getString(REVIEW_COLUMN_REVIEW));
        }

    };


    @Override
    public Review createReview(final Document file, final Client user, final double ranking, final String review) {
        final Map<String, Object> args = new HashMap<>();
        args.put(DOCUMENT_COLUMN_DOCUMENT_ID, file.getDocumentId());
        args.put(DOCUMENT_COLUMN_CLIENT_ID, user.getClientId());
        args.put(REVIEW_COLUMN_RANKING, ranking);
        args.put(REVIEW_COLUMN_REVIEW, review);

        final Number reviewid = jdbcInsert.executeAndReturnKey(args);
        return new Review(reviewid.intValue(), file, user, ranking, review);
    }

    @Override
    public List<Review> findByFileId(int fileid) {
        //TODO Check query
        List<Review> list = jdbcTemplate.query("SELECT reviews.review_id, reviews.document_id AS document_id, documents.subject, documents.document_name, documents.document_size," +
                "reviews.client_id AS client_id, clients.username, reviews.ranking, reviews.review FROM reviews " +
                "INNER JOIN clients ON clients.client_id=reviews.client_id " +
                "INNER JOIN documents ON documents.document_id=reviews.document_id " +
                "WHERE reviews.document_id = ?", ROW_MAPPER, fileid);
        return list;
    }

    //8809
    @Override
    public double getAverage(int fileid) {
        return jdbcTemplate.queryForObject("SELECT ROUND(coalesce(AVG(" + REVIEW_COLUMN_RANKING + "), 0),2) " +
                "FROM " + REVIEW_TABLE_NAME + " WHERE " + REVIEW_COLUMN_DOCUMENT_ID + "= ?", Double.class, fileid);
    }

}
