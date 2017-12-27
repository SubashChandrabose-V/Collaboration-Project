package org.com.service;

import java.util.List;

import org.com.model.BlogComment;
import org.com.model.BlogPost;

public interface BlogPostService {

	void addBlogPost(BlogPost blogPost);
	
	List<BlogPost> getBlogs(int approved);
	
	BlogPost getBlogById(int id);

	void updateBlogPost(BlogPost blogPost);
	
	void addBlogComment(BlogComment blogComment);

	List<BlogComment> getBlogComments(int blogPostId);

	public List<BlogPost> getNotification(String username);

}
