package com.sw.compupie.service;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.sw.compupie.daoImpl.UserDao;
import com.sw.compupie.security.SecureCompuPie;

@Path("/security")
public class SecurityService {

	@GET
	@Produces("application/json")
	public Response login(@QueryParam("username") String username, @QueryParam("password") String password) {
		UserDao dao = new UserDao();
		SecureCompuPie secure = new SecureCompuPie();
		try {
			if (dao.ispresent(username, secure.encrypt(password))) {
				return Response.ok().build();
			} else {
				return Response.status(405).build();
			}

		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
	
	@PUT
	@Produces("application/json")
	public Response changeCredentials(@QueryParam("cusername") String cusername,
			@QueryParam("cpassword") String cpassword,
			@QueryParam("nusername") String nusername,
			@QueryParam("npassword") String npassword) {
		UserDao dao = new UserDao();
		SecureCompuPie secure = new SecureCompuPie();
		try {
			if (dao.ispresent(cusername, secure.encrypt(cpassword))) {
				dao.update(cusername, secure.encrypt(cpassword), nusername, secure.encrypt(npassword));
				return Response.ok().build();
			} else {
				return Response.status(405).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
