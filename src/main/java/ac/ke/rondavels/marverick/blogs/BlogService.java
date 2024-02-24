package ac.ke.rondavels.marverick.blogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class BlogService  implements BlogServiceInter{
    private BlogRepoInterface blogRepoInterface;
    @Autowired
    public BlogService(BlogRepoInterface blogRepoInterface) {
        this.blogRepoInterface = blogRepoInterface;
    }
    public List<Blog> findAllBlogs(){
        return blogRepoInterface.findAllBlogs();
    }
    @Override
    public Blog findBlogById(Long id) {
       return blogRepoInterface.findBlogById(id);
    }
   public void saveBlog(Blog blog){
       if (!blogRepoInterface.isBlogExist(blog)){
              blogRepoInterface.addBlog(blog);
         }else{
           blogRepoInterface.updateBlog(blog);}
   }

    public void deleteBlogById(Long id){
        blogRepoInterface.deleteBlogById(id);
    }
    public void saveComments(Blog blog, String comment){
        blogRepoInterface.saveComments(blog, comment);
    }
    @Override
    public List<Comment> getCommentsByBlogId(Long blogId) {
        return blogRepoInterface.getCommentsByBlogId( blogId);
    }
    }


