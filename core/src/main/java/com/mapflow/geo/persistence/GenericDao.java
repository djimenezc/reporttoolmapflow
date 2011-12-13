package com.mapflow.geo.persistence;

import java.io.Serializable;

import com.mapflow.geo.model.BaseObject;

/**
 * Generic DAO Pattern
 * 
 * @author djimenez
 * @param <I>
 * @param <E>
 */
public interface GenericDao<E extends BaseObject, I extends Serializable> extends BasicDao<E, I> {

  /**
   * Method that delete a specific object in the persistent tier
   * 
   * @param entity
   */
  void remove(E entity);

  /**
   * Method that save a object information in the persistent tier
   * 
   * @param entity
   */
  // void save(E entity);

}
