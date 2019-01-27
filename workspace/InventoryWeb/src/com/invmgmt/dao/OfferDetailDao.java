package com.invmgmt.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.invmgmt.entity.OfferDetailsForm;
import com.invmgmt.entity.Project;

@Repository
public class OfferDetailDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public int addOfferDetails(OfferDetailsForm off) {

		int offerid;

		Session session = sessionFactory.getCurrentSession();

		offerid = (int) session.save(off);

		return offerid;
	}

	@Transactional
	public OfferDetailsForm getOfferDetails(int offerId) {
		OfferDetailsForm offer = new OfferDetailsForm();
		Session session = sessionFactory.getCurrentSession();

		String hql = "FROM offerdetails P WHERE P.offerId = ";

		Query query = session.createQuery(hql + offerId);
		List results = query.getResultList();

		Iterator itr = results.iterator();

		while (itr.hasNext()) {
			offer = (OfferDetailsForm) itr.next();
		}

		return offer;
	}
	
/*	@Transactional
	public ArrayList<Project> getProject(String projectName) {
		ArrayList<Project> projectList = new ArrayList<Project>();
		
		Session session = sessionFactory.getCurrentSession();

		String hql = "FROM Project P WHERE P.projectName LIKE '%";

		Query query = session.createQuery(hql + projectName + "%'");
		List results = query.getResultList();

		Iterator itr = results.iterator();

		while (itr.hasNext()) {
			projectList.add((Project) itr.next());
		}

		return projectList;
	}*/
}
