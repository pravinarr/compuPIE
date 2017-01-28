package com.sw.compupie.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.sw.compupie.model.MetaInfo;

@Path("/metasInfo")
public class MetaInfoService {
	
	@GET
	@Produces("application/json")
	public Response getMetaInfo(){
		return Response.ok(new MetaInfo()).build();
	}

}
