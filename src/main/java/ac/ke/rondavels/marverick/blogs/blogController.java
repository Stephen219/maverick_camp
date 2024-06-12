package ac.ke.rondavels.marverick.blogs;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class blogController {

    private final BlogServiceInter blogServiceInter;


    @Autowired
    public blogController(BlogServiceInter blogServiceInter1) {
        this.blogServiceInter = blogServiceInter1;
    }


    @GetMapping("/add_blog")
    public ModelAndView blog(){
        ModelAndView mv = new ModelAndView("blogs/addBlog");
        Blog blog = new Blog();
        mv.addObject("blog", blog);
        return mv;
    }

    @PostMapping("/add_blog")
    public ModelAndView addBlog(@Valid Blog blog, BindingResult bindingResult,@RequestParam(required = false) Long id){
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("blogs/addBlog");
            mv.addObject("blog", blog);
            return mv;
        }
        if (id != null) {
            blog.setId(id);
            blogServiceInter.updateBlog(blog);
        } else {
            blogServiceInter.saveBlog(blog);

        }
        return new ModelAndView("redirect:/all_blogs");

    }

    @GetMapping("/all_blogs")
    public ModelAndView allBlogs(){
        List<Blog> blogs = blogServiceInter.findAllBlogs();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy h:mma");
        for (Blog blog : blogs) {
            LocalDateTime blogDate = blog.getDateCreated();
            String formattedDate = blogDate.format(formatter);
            blog.setFormattedDate(formattedDate);
        }
        return new ModelAndView("blogs/allBlogs").addObject("blogs", blogs);
    }

    @GetMapping("/blog/{id}")
    public ModelAndView blogById(@PathVariable Long id) {
        try {
            Blog blog = blogServiceInter.findBlogById(id);

            LocalDateTime blogDate = blog.getDateCreated();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy h:mma");
            String formattedDate = blogDate.format(formatter);

           List<Comment> comments = blogServiceInter.getCommentsByBlogId(id);
            System.out.println(comments);

            return new ModelAndView("blogs/individualblog")
                    .addObject("comments", comments)
                    .addObject("blog", blog)
                    .addObject("formattedDate", formattedDate);

        } catch (RuntimeException e) {
            return new ModelAndView("redirect:/404/");
        }
    }


    @PostMapping("/blog/{id}")
    public ModelAndView blogComment(@PathVariable Long id,
                                    @RequestParam String comment)
    {
        Blog blog = blogServiceInter.findBlogById(id);
        blogServiceInter.saveComments(blog, comment);
        return new ModelAndView("redirect:/blog/" + id);
    }

    @GetMapping("/edit_blog")
    public ModelAndView editBlog(@RequestParam Long id){
        Blog blog = blogServiceInter.findBlogById(id);
        ModelAndView mv = new ModelAndView("blogs/addBlog");
        mv.addObject("blog", blog);
        return mv;
    }


    @PostMapping("/delete_blog/{id}")
    public ModelAndView deleteBlog(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            blogServiceInter.deleteBlogById(id);
            redirectAttributes.addFlashAttribute("message", "Blog deleted successfully");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", "Blog could not be deleted");
            return new ModelAndView("redirect:/all_blogs");
        }
        return new ModelAndView("redirect:/all_blogs");
    }


}
