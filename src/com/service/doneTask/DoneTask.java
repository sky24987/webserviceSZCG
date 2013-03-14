package com.service.doneTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.db.manger.DaoManager;

public class DoneTask {
	private DaoManager dm = new DaoManager();
	public boolean logined=false;
	
	public void login(String username,String password){
		if("市数字城管".equals(username)&&"sszcg2010".equals(password)){
			logined=true;
		}
	}
	
	public DoneTaskBean[] getDoneTasks()
	{
		if (logined)
		{
			dm.initConnection();
			Connection connection = dm.conn;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			DoneTaskBean tds[] = new DoneTaskBean[30];

			
			String sql = "SELECT * FROM TCDoneTask";
			try
			{
				stmt = connection.prepareStatement(sql);
				rs = stmt.executeQuery();
				int i = 0 ; 
				while (rs.next())
				{
					DoneTaskBean	td =new DoneTaskBean();
					td.setId(rs.getString("CaseID"));
					tds[i]=td;
				}
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
			finally
			{
				rs = null;
				stmt = null;
				connection = null;
				dm.closeConnection();
			}
			return tds;
			
		}
		
		return null;
	}
	
	/**
	 * 通过caseId 得到区数字城管的结案数据
	 * @param caseId
	 * @return
	 */
	
	
	public DoneTaskBean getDoneTask(String caseId){
		if(logined){
			ArrayList<DoneTaskBean> al = new ArrayList<DoneTaskBean>();
			dm.initConnection();
			Connection connection = dm.conn;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String cateId = null;
			DoneTaskBean td = null;
			Map mp = null;
			try
				{
					stmt = connection.prepareStatement("SELECT DISTINCT TOP 1 CateID FROM TDoneTask WHERE  ProInsActiveState = 'C' AND CaseID='" + caseId + "'");
					rs = stmt.executeQuery();
					if (rs.next())
					{
						cateId = rs.getString("CateID");
						System.out.println(cateId);
					}
				}
				catch (Exception exception)
				{
					exception.printStackTrace();
				}
				finally
				{
					rs = null;
					stmt = null;
				}
			

			String sql = "SELECT DISTINCT A.FSACTINSID AS FSACTINSID,A.FSCommentTypeID AS FSCommentTypeID,A.FSReplaceSigner AS FSReplaceSigner,T.FSCommentsAliasName AS FSCommentsAliasName,A.FSComments AS FSComments,A.FSSecretComments AS FSSecretComments,A.FDCommentTime AS FDCommentTime,A.FDCREATETIME As FDCREATETIME,A.FSHandlerID AS FSHandlerID, A.FSFlowKind AS FSFlowKind FROM TWFActivityInsBak A,TWFCommentsTypeAlias T WHERE A.FSCommentTypeID<>'' AND A.FSCateID='" + cateId + "' AND A.FSCaseID='" + caseId + "' AND A.FCActiveState<>'T' AND A.FSCommentTypeID=T.FSCommentsAliasID ORDER BY A.FDCommentTime,A.FDCREATETIME";
			try
			{
				stmt = connection.prepareStatement(sql);
				rs = stmt.executeQuery();
				td =new DoneTaskBean();
				while (rs.next())
				{
					
					mp=new HashMap();
					mp.put(rs.getString("FSCommentsAliasName") , rs.getString("FSComments"));
					
				}
				td.setId(caseId);
				
				td.setComment(mp);
				al.add(td);
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
			finally
			{
				rs = null;
				stmt = null;
				connection = null;
				dm.closeConnection();
			}
			return td;
		}
		else{
			
			return null;
		}
	}
	
	
	
}
