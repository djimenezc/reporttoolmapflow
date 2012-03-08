package com.mapflow.geo.stats.model;

import com.mapflow.geo.stats.model.entities.MapdisplayLogEntity;
import com.mapflow.model.BaseObject;

public class Mapdisplay extends BaseObject {

  private static final long serialVersionUID = 4201390790656264138L;
  private MapdisplayLogEntity entity;

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Mapdisplay other = (Mapdisplay) obj;
    if (entity == null) {
      if (other.entity != null) {
        return false;
      }
    }
    else if (!entity.equals(other.entity)) {
      return false;
    }
    return true;
  }

  public MapdisplayLogEntity getEntity() {
    return entity;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((entity == null) ? 0 : entity.hashCode());
    return result;
  }

  public void setEntity(final MapdisplayLogEntity entity) {
    this.entity = entity;
  }

  @Override
  public String toString() {
    return "Mapdisplay [entity=" + entity + "]";
  }

}
