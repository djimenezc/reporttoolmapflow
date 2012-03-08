package com.mapflow.geo.stats.dao.impl;

import org.springframework.stereotype.Repository;

import com.mapflow.geo.common.persistence.hibernate.GenericDaoHibernate;
import com.mapflow.geo.stats.dao.MapdisplayLogDao;
import com.mapflow.geo.stats.model.entities.MapdisplayLogEntity;

/**
 * DAO to manage the CRUD operation with Log entries
 * 
 * @author djimenez
 */
@Repository("mapdisplayDao")
public class MapdisplayHibernateDao extends GenericDaoHibernate<MapdisplayLogEntity, String> implements
  MapdisplayLogDao {

  public MapdisplayHibernateDao() {
    super(MapdisplayLogEntity.class);
  }

  @Override
  public MapdisplayLogEntity findFirst() {
    // TODO Auto-generated method stub
    return null;
  }

}
