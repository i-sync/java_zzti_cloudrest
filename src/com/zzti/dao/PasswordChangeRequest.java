package com.zzti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.zzti.bean.Result;
import com.zzti.bean.TResult;
import com.zzti.utils.Common;

public class PasswordChangeRequest {
	
	/**
	 * add a record
	 * @param data
	 * @return
	 */
	public Result add(com.zzti.bean.PasswordChangeRequest data)
	{
		Result result = new Result();
		try {
			String sql = "insert into PasswordChangeRequest(ID,AddTime,Email,`Status`) values(?,?,?,?);";
			Object[] objs = new Object[] { data.getId(), new Date(),data.getEmail(),1 };
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
	 * get a record invalid by id
	 * @param data
	 * @return
	 */
	public Result update(com.zzti.bean.PasswordChangeRequest data)
	{
		Result result = new Result();
		try {
			String sql = "update PasswordChangeRequest set `Status`=1 where `ID`=?;";
			Object[] obj = new Object[] {data.getId() };
			int res = DBHelper.executeNonQuery(sql, obj);
			result.setResult(res);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(0);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * get a model by id
	 * @param data
	 * @return
	 */
	public TResult<com.zzti.bean.PasswordChangeRequest> getModel(com.zzti.bean.PasswordChangeRequest data)
	{
		TResult<com.zzti.bean.PasswordChangeRequest> result = new TResult<com.zzti.bean.PasswordChangeRequest>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from PasswordChangeRequest where ID=? and `Status`=?;"; 
			Object[] obj = new Object[] { data.getId(),1 };

			conn = new ConnectionManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}
			rs = pstmt.executeQuery();
			// 判断是否有数据
			if (rs.next()) {
				rs.getTimestamp("AddTime");
				data.setEmail(rs.getString("Email"));
				data.setAddTime(Common.dateToString(rs.getTimestamp("AddTime").getTime()));
				data.setStatus(rs.getInt("Status"));
				result.setT(data);
				result.setResult(1);
			} else {
				result.setResult(-1);
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
			ConnectionManager.free(rs, pstmt, conn);
		}

		return result;
	}
}
