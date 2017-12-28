package org.com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.com.model.Friend;
import org.com.model.User;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class FriendDAOImpl implements FriendDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> listOfSuggestedUsers(String username) {
		Session session=sessionFactory.getCurrentSession();
		SQLQuery query=session.createSQLQuery("select * from userdetails where username in"
				+ "(select username from userdetails where username!=? minus "
				+ "(select fromId from friend where toId=? "
				+ "union select toId from friend where fromId=?)"
				+ ")");
		query.setString(0, username);
		query.setString(1, username);
		query.setString(2, username);
		query.addEntity(User.class); //to convert records to User Objects
		List<User> suggestedUsers=query.list();
		return suggestedUsers; //List<User>
	}

	@Override
	public void friendRequest(Friend friend) {
		Session session=sessionFactory.getCurrentSession();
		session.save(friend);
		
	}

	@Override
	public List<Friend> pendingRequests(String toId) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Friend where toId=? and status='P'");
		query.setString(0, toId);
		return query.list();

	}

	@Override
	public void updatePendingRequest(Friend friend) {
		Session session=sessionFactory.getCurrentSession();
		if(friend.getStatus()=='A')
			session.update(friend); //update friend set status='a' where id=?
		else
			session.delete(friend); // delete from friend where id=?
		
	}

	@Override
	public List<String> listOfFriends(String username) {
		Session session=sessionFactory.getCurrentSession();
		SQLQuery sqlQuery1=session.createSQLQuery("select fromId from Friend where toId=? and status='A'").addScalar("fromId",StandardBasicTypes.STRING);
		
		sqlQuery1.setString(0, username);
		List<String> list1=sqlQuery1.list();
		System.out.println("Result of lst query" +list1);
		SQLQuery sqlQuery2=session.createSQLQuery("select toId from Friend where fromId=? and status='A'").addScalar("toId",StandardBasicTypes.STRING);
		sqlQuery2.setString(0, username);
		List<String> list2=sqlQuery2.list();
		System.out.println("Result of 2nd query" +list2);
		list1.addAll(list2);	//list1=list1 u list2
		System.out.println("Result of list1 + list2" +list1);
		return list1;
	}
	
	

}
