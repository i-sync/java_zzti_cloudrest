package com.zzti.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.zzti.bean.Contact;
import com.zzti.bean.ListResult;
import com.zzti.bean.Result;
import com.zzti.bean.TResult;
import com.zzti.utils.Common;

@Path("/contact")
public class ContactResource {
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public boolean contactExists(@FormParam("data") String data)
	{
		return new com.zzti.dao.Contact().exists(Common.getT(data, Contact.class));
	}
	
	@GET
	@Path("/{name}")
	public boolean contactExistsByName(@PathParam("name") String name)
	{
		System.out.println(name);
		Contact data = new Contact();
		data.setName(name);
		return new com.zzti.dao.Contact().exists(data);
	}
	
	@POST
	@Path("/add")
	@Produces({"application/json;charset=utf-8"})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public Result contactAdd(@FormParam("data") String data)
	{
		return new com.zzti.dao.Contact().add(Common.getT(data, Contact.class));
	}
	
	@POST
	@Path("/update")
	@Produces({"application/json;charset=utf-8"})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public Result contactUpdate(@FormParam("data") String data)
	{
		return new com.zzti.dao.Contact().update(Common.getT(data, Contact.class));
	}
	
	@POST
	@Path("/delete")
	@Produces({"application/json;charset=utf-8"})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public Result contactDelete(@FormParam("data") String data)
	{
		return new com.zzti.dao.Contact().delete(Common.getT(data, Contact.class));
	}
	
	@GET
	@Path("/delete/{id}")
	@Produces({"application/json;charset=utf-8"})
	public Result contactDelete(@PathParam("id") int id)
	{
		Contact data = new Contact();
		data.setId(id);
		return new com.zzti.dao.Contact().delete(data);
	}
	
	@POST
	@Path("/model")
	@Produces({"application/json;charset=utf-8"})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public TResult<Contact> contactGetModel(@FormParam("data") String data)
	{
		return new com.zzti.dao.Contact().getModel(Common.getT(data, Contact.class));
	}
	
	@GET
	@Path("/model/{id}")
	@Produces({"application/json;charset=utf-8"})
	public TResult<Contact> contactGetModel(@PathParam("id") int id)
	{
		Contact data = new Contact();
		data.setId(id);
		return new com.zzti.dao.Contact().getModel(data);
	}
	
	@POST
	@Path("/list")
	@Produces({"application/json;charset=utf-8"})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public ListResult<Contact> contact_getList(@FormParam("data") String data)
	{
		return new com.zzti.dao.Contact().getList(Common.getT(data, Contact.class));
	}
}
