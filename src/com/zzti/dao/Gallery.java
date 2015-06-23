package com.zzti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zzti.bean.ListResult;
import com.zzti.bean.Result;
import com.zzti.bean.TResult;

public class Gallery {
	
	/**
	 * add
	 * @param data
	 * @return
	 */
	public Result add(com.zzti.bean.Gallery data)
	{
		Result result = new Result();
		try {
			String sql = "insert into Gallery(CID,CName,Title,Caption,Content,Picture,`AddDate`,`UpdateDate`) values(?,?,?,?,?,?,?,?);";
			Object[] objs = new Object[] { data.getCid(),data.getCname(),data.getTitle(),data.getCaption(),data.getContent(),data.getPicture(), new Date(),new Date() };
			int res = DBHelper.executeNonQuery(sql, objs);
			result.setResult(res);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(0);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	/**
	 * update
	 * @param data
	 * @return
	 */
	public Result update(com.zzti.bean.Gallery data)
	{
		Result result = new Result();
		try {
			String sql = "update Gallery set CID=?,CName=?,Title=?,Caption=?,Content=?,Picture=?,`UpdateDate`=? where ID=?;";
			Object[] objs = new Object[] { data.getCid(),data.getCname(),data.getTitle(),data.getCaption(),data.getContent(),data.getPicture(),new Date(),data.getId() };
			int res = DBHelper.executeNonQuery(sql, objs);
			result.setResult(res);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(0);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * delete
	 * @param data
	 * @return
	 */
	public Result delete(com.zzti.bean.Gallery data) {
		Result result = new Result();
		try {
			String sql = "delete from Gallery where ID=?";
			Object[] objs = new Object[] { data.getId() };
			int res = DBHelper.executeNonQuery(sql, objs);
			result.setResult(res);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(0);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * get model
	 * @param data
	 * @return
	 */
	public TResult<com.zzti.bean.Gallery> getModel(com.zzti.bean.Gallery data) {
		TResult<com.zzti.bean.Gallery> result = new TResult<com.zzti.bean.Gallery>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql =  "select * from Gallery where ID=?";
			Object[] obj = new Object[] { data.getId() };

			conn = PoolManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}
			rs = pstmt.executeQuery();
			// check if has data
			if (rs.next()) {
				//data.setName(rs.getString("Name"));
				data.setCid(rs.getInt("CID"));
				data.setCname(rs.getString("CName"));
				data.setTitle(rs.getString("Title"));
				data.setCaption(rs.getString("Caption"));
				data.setContent(rs.getString("Content"));
				data.setAddDate(new Date(rs.getTimestamp("AddDate").getTime()));
				data.setUpdateDate(new Date(rs.getTimestamp("UpdateDate").getTime()));
				result.setT(data);
				result.setResult(1);
			} else {
				result.setResult(0);
				result.setMessage("没有找到数据！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result.setResult(0);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(0);
			result.setMessage(e.getMessage());
		} finally {
			PoolManager.free(rs, pstmt, conn);
		}

		return result;
	}
	
	/**
	 * get paging list
	 * @param data
	 * @return
	 */
	public ListResult<com.zzti.bean.Gallery> getList(com.zzti.bean.Gallery data) {
		ListResult<com.zzti.bean.Gallery> result = new ListResult<com.zzti.bean.Gallery>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String condition = " where 1=1 ";
			String limit = "";
			if (data.getPage() != null) {
				int start = (data.getPage().getPageIndex() - 1)
						* data.getPage().getPageSize();
				int num = data.getPage().getPageSize();
				limit = " limit " + start + "," + num;
			}
			
			if (data.getCid() > 0) {
				condition += " and cid=" + data.getCid();
			}

			String sql = "select * from Gallery "+ condition+ limit;
			//System.out.println(sql);
			conn = PoolManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<com.zzti.bean.Gallery> list = new ArrayList<com.zzti.bean.Gallery>();
			// com.zzti.bean.Gallery data = null;
			while (rs.next()) {
				int id = rs.getInt("ID");
				int cid = rs.getInt("CID");
				String cname = rs.getString("CName");
				String title = rs.getString("Title");
				String caption = rs.getString("Caption");
				String content = rs.getString("Content");
				String picture = rs.getString("Picture");
				Date addDate = rs.getDate("AddDate");
				Date updateDate = rs.getDate("UpdateDate");
				data = new com.zzti.bean.Gallery(id,  cid, cname, title,
						caption, content, picture, addDate, updateDate);
				list.add(data);
			}
			rs.close();

			// select total count
			sql = "select * from Gallery "+ condition;
			rs = stmt.executeQuery(sql);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("count");
			}
			// set total count
			result.setObj(count);
			result.setResult(1);// success
			result.setList(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setResult(0);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(0);
			result.setMessage(e.getMessage());
		} finally {
			PoolManager.free(rs, stmt, conn);
		}
		return result;
	}
}
