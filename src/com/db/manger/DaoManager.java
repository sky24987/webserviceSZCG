package com.db.manger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.service.doneTask.DoneTask;
import com.service.doneTask.DoneTaskBean;

public class DaoManager {
	private String sql;
	public Connection conn = null;
	
	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	public void initConnection(){//��ʼ�����ݿ�����
		try{
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost/chinaweal;instance=sqlexpress","sa","123456");
			
		}
		catch(Exception ex){ex.printStackTrace();}
	}
	
	public void closeConnection(){
		try{
			if(conn!=null){
				conn.close();
				conn = null;
			}
		}catch(Exception ex){ex.printStackTrace();}
	}
	public static void main(String a[]) throws SQLException{
		DaoManager  dm  = new DaoManager();
		dm.initConnection();
		Statement st=dm.conn.createStatement();
		ResultSet rs=st.executeQuery("select * from tb_SMS");
		while(rs.next()){
			System.out.println(rs.getString(4));
		}
		dm.closeConnection();
		
		DoneTask dt = new DoneTask();
		DoneTaskBean dtb=dt.getDoneTask("04da34c1-012f-1000-e000-0045c0a84101");
		HashMap plmm = (HashMap) dtb.getComment();
		Set s = plmm.keySet();
		for(Iterator<String> ls=s.iterator();ls.hasNext();){
			String ss = ls.next();
			System.out.println(ss);
		}
		
	}
}
