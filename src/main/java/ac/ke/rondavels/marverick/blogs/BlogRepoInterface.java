package ac.ke.rondavels.marverick.blogs;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface BlogRepoInterface {

    public List<Blog> findAllBlogs();
    public Blog findBlogById(Long id);
    public void addBlog(Blog blog);
    public void updateBlog(Blog blog);
    public boolean isBlogExist(Blog blog);

    public void deleteBlogById(Long id);

    public void saveComments (Blog blog, String comment);

    public List<Comment> getCommentsByBlogId(Long blogId);


}
