package com.zzti.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.zzti.bean.PasswordChangeRequest;
import com.zzti.bean.Result;
import com.zzti.bean.TResult;
import com.zzti.utils.Common;

@Path("/passwordchangerequest")
public class PasswordChangeRequestResquest {

	@POST
	@Path("/add")
	@Produces({"application/json;charset=utf-8"})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public Result pcrAdd(@FormParam("data") String data)
	{
		return new com.zzti.dao.PasswordChangeRequest().add(Common.getT(data, PasswordChangeRequest.class));
	}
	
	@POST
	@Path("/update")
	@Produces({"application/json;charset=utf-8"})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public Result contactUpdate(@FormParam("data") String data)
	{
		return new com.zzti.dao.PasswordChangeRequest().update(Common.getT(data, PasswordChangeRequest.class));
	}
	
	@GET
	@Path("/model/{id}")
	@Produces({"application/json;charset=utf-8"})
	public TResult<PasswordChangeRequest> contactGetModel(@PathParam("id") String id)
	{
		PasswordChangeRequest data = new PasswordChangeRequest();
		data.setId(id);
		return new com.zzti.dao.PasswordChangeRequest().getModel(data);
	}
}
