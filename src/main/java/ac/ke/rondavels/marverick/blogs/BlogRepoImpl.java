package ac.ke.rondavels.marverick.blogs;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BlogRepoImpl implements BlogRepoInterface {
    JdbcTemplate jdbcTemplate;

    private RowMapper<Blog> blogRowMapper;


    public BlogRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        setBlogRowMapper();
    }


    private void setBlogRowMapper() {
        blogRowMapper = (resultSet, i) -> new Blog(
                resultSet.getLong("id"),
                resultSet.getString("title"),
                resultSet.getString("content"),
                resultSet.getString("description"),
                resultSet.getTimestamp("date_created").toLocalDateTime(),
                resultSet.getString("image"),
                null
        );
    }

    public List<Blog> findAllBlogs() {
        String sql = "SELECT * FROM blogs";
        return jdbcTemplate.query(sql, blogRowMapper);
    }


    public Blog findBlogById(Long id) {
        String sql = "SELECT * FROM blogs WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, blogRowMapper, id);
        } catch (EmptyResultDataAccessException ex) {
            throw new RuntimeException("blog not found");
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error while fetching blog by ID", ex);
        }
    }


    public void addBlog(Blog blog) {
        LocalDateTime dateCreated = LocalDateTime.now();
        String sql = "INSERT INTO blogs(title, content, description, date_created, image) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, blog.getTitle(), blog.getContent(), blog.getDescription(), dateCreated, blog.getImage());
    }



    public void updateBlog(Blog blog) {
        String sql = "UPDATE blogs SET title = ?, content = ?, description = ?, date_created = ?, image = ? WHERE id = ?";
        jdbcTemplate.update(sql, blog.getTitle(), blog.getContent(), blog.getDescription(), blog.getDateCreated(), blog.getImage(), blog.getId());
    }
    public boolean isBlogExist(Blog blog) {
        String sql = "SELECT COUNT(*) FROM blogs WHERE id = ?";
        try {
            int count = jdbcTemplate.queryForObject(sql, Integer.class, blog.getId());
            return count > 0;
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error while checking if blog exists", ex);
        }
    }

    public void deleteBlogById(Long id) {
        String sql = "UPDATE blogs SET is_showing = true WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }




    public void saveComments (Blog blog, String comment) {
      String sql = "INSERT INTO comments(comment, blog_id) VALUES(?,?)";
        jdbcTemplate.update(sql, comment, blog.getId());

    }


    public List<Comment> getCommentsByBlogId(Long blogId) {
        String sql = "SELECT comment, date_created FROM comments WHERE blog_id = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, blogId);

        List<Comment> comments = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            String comment = (String) row.get("comment");
            LocalDateTime createdAt = (LocalDateTime) row.get("date_created");

            Comment newComment = new Comment(blogId, comment, createdAt);
            comments.add(newComment);
        }

        return comments;
    }










}









