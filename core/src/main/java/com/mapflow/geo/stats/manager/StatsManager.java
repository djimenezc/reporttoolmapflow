package com.mapflow.geo.stats.manager;

import java.util.List;

import com.mapflow.geo.common.manager.GenericManager;
import com.mapflow.geo.common.model.entities.User;
import com.mapflow.geo.stats.dao.MapdisplayLogDao;
import com.mapflow.geo.stats.model.Mapdisplay;

/**
 * Business Service Interface to handle communication between web and persistence layer.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by <a
 *         href="mailto:dan@getrolling.com">Dan Kibler </a>
 */
public interface StatsManager extends GenericManager<Mapdisplay, Long> {

  /**
   * Retrieves a list of all entries.
   * 
   * @return List
   */
  List<User> getLogEntries();

  /**
   * Convenience method for testing - allows you to mock the DAO and set it on an interface.
   * 
   * @param mapdisplayLogDao
   *          the UserDao implementation to use
   */
  void setMapdisplayLogDao(MapdisplayLogDao mapdisplayLogDao);

}
