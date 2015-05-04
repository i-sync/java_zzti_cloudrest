package com.zzti.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.zzti.bean.ListResult;
import com.zzti.bean.Result;
import com.zzti.bean.TResult;

@Path("/contact")
public class ContactResource {
	@GET
	public boolean contactExists(com.zzti.bean.Contact data)
	{
		return new com.zzti.dao.Contact().exists(data);
	}
	
	@GET
	@Path("/{name}")
	public boolean contactExists(@PathParam("name") String name)
	{
		System.out.println(name);
		com.zzti.bean.Contact data = new com.zzti.bean.Contact();
		data.setName(name);
		return new com.zzti.dao.Contact().exists(data);
	}
	
	@POST
	@Path("/add")
	@Produces({"application/json;charset=utf-8"})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public Result contactAdd(@FormParam("data") com.zzti.bean.Contact data)
	{
		return new com.zzti.dao.Contact().add(data);
	}
	
	@POST
	@Path("/update")
	@Produces({"application/json;charset=utf-8"})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public Result contactUpdate(@FormParam("data") com.zzti.bean.Contact data)
	{
		return new com.zzti.dao.Contact().update(data);
	}
	
	@POST
	@Path("/delete")
	@Produces({"application/json;charset=utf-8"})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public Result contactDelete(@FormParam("data") com.zzti.bean.Contact data)
	{
		return new com.zzti.dao.Contact().delete(data);
	}
	
	@GET
	@Path("/delete/{id}")
	@Produces({"application/json;charset=utf-8"})
	public Result contactDelete(@PathParam("id") int id)
	{
		com.zzti.bean.Contact data = new com.zzti.bean.Contact();
		data.setId(id);
		return new com.zzti.dao.Contact().delete(data);
	}
	
	@POST
	@Path("/model")
	@Produces({"application/json;charset=utf-8"})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public TResult<com.zzti.bean.Contact> contactGetModel(@FormParam("data") com.zzti.bean.Contact data)
	{
		return new com.zzti.dao.Contact().getModel(data);
	}
	
	@GET
	@Path("/model/{id}")
	@Produces({"application/json;charset=utf-8"})
	public TResult<com.zzti.bean.Contact> contactGetModel(@PathParam("id") int id)
	{
		com.zzti.bean.Contact data = new com.zzti.bean.Contact();
		data.setId(id);
		return new com.zzti.dao.Contact().getModel(data);
	}
	
	@POST
	@Path("/list")
	@Produces({"application/json;charset=utf-8"})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public ListResult<com.zzti.bean.Contact> contact_getList(@FormParam("data") com.zzti.bean.Contact data)
	{
		return new com.zzti.dao.Contact().getList(data);
	}
}
