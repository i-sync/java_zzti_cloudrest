package com.zzti.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.zzti.bean.Gallery;
import com.zzti.bean.ListResult;
import com.zzti.bean.Result;
import com.zzti.bean.TResult;
import com.zzti.utils.Common;

@Path("/gallery")
public class GalleryResource {
	
	@POST
	@Path("/add")
	@Produces({"application/json;charset=utf-8"})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public Result galleryAdd(@FormParam("data") String data)
	{
		return new com.zzti.dao.Gallery().add(Common.getT(data, Gallery.class));
	}
	
	@POST
	@Path("/update")
	@Produces({"application/json;charset=utf-8"})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public Result galleryUpdate(@FormParam("data") String data)
	{
		return new com.zzti.dao.Gallery().update(Common.getT(data, Gallery.class));
	}
	
	@POST
	@Path("/delete")
	@Produces({"application/json;charset=utf-8"})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public Result galleryDelete(@FormParam("data") String data)
	{
		return new com.zzti.dao.Gallery().delete(Common.getT(data, Gallery.class));
	}
	
	@GET
	@Path("/delete/{id}")
	@Produces({"application/json;charset=utf-8"})
	public Result galleryDelete(@PathParam("id") int id)
	{
		Gallery data = new Gallery();
		data.setId(id);
		return new com.zzti.dao.Gallery().delete(data);
	}
	
	@POST
	@Path("/model")
	@Produces({"application/json;charset=utf-8"})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public TResult<Gallery> galleryGetModel(@FormParam("data") String data)
	{
		return new com.zzti.dao.Gallery().getModel(Common.getT(data, Gallery.class));
	}
	
	@GET
	@Path("/model/{id}")
	@Produces({"application/json;charset=utf-8"})
	public TResult<Gallery> galleryGetModel(@PathParam("id") int id)
	{
		Gallery data = new Gallery();
		data.setId(id);
		return new com.zzti.dao.Gallery().getModel(data);
	}
	
	@POST
	@Path("/list")
	@Produces({"application/json;charset=utf-8"})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public ListResult<Gallery> galleryGetList(@FormParam("data") String data)
	{
		return new com.zzti.dao.Gallery().getList(Common.getT(data, Gallery.class));
	}
}
