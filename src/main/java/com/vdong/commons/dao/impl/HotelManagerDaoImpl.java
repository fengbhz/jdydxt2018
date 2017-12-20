package com.vdong.commons.dao.impl;

import com.mysql.jdbc.Statement;
import com.vdong.commons.dao.HotelManagerDao;
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
import java.text.SimpleDateFormat;
import java.util.*;

@Repository("hotelManagerDao")
public class HotelManagerDaoImpl implements HotelManagerDao {

	private final Logger logger = LoggerFactory.getLogger(HotelManagerDaoImpl.class);
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

	
	
	public int insertHotel(Map<String, String> map) {
		
		
		final String id = UUID.randomUUID().toString().substring(0, 32);
		final String hotelName = (map.get("hotelName")==null?"":map.get("hotelName"));
		final String hotelid = (map.get("hotelid")==null?"":map.get("hotelid"));
		final String order = (map.get("order")==null?"":map.get("order"));
		final String hotelSummary = (map.get("hotelSummary")==null?"":map.get("hotelSummary"));
		final String hotelPic = (map.get("hotelPic")==null?"":map.get("hotelPic"));
		final String hotelAccount = (map.get("hotelAccount")==null?"":map.get("hotelAccount"));
		final String hotelPass = (map.get("hotelPass")==null?"":map.get("hotelPass"));
		final String tel = (map.get("tel")==null?"":map.get("tel"));
		final String area = (map.get("area")==null?"":map.get("area"));
		final String address = (map.get("address")==null?"":map.get("address"));
		final String coordinate = (map.get("coordinate")==null?"":map.get("coordinate"));
		final String remark = (map.get("remark")==null?"":map.get("remark"));
		final String userId = (map.get("userId")==null?"":map.get("userId"));
		final	String  sql="insert into hotelsupplier"
				+"(id, hotelName, hotelSummary, hotelPic, hotelAccount, hotelPass, tel, area,address,coordinate,remark,`order`,submitDate,hotelKind,status,userId)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
		
		int a = 0;
		final   String time = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {

			a = this.getJdbc().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(
							sql,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, id);
					ps.setString(2, hotelName);
					ps.setString(3, hotelSummary);
					ps.setString(4, hotelPic);
					ps.setString(5, hotelAccount);
					ps.setString(6, hotelPass);
					ps.setString(7, tel);
					ps.setString(8, area);
					ps.setString(9, address);
					ps.setString(10, coordinate);
					ps.setString(11, remark);
					ps.setString(12, order);
					ps.setString(13, time);
					ps.setString(14, hotelid);
					ps.setString(15, "0");
					ps.setInt(16, Integer.valueOf(userId));
					return ps;
				}
			}, keyHolder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public List getRoomList(Map<String, String> map) {
		List parm = new ArrayList();
		List list = new ArrayList();
		String id=map.get("id");
		String sql = " select * from hotelroom where 1=1 ";
		// 后期需要什么条件再加
		if(StringUtils.isNotBlank(id)){
			sql+="  and id=?";
			 parm.add(id);
		}
		
		try {
			list = jdbc.queryForList(sql, parm.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
