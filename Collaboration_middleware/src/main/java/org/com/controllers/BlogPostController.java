package org.com.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.com.model.Error;
import org.com.model.BlogComment;
import org.com.model.BlogPost;
import org.com.model.User;
import org.com.service.BlogPostService;
import org.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogPostController {
	
	@Autowired
	private BlogPostService blogPostService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/addblogpost",method=RequestMethod.POST)
	public ResponseEntity<?> addBlogPost(@RequestBody BlogPost blogPost,HttpSession session){
		
		String username=(String)session.getAttribute("username");
		if(username==null)
		{
			Error error=new Error(5,"unauthorized access...");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		
		blogPost.setPostedOn(new Date());
		User postedBy=userService.getUserByUsername(username);
		blogPost.setPostedBy(postedBy); // postedby is an object of type user
		try {
		blogPostService.addBlogPost(blogPost);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		}
		catch(Exception e) {
			Error error = new Error(7,"unble to save blog post details");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/getblogs/{approved}",method=RequestMethod.GET)
	// getblogs/0 -> blogs waiting for approval
	//getblogs/1 ->	blogs which are approved
	public ResponseEntity<?> getBlogs(@PathVariable int approved,HttpSession session){
		
		String username=(String)session.getAttribute("username");
		if(username==null)
		{
			Error error=new Error(5,"unauthorized access...");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED); //unauthorized
		}
		List<BlogPost> blogs=blogPostService.getBlogs(approved);
		return new ResponseEntity<List<BlogPost>>(blogs,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getblogbyid/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> getBlogById(@PathVariable int id, HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null)
		{
			Error error=new Error(5,"unauthorized access...");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED); //unauthorized
		}
		BlogPost blogPost = blogPostService.getBlogById(id);
		return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public ResponseEntity<?> updateBlogPost(@RequestBody BlogPost blogPost, HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null) {
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		//admin has rejected the blogpost but reason is not mentioned
		if(!blogPost.isApproved() && blogPost.getRejectionReason()==null)
			blogPost.setRejectionReason("Not mentioned");
		blogPostService.updateBlogPost(blogPost);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/addcomment",method=RequestMethod.POST)
	public ResponseEntity<?> addBlogComment(@RequestBody BlogComment blogComment,HttpSession session){
		
		String username=(String)session.getAttribute("username");
		if(username==null) {
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		//commentedBy?-> user
		User user=userService.getUserByUsername(username);
		blogComment.setCommentedBy(user);
		blogComment.setCommentedOn(new Date());
		try {
		blogPostService.addBlogComment(blogComment);
		return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
		}catch(Exception e) {
			Error error = new Error(7,"unable to post comments");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/getcomments/{blogPostId}",method=RequestMethod.GET)
	public ResponseEntity<?> getComments(@PathVariable int blogPostId, HttpSession session){
	
		String username=(String)session.getAttribute("username");
		if(username==null) {
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<BlogComment> blogComments=blogPostService.getBlogComments(blogPostId);
		return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getnotification",method=RequestMethod.GET)
	public ResponseEntity<?> getNotification(HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null) {
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		List<BlogPost> blogPostNotification=blogPostService.getNotification(username);
		return new ResponseEntity<List<BlogPost>>(blogPostNotification,HttpStatus.OK);
	}
		
}
