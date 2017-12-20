package com.vdong.commons.dao.impl;

import com.mysql.jdbc.Statement;
import com.vdong.commons.dao.HotelMsgDao;
import com.vdong.commons.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Repository("hotelMsgDao")
public class HotelMsgDaoImpl implements HotelMsgDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private JdbcTemplate jdbc;

	public void setDataSource(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}

	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public JdbcTemplate getJdbc() {
		return this.jdbc;
	}

	public int insertMsg(Map<String, String> map) {
		final String title = (map.get("title") == null ? "" : map.get("title"));
		// final String date = (map.get("date")==null?"":map.get("date"));
		final String info = (map.get("info") == null ? "" : map.get("info"));
		final String appuser = (map.get("appuser") == null ? "" : map
				.get("appuser"));
		final String picname = (map.get("picname") == null ? "" : map
				.get("picname"));
		final String type = (map.get("type") == null ? "" : map.get("type"));
		final String readflag = (map.get("readflag") == null ? "" : map
				.get("readflag"));
		final String sql = " insert into  news (title,date,info,appuser,pic,type,readflag)  values (?,?,?,?,?,?,?)";
		int a = 0;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
			a = this.getJdbc().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, title);
					ps.setString(2, DateUtil.dateFormat(new Date()));
					ps.setString(3, info);
					ps.setString(4, appuser);
					ps.setString(5, picname);
					ps.setString(6, type);
					ps.setString(7, readflag);
					return ps;
				}
			}, keyHolder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	public int updateStatus(String id, String readStatus) {
		String sql = " update news set readflag=? where  id=? ";
		int a = 0;
		try {
			a = jdbc.update(sql, new Object[] { readStatus, id });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	public List getMsg(String type, String str) {
		String sql = " select * from  news where  type=?  ";
		List list = new ArrayList();
		try {
			list = jdbc.queryForList(sql, new Object[] { type });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int updateMsg(Map<String, String> map) {
		List parm = new ArrayList();
		StringBuffer sb = new StringBuffer();
		int a = 0;
		sb.append("");
		String title = (map.get("title") == null ? "" : map.get("title"));
		String id = (map.get("id") == null ? "" : map.get("id"));
		String info = (map.get("info") == null ? "" : map.get("info"));
		String appuser = (map.get("appuser") == null ? "" : map.get("appuser"));
		String picname = (map.get("picname") == null ? "" : map.get("picname"));
		String type = (map.get("type") == null ? "" : map.get("type"));
		String readflag = (map.get("readflag") == null ? "" : map
				.get("readflag"));
		String sql = " update  news set id=?";
		parm.add(id);

		if (StringUtils.isNotBlank(id)) {
			sb.append("  id=? ,");
			parm.add(id);
		}
		if (StringUtils.isNotBlank(title)) {
			sb.append("  title=? ,");
			parm.add(title);
		}
		if (StringUtils.isNotBlank(info)) {
			sb.append("  info=? ,");
			parm.add(info);
		}
		if (StringUtils.isNotBlank(appuser)) {
			sb.append("  appuser=? ,");
			parm.add(appuser);
		}
		if (StringUtils.isNotBlank(picname)) {
			sb.append("  picname=? ,");
			parm.add(picname);
		}
		if (StringUtils.isNotBlank(type)) {
			sb.append("  type=? ,");
			parm.add(type);
		}

		if (StringUtils.isNotBlank(readflag)) {
			sb.append("  readflag=? ,");
			parm.add(readflag);
		}

		try {
			a = jdbc.update(sql + sb.toString(), parm.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	public int insertAd(Map<String, String> map) {
		final String adKind = (map.get("adKind") == null ? "" : map
				.get("adKind"));
		final String adTitle = (map.get("adTitle") == null ? "" : map
				.get("adTitle"));
		final String adPic = (map.get("adPic") == null ? "" : map.get("adPic"));
		final String adPic2 = (map.get("adPic2") == null ? "" : map.get("adPic2"));
		final String adPic3 = (map.get("adPic3") == null ? "" : map
				.get("adPic3"));
		final String adPic4 = (map.get("adPic4") == null ? "" : map
				.get("adPic4"));
		final String adPic5 = (map.get("adPic5") == null ? "" : map
				.get("adPic5"));
		final String adContent = (map.get("adContent") == null ? "" : map
				.get("adContent"));
		final String adStatus = (map.get("adStatus") == null ? "" : map
				.get("adStatus"));
		final String sql = " insert into  hotelad (adKind,adTitle,adPic,adContent,adSubmitDate,adStatus,adPic2,adPic3,adPic4,adPic5)  values (?,?,?,?,?,?,?,?,?,?)";
		int a = 0;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
			a = this.getJdbc().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, adKind);
					ps.setString(2, adTitle);
					ps.setString(3, adPic);
					ps.setString(4, adContent);
					ps.setString(5, DateUtil.dateFormat(new Date()));
					ps.setString(6, adStatus);
					ps.setString(7, adPic2);
					ps.setString(8, adPic3);
					ps.setString(9, adPic4);
					ps.setString(10, adPic5);
					return ps;
				}
			}, keyHolder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	public int updateAd(Map<String, String> map) {
		List parm = new ArrayList();
		StringBuffer sb = new StringBuffer();
		int a = 0;
		sb.append("");
		String id = (map.get("id") == null ? "" : map.get("id"));
		String adKind = (map.get("adKind") == null ? "" : map.get("adKind"));
		String adTitle = (map.get("adTitle") == null ? "" : map.get("adTitle"));
		String adPic = (map.get("adPic") == null ? "" : map.get("adPic"));
		String adPic2 = (map.get("adPic2") == null ? "" : map.get("adPic2"));
		String adPic3 = (map.get("adPic3") == null ? "" : map.get("adPic3"));
		String adPic4 = (map.get("adPic4") == null ? "" : map.get("adPic4"));
		String adPic5 = (map.get("adPic5") == null ? "" : map.get("adPic5"));
		
		String adContent = (map.get("adPic") == null ? "" : map.get("adPic"));
		String adStatus = (map.get("adStatus") == null ? "" : map
				.get("adStatus"));
		String sql = " update  hotelad  set ";
		

		if (StringUtils.isNotBlank(adKind)) {
			sb.append("  adKind=? ,");
			parm.add(adKind);
		}
		if (StringUtils.isNotBlank(adTitle)) {
			sb.append("  adTitle=? ,");
			parm.add(adTitle);
		}
		
		if (StringUtils.isNotBlank(adPic)) {
			sb.append("  adPic=? ,");
			parm.add(adPic);
		}
		
		if (StringUtils.isNotBlank(adPic2)) {
			sb.append("  adPic2=? ,");
			parm.add(adPic2);
		}
		
		if (StringUtils.isNotBlank(adPic3)) {
			sb.append("  adPic3=? ,");
			parm.add(adPic3);
		}
		if (StringUtils.isNotBlank(adPic4)) {
			sb.append("  adPic4=? ,");
			parm.add(adPic4);
		}
		if (StringUtils.isNotBlank(adPic5)) {
			sb.append("  adPic5=? ,");
			parm.add(adPic5);
		}
		if (StringUtils.isNotBlank(adContent)) {
			sb.append("  adContent=? ,");
			parm.add(adContent);
		}
		if (StringUtils.isNotBlank(adStatus)) {
			sb.append("  adStatus=? ,");
			parm.add(adStatus);
		}

		if (StringUtils.isNotBlank(id)) {
			sb.append("  id=? ");
			parm.add(id);
		}
		sb.append("  where  id=? ");
		parm.add(id);
		
		try {
			a = jdbc.update(sql + sb.toString(), parm.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
}
