package ac.ke.rondavels.marverick.blogs;

import java.util.List;

public interface BlogServiceInter {
    public List<Blog> findAllBlogs();
    public Blog findBlogById(Long id);
    public void saveBlog(Blog blog);

    public void deleteBlogById(Long id);
    public void saveComments(Blog blog, String comment);

    public List<Comment> getCommentsByBlogId(Long blogId);

    public void updateBlog(Blog blog);






}
