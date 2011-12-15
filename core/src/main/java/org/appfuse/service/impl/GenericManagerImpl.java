package org.appfuse.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.appfuse.service.GenericManager;
import org.compass.core.CompassHit;
import org.compass.core.support.search.CompassSearchCommand;
import org.compass.core.support.search.CompassSearchHelper;
import org.compass.core.support.search.CompassSearchResults;
import org.springframework.beans.factory.annotation.Autowired;

import com.mapflow.geo.common.persistence.GenericDao;
import com.mapflow.model.BaseObject;

/**
 * This class serves as the Base class for all other Managers - namely to hold common CRUD methods
 * that they might all use. You should only need to extend this class when your require custom CRUD
 * logic.
 * <p/>
 * <p>
 * To register this class in your Spring context file, use the following XML.
 * 
 * <pre>
 *     &lt;bean id="userManager" class="org.appfuse.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="org.appfuse.dao.hibernate.GenericDaoHibernate"&gt;
 *                 &lt;constructor-arg value="org.appfuse.model.User"/&gt;
 *                 &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 * <p/>
 * <p>
 * If you're using iBATIS instead of Hibernate, use:
 * 
 * <pre>
 *     &lt;bean id="userManager" class="org.appfuse.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="org.appfuse.dao.ibatis.GenericDaoiBatis"&gt;
 *                 &lt;constructor-arg value="org.appfuse.model.User"/&gt;
 *                 &lt;property name="dataSource" ref="dataSource"/&gt;
 *                 &lt;property name="sqlMapClient" ref="sqlMapClient"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 * 
 * @param <T>
 *          a type variable
 * @param <PK>
 *          the primary key for that type
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class GenericManagerImpl<T extends BaseObject, PK extends Serializable> implements
  GenericManager<T, PK> {

  /**
   * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
   */
  protected final Log log = LogFactory.getLog(getClass());

  /**
   * GenericDao instance, set by constructor of child classes
   */
  protected GenericDao<T, PK> dao;

  @Autowired
  private CompassSearchHelper compass;

  public GenericManagerImpl() {
  }

  public GenericManagerImpl(final GenericDao<T, PK> genericDao) {
    this.dao = genericDao;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean exists(final PK id) {
    return dao.exists(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public T get(final PK id) {
    return dao.get(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<T> getAll() {
    return dao.getAll();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remove(final PK id) {
    dao.remove(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public T save(final T object) {
    return dao.save(object);
  }

  /**
   * {@inheritDoc}
   * <p/>
   * Search implementation using Compass.
   */
  @Override
  @SuppressWarnings("unchecked")
  public List<T> search(final String q, final Class clazz) {
    if ((q == null) || "".equals(q.trim())) {
      return getAll();
    }

    final List<T> results = new ArrayList<T>();

    final CompassSearchCommand command = new CompassSearchCommand(q);
    final CompassSearchResults compassResults = compass.search(command);
    final CompassHit[] hits = compassResults.getHits();

    if (log.isDebugEnabled() && (clazz != null)) {
      log.debug("Filtering by type: " + clazz.getName());
    }

    for (final CompassHit hit : hits) {
      if (clazz != null) {
        if (hit.data().getClass().equals(clazz)) {
          results.add((T) hit.data());
        }
      }
      else {
        results.add((T) hit.data());
      }
    }

    if (log.isDebugEnabled()) {
      log.debug("Number of results for '" + q + "': " + results.size());
    }

    return results;
  }
}
