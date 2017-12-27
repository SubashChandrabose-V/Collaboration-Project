package org.com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.com.model.Blog;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("blogDAO")
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public BlogDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional
	@Override
	public boolean createBlog(Blog blog)
	{
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(blog);
			System.out.println("Inertion Successful");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised:"+e);
			return false;
		}
	}

	@Transactional
	@Override
	public boolean approveBlog(Blog blog)
	{
		try {
			blog.setStatus("A");
			sessionFactory.getCurrentSession().saveOrUpdate(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("exception arised:" +e);
			return false;
		}

	}

	@Transactional
	@Override
	public boolean deleteBlog(int blogId) {

		try {
			Session session=sessionFactory.openSession();
			Blog blog=(Blog)session.get(Blog.class,blogId);
			session.delete(blog);
			session.flush();
			session.close();
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception Arised" +e);
			return false;
		}
	}

	@Override
	public boolean editBlog(int blogId) {
		
		
		
		return false;
	}

	@Override
	public Blog getBlog(int blogId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public List<Blog> getBlogs() {
		
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Blog where status = 'A'");
		List<Blog> listBlog=query.list();
		session.close();
		return listBlog;

	}

}
