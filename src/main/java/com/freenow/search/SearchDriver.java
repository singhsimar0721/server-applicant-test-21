package com.freenow.search;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Repository;

import com.freenow.domainobject.DriverDO;

/**
 * Search methods for the entity User using Hibernate search.
 * The Transactional annotation ensure that transactions will be opened and
 * closed at the beginning and at the end of each method.
 * 
 * @author singh
 */
@Repository
@Transactional
public class SearchDriver {

  @PersistenceContext
  private EntityManager entityManager;
  
  /**
   * A basic search for the entity DriverDO. The search is done by exact match per
   * keywords on fields name, city and email.
   * 
   * @param text The query text.
   */
  public List<DriverDO> search(String text) {
    
    FullTextEntityManager fullTextEntityManager =Search.getFullTextEntityManager(entityManager);
    
    QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(DriverDO.class).get();
   
    Query query = queryBuilder.keyword().onFields("username", "onlineStatus","carDO.licensePlate","carDO.seatCount","carDO.rating").matching(text).createQuery();

    FullTextQuery jpaQuery =fullTextEntityManager.createFullTextQuery(query, User.class);
  
    @SuppressWarnings("unchecked")
    List<DriverDO> results = jpaQuery.getResultList();
    
    return results;
  } 


} 
