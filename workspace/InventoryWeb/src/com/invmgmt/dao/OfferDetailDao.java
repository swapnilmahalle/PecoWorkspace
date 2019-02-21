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

import com.invmgmt.entity.BOQDetails;
import com.invmgmt.entity.OfferDetails;
import com.invmgmt.entity.Project;

@Repository
public class OfferDetailDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public int addOfferDetails(OfferDetails off) {

		int offerid;

		Session session = sessionFactory.getCurrentSession();

		offerid = (int) session.save(off);

		return offerid;
	}

	@Transactional
	public OfferDetails getOfferDetails(int docNumber) {
		OfferDetails offer = new OfferDetails();
		Session session = sessionFactory.getCurrentSession();

		String hql = "FROM OfferDetails P WHERE P.docNumber = ";

		Query query = session.createQuery(hql + docNumber);
		List results = query.getResultList();

		Iterator itr = results.iterator();

		while (itr.hasNext()) {
			offer = (OfferDetails) itr.next();
		}

		return offer;
	}
	
	@Transactional
	public float getRates(String boqName) {
		ArrayList<BOQDetails> projectList = new ArrayList<>();
		float sum = 0;
		Session session = sessionFactory.getCurrentSession();

		String hql = "FROM BOQDetails P WHERE P.boqName LIKE '%";

		Query query = session.createQuery(hql + boqName + "%'");
		List results = query.getResultList();

		Iterator itr = results.iterator();

		while (itr.hasNext()) {
			BOQDetails boq =new BOQDetails();
			boq =(BOQDetails) itr.next();
			sum = sum +Float.parseFloat(boq.getErectionAmount()) + Float.parseFloat(boq.getSupplyAmount()); 
		//	projectList.add((BOQDetails) itr.next());
		}

		return sum;
	}
}
