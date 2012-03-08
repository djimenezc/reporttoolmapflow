package com.mapflow.geo.stats.manager.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.mapflow.geo.common.manager.impl.GenericManagerImpl;
import com.mapflow.geo.stats.dao.MapdisplayLogDao;
import com.mapflow.geo.stats.manager.StatsManager;
import com.mapflow.geo.stats.model.Mapdisplay;
import com.mapflow.geo.stats.service.StatsService;

/**
 * Implementation of UserManager interface.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Service("statsManager")
@WebService(serviceName = "statsService", endpointInterface = "com.mapflow.geo.stats.service.StatsService")
public class StatsManagerImpl extends GenericManagerImpl<Mapdisplay, Long> implements StatsManager,
  StatsService {

  @Override
  public List<Mapdisplay> getLogEntries() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setMapdisplayLogDao(final MapdisplayLogDao mapdisplayLogDao) {
    // TODO Auto-generated method stub

  }

}
