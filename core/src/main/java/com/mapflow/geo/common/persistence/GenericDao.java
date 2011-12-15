package com.mapflow.geo.common.persistence;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Generic DAO Pattern
 * 
 * @author djimenez
 * @param <I>
 * @param <E>
 */
public interface GenericDao<E extends com.mapflow.model.BaseObject, PK extends Serializable>
  extends BasicDao<E, PK> {

  /**
   * Checks for existence of an object of type E using the id arg.
   * 
   * @param id
   *          the id of the entity
   * @return - true if it exists, false if it doesn't
   */
  boolean exists(PK id);

  /**
   * Method that save a object information in the persistent tier
   * 
   * @param entity
   */
  // void save(E entity);

  /**
   * Find a list of records by using a named query
   * 
   * @param queryName
   *          query name of the named query
   * @param queryParams
   *          a map of the query names and the values
   * @return a list of the records found
   */
  List<E> findByNamedQuery(String queryName, Map<String, Object> queryParams);

  /**
   * Generic method to get an object based on class and identifier. An
   * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is found.
   * 
   * @param id
   *          the identifier (primary key) of the object to get
   * @return a populated object
   * @see org.springframework.orm.ObjectRetrievalFailureException
   */
  E get(PK id);

  /**
   * Generic method used to get all objects of a particular type. This is the same as lookup up all
   * rows in a table.
   * 
   * @return List of populated objects
   */
  List<E> getAll();

  /**
   * Gets all records without duplicates.
   * <p>
   * Note that if you use this method, it is imperative that your model classes correctly implement
   * the hashcode/equals methods
   * </p>
   * 
   * @return List of populated objects
   */
  List<E> getAllDistinct();

  /**
   * Method that delete a specific object in the persistent tier
   * 
   * @param entity
   */
  void remove(E entity);

  /**
   * Generic method to delete an object based on class and id
   * 
   * @param id
   *          the identifier (primary key) of the object to remove
   */
  void remove(PK id);

  /**
   * Generic method to save an object - handles both update and insert.
   * 
   * @param object
   *          the object to save
   * @return the persisted object
   */
  E save(E object);

}
