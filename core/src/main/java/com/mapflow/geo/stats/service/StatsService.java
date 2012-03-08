package com.mapflow.geo.stats.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.mapflow.geo.stats.model.Mapdisplay;

/**
 * Web Service interface so hierarchy of Generic Manager isn't carried through.
 */
@WebService
@Path("/")
@Produces({ "application/json", "application/xml" })
public interface StatsService {

  /**
   * Retrieves a list of all users.
   * 
   * @return List
   */
  @GET
  @Path("/entries")
  List<Mapdisplay> getLogEntries();

}
