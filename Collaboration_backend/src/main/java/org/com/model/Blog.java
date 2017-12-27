package org.com.model;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Blog {

	@Id
	private int blogId;
	
	private String blogName,blogContent,status,Userid;
	
	private int likes;
	
	private Date createDate;

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getBlogName() {
		return blogName;
	}

	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}

	public String getBlogContent() {
		return blogContent;
	}

	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getUserid() {
		return Userid;
	}

	public void setUserid(String userid) {
		Userid = userid;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	


}
