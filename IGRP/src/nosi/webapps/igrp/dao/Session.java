package nosi.webapps.igrp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import nosi.core.dao.RowDataGateway;
import nosi.core.webapp.Igrp;
import nosi.core.webapp.helpers.Permission;

/**
 * Marcel Iekiny
 * May 29, 2017
 */

public class Session implements RowDataGateway{
	  
	private int id;
	private String sessionId;
	private long startTime;
	private long endTime;
	private int userId;
	private String ipAddress;
	private String userName;
	private int envId;
	private String target;
	private int orgId;
	private int profTypeId;
	private int https; // boolean 0 or 1
	private String sessionOldId;
	private String host;
	private String hostName;
	private String mediaType;
	
	private Connection conn;	
	
	public Session() {
		this.conn = Igrp.getInstance().getDao().unwrap("db1");
	}

	@Override
	public boolean insert() {
		String sql  = "insert into glb_t_session(SESSION_ID, USER_ID, IP_ADDRESS, USER_NAME, ENV_ID, TARGET,"
				+ " ORG_ID, PROF_TYPE_ID, HTTPS, SESSION_OLD_ID, HOST, HOST_NAME, MEDIA_TYPE, "
				+ " START_TIME, END_TIME) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int result = 0;
		try{
			conn.setAutoCommit(true);
			PreparedStatement ps = this.conn.prepareStatement(sql);
			ps.setString(1, this.sessionId);
			ps.setInt(2, this.userId);
			ps.setString(3, this.ipAddress);
			ps.setString(4, this.userName);
			ps.setInt(5, this.envId);
			ps.setString(6, this.target);
			ps.setInt(7, this.orgId);
			ps.setInt(8, this.profTypeId);
			ps.setInt(9, this.https);
			ps.setString(10, this.sessionOldId);
			ps.setString(11, this.host);
			ps.setString(12, this.hostName);
			ps.setString(13, this.mediaType);
			ps.setLong(14, this.startTime);
			ps.setLong(15, this.endTime);
			result = ps.executeUpdate();
			ps.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result > 0;
	}

	@Override
	public Object getOne() {
		Session obj = null;
		try{
			Statement st = this.conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM glb_t_session where id = "+ this.id);
			
			if(rs.next()){
				obj = new Session();
				obj.id = rs.getInt("id");
				obj.sessionId = rs.getString("SESSION_ID");
				obj.startTime = rs.getLong("START_TIME");
				obj.endTime = rs.getLong("END_TIME");
				obj.userId = rs.getInt("USER_ID");
				obj.ipAddress = rs.getString("IP_ADDRESS");
				obj.userName = rs.getString("USER_NAME");
				obj.envId = rs.getInt("ENV_ID");
				obj.target = rs.getString("TARGET");
				obj.orgId = rs.getInt("ORG_ID");
				obj.profTypeId = rs.getInt("PROF_TYPE_ID");
				obj.https = rs.getInt("HTTPS");
				obj.sessionOldId = rs.getString("SESSION_OLD_ID");
				obj.host = rs.getString("HOST");
				obj.hostName = rs.getString("HOST_NAME");
				obj.mediaType = rs.getString("MEDIA_TYPE");
			}
			st.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		return obj;
	}
	
	public Object getBySessionId() {
		Session obj = null;
		try{
			String sql = "SELECT * FROM glb_t_session where session_id = ?";
			PreparedStatement ps = this.conn.prepareStatement(sql);
			ps.setString(1, this.sessionId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				obj = new Session();
				obj.id = rs.getInt("id");
				obj.sessionId = rs.getString("SESSION_ID");
				obj.startTime = rs.getLong("START_TIME");
				obj.endTime = rs.getLong("END_TIME");
				obj.userId = rs.getInt("USER_ID");
				obj.ipAddress = rs.getString("IP_ADDRESS");
				obj.userName = rs.getString("USER_NAME");
				obj.envId = rs.getInt("ENV_ID");
				obj.target = rs.getString("TARGET");
				obj.orgId = rs.getInt("ORG_ID");
				obj.profTypeId = rs.getInt("PROF_TYPE_ID");
				obj.https = rs.getInt("HTTPS");
				obj.sessionOldId = rs.getString("SESSION_OLD_ID");
				obj.host = rs.getString("HOST");
				obj.hostName = rs.getString("HOST_NAME");
				obj.mediaType = rs.getString("MEDIA_TYPE");
			}
			
			ps.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		return obj;
	}

	@Override
	public boolean update(){ // Load the data first
		int result = 0;
		try{
			this.conn.setAutoCommit(true);
			PreparedStatement ps = this.conn.prepareStatement("update glb_t_session set "
					+ "SESSION_ID = ?, "
					+ "START_TIME = ?, "
					+ " END_TIME = ?,"
					+ " USER_ID = ?,"
					+ " IP_ADDRESS = ?,"
					+ " USER_NAME = ?,"
					+ " ENV_ID = ?,"
					+ " TARGET = ?,"
					+ " ORG_ID = ?,"
					+ " PROF_TYPE_ID = ?,"
					+ " HTTPS = ?,"
					+ " SESSION_OLD_ID = ?,"
					+ " HOST = ?,"
					+ " HOST_NAME = ?,"
					+ " MEDIA_TYPE = ? "
					+ " where id = ?");
			
			ps.setString(1, this.sessionId);
			ps.setLong(2, this.startTime);
			ps.setLong(3,this.endTime);
			ps.setInt(4, this.userId);
			ps.setString(5, this.ipAddress);
			ps.setString(6, this.userName);
			ps.setInt(7, this.envId);
			ps.setString(8, this.target);
			ps.setInt(9, this.orgId);
			ps.setInt(10, this.profTypeId);
			ps.setInt(11, this.https);
			ps.setString(12, this.sessionOldId);
			ps.setString(13, this.host);
			ps.setString(14, this.hostName);
			ps.setString(15, this.mediaType);
			ps.setInt(16, this.id);
			
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return result > 0;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getEnvId() {
		return envId;
	}

	public void setEnvId(int envId) {
		this.envId = envId;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public int getProfTypeId() {
		return profTypeId;
	}

	public void setProfTypeId(int profTypeId) {
		this.profTypeId = profTypeId;
	}

	public int getHttps() {
		return https;
	}

	public void setHttps(int https) {
		this.https = https;
	}

	public String getSessionOldId() {
		return sessionOldId;
	}

	public void setSessionOldId(String sessionOldId) {
		this.sessionOldId = sessionOldId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", sessionId=" + sessionId + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", userId=" + userId + ", ipAddress=" + ipAddress + ", userName=" + userName + ", envId=" + envId
				+ ", target=" + target + ", orgId=" + orgId + ", profTypeId=" + profTypeId + ", https=" + https
				+ ", sessionOldId=" + sessionOldId + ", host=" + host + ", hostName=" + hostName + ", mediaType="
				+ mediaType + ", conn=" + conn + "]";
	}
	
	public static boolean afterLogin(){
		Session currentSession = new Session();
		currentSession.setUserId(Igrp.getInstance().getUser().getIdentity().getIdentityId());
		User user = ((User)Igrp.getInstance().getUser().getIdentity());
		currentSession.setEnvId(user.getAplicacao().getId());
		currentSession.setOrgId(user.getOrganica().getId());
		currentSession.setProfTypeId(user.getProfile().getId());
		currentSession.setIpAddress(Igrp.getInstance().getRequest().getRemoteAddr());
		currentSession.setSessionId(Igrp.getInstance().getRequest().getRequestedSessionId());
		currentSession.setUserName(user.getUser_name());
		currentSession.setHttps( Igrp.getInstance().getRequest().isSecure() ? 1 : 0);
		currentSession.setHost(Igrp.getInstance().getRequest().getRemoteHost());
		currentSession.setHostName(Igrp.getInstance().getRequest().getRemoteHost());
		currentSession.setSessionOldId(Igrp.getInstance().getRequest().getRequestedSessionId());
		currentSession.setMediaType("WEB");
		currentSession.setTarget("_blank");
		long time = System.currentTimeMillis();
		currentSession.setStartTime(time);
		currentSession.setEndTime(time + 30*60); // add 30 min.
		
		return currentSession.insert();
		
	}
	
	public static boolean afterLogout(String sessionId){
		Session session = new Session();
		session.setSessionId(sessionId);
		session = (Session) session.getBySessionId();
		session.setEndTime(System.currentTimeMillis());
		
		return session.update();
	}
	
}
