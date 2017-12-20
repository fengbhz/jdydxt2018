package com.vdong.commons.dao.impl;

import com.mysql.jdbc.Statement;
import com.vdong.commons.dao.UserManagerDao;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository("userManagerDao")
public class UserManagerDaoImpl implements UserManagerDao {


	private final Logger logger = LoggerFactory.getLogger(UserManagerDaoImpl.class);
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

	public int deletedUser(String id) {
		String sql = "delete from hoteluser where id=?";
		int c = 0;
		try {
			c = jdbc.update(sql, new Object[] { id });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	public int insertUser(Map<String, String> map) {
		final	String  sql="insert admin (username,userpw,zhi,tel,age,type,hotelId,income,roleId)  values(?,?,?,?,?,?,?,?,?)";
		int a = 0;
		final   String time = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
		final	String username = map.get("hotelAccount");// 所属酒店
		final   String passwd = (map.get("hotelPass")==null?"":map.get("hotelPass"));
		final   String zhi = (map.get("hotelName")==null?"":map.get("hotelName"));
		final   String tel = (map.get("tel")==null?"":map.get("tel"));
		final   String age = (map.get("age")==null?"":map.get("age"));
		final   String type = (map.get("type")==null?"":map.get("type"));
		final   String hotelId = (map.get("hotelId")==null?"":map.get("hotelId"));
		final   String income = (map.get("income")==null?"":map.get("income"));
		//final   String roleId = (map.get("roleId")==null?"":map.get("roleId"));
		final   String roleId="2";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {

			a = this.getJdbc().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(
							sql,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, username);
					ps.setString(2, passwd);
					ps.setString(3, zhi);
					ps.setString(4, tel);
					ps.setString(5, age);
					ps.setString(6, type);
					ps.setString(7, hotelId);
					ps.setString(8, income);
					ps.setInt(9, Integer.valueOf(roleId));
					return ps;
				}
			}, keyHolder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(a>0){
			return keyHolder.getKey().intValue();	
		}else{
			return 0;
		}
	}

	public String checkUser(String account, String phone) {
		String count="";
		String  sql=" select * from hotelowner  where  account=? and mobile=?";
		try {
			List  list=jdbc.queryForList(sql, new Object[]{account,phone});
			if(null!=list&&list.size()>0){
				count=((Map<String,String>)list.get(0)).get("id");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List getOwer(String id) {
		int count=0;
		List  list=new ArrayList();
		String sql=  " select * from hotelOwer  where id=  ?";
		try {
			  list=jdbc.queryForList(sql, new Object[]{id});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateOwerStatus(String id,String status) {
		String sql=" update hotelowner set applyStatus = ? where id = ? ";
		int count = 0;
		try {
			count = jdbc.update(sql, new Object[] { status,id });
		} catch (Exception e) {
			logger.info("uodateOwerStatus---->" + e.getMessage());
			e.printStackTrace();
		}
		return  count;

	}

	@Override
	public int insertUserforOwer(Map<String, String> map) {
		final	String  sql="insert into hoteluser(id, account, name, mobile, idCard, userKind, userStutus, virtualCoin, lockVirtualCoin, integral, wechat, belongHotelId, belongHotel, role, nickName, gender, language, city, province, country, avatarUrl, code)" +
				" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     		int a = 0;
		final   String time = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
		final	String id = map.get("id");// 所属酒店
		final	String account = map.get("mobile");// 所属酒店
		final   String mobile = (map.get("mobile")==null?"":map.get("mobile"));
		final   String idCard = (map.get("idCard")==null?"":map.get("idCard"));
		final   String userKind = (map.get("userKind")==null?"":map.get("userKind"));
		final   String userStutus = (map.get("userStutus")==null?"":map.get("userStutus"));
		final   String lockVirtualCoin = (map.get("lockVirtualCoin")==null?"":map.get("lockVirtualCoin"));
		final   String virtualCoin = (map.get("virtualCoin")==null?"":map.get("virtualCoin"));
		final   String integral = (map.get("integral")==null?"":map.get("integral"));
		final   String name = (map.get("ownerName")==null?"":map.get("ownerName"));


		final   String wechat = (map.get("wechat")==null?"":map.get("wechat"));
		final   String belongHotelId = (map.get("belongHotelId")==null?"":map.get("belongHotelId"));
		final   String belongHotel = (map.get("belongHotel")==null?"":map.get("belongHotel"));
		final   String role = (map.get("role")==null?"":map.get("role"));
		final   String nickName = (map.get("nickName")==null?"":map.get("nickName"));
		final   String gender = (map.get("gender")==null?"":map.get("gender"));
		final   String language = (map.get("language")==null?"":map.get("language"));
		final   String city = (map.get("city")==null?"":map.get("city"));
		final   String province = (map.get("province")==null?"":map.get("province"));
		final   String country = (map.get("country")==null?"":map.get("country"));
		final   String avatarUrl = (map.get("avatarUrl")==null?"":map.get("avatarUrl"));
		final   String code = (map.get("code")==null?"":map.get("code"));
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {

			a = this.getJdbc().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(
							sql,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, id);
					ps.setString(2, account);
					ps.setString(3, name);
					ps.setString(4, mobile);
					ps.setString(5, idCard);
					ps.setString(6, "0");
					ps.setString(7, "0");
					ps.setString(8, "0");
					ps.setString(9, "0");
					ps.setString(10, "0");
					/******************调用微信接口获得***********************/
					/*	wechat, belongHotelId, belongHotel, role, nickName, gender, language, city, province, country, avatarUrl, code*/
					ps.setString(11, wechat);
					ps.setString(12, belongHotelId);
					ps.setString(13, belongHotel);
					ps.setString(14, role);
					ps.setString(15, nickName);
					ps.setString(16, gender);
					ps.setString(17, language);
					ps.setString(18, city);
					ps.setString(19, province);
					ps.setString(20, country);
					ps.setString(21, avatarUrl);
					ps.setString(22, code);
					return ps;
				}
			}, keyHolder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(a>0){
			return 1;
		}else{
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public int updateUserforOwer(Map<String, String> map) {
		List parm =new ArrayList();
		int count=0;
		  String  updateSql=" update  hoteluser set      ";
		   String id=(map.get("uid")==null?"":map.get("uid"));
		   String account=(map.get("account")==null?"":map.get("account"));
		   String name=(map.get("name")==null?"":map.get("name"));
		   String userKind=(map.get("userKind")==null?"":map.get("userKind"));
		   String wechat = (map.get("wechat")==null?"":map.get("wechat"));
		   String belongHotelId = (map.get("belongHotelId")==null?"":map.get("belongHotelId"));
		   String belongHotel = (map.get("belongHotel")==null?"":map.get("belongHotel"));
		   String role = (map.get("role")==null?"":map.get("role"));
		   String nickName = (map.get("nickName")==null?"":map.get("nickName"));
		   String gender = (map.get("gender")==null?"":map.get("gender"));
		   String language = (map.get("language")==null?"":map.get("language"));
		   String city = (map.get("city")==null?"":map.get("city"));
		   String province = (map.get("province")==null?"":map.get("province"));
		   String country = (map.get("country")==null?"":map.get("country"));
		   String avatarUrl = (map.get("avatarUrl")==null?"":map.get("avatarUrl"));
		   String code = (map.get("code")==null?"":map.get("code"));

		if (StringUtils.isNotBlank(wechat)) {
			updateSql += "  wechat=? ,";
			parm.add(wechat);
		}
		if (StringUtils.isNotBlank(belongHotelId)) {
			updateSql += "  belongHotelId=?  ,";
			parm.add(belongHotelId);
		}
		
		if (StringUtils.isNotBlank(account)) {
			updateSql += "  account=?  ,";
			parm.add(account);
		}
		
		if (StringUtils.isNotBlank(userKind)) {
			updateSql += "  userKind=?  ,";
			parm.add(userKind);
		}
		
		if (StringUtils.isNotBlank(name)) {
			updateSql += "  name=?  ,";
			parm.add(name);
		}
		
		if (StringUtils.isNotBlank(belongHotel)) {
			updateSql += "  belongHotel=?  ,";
			parm.add(belongHotel);
		}
		if (StringUtils.isNotBlank(role)) {
			updateSql += "  role=?  ,";
			parm.add(role);
		}
		if (StringUtils.isNotBlank(nickName)) {
			updateSql += "  nickName=? ,";
			parm.add(nickName);
		}
		if (StringUtils.isNotBlank(gender)) {
			updateSql += "  gender=?  ,";
			parm.add(gender);
		}
		if (StringUtils.isNotBlank(language)) {
			updateSql += "  language=?  ,";
			parm.add(language);
		}
		if (StringUtils.isNotBlank(city)) {
			updateSql += "  city=? ,";
			parm.add(city);
		}
		if (StringUtils.isNotBlank(province)) {
			updateSql += "  province=? ,";
			parm.add(province);
		}
		if (StringUtils.isNotBlank(country)) {
			updateSql += "  country=? ,";
			parm.add(country);
		}
		if (StringUtils.isNotBlank(avatarUrl)) {
			updateSql += "  avatarUrl=? ,";
			parm.add(avatarUrl);
		}
		if (StringUtils.isNotBlank(code)) {
			updateSql += "  code=? ,";
			parm.add(code);
		}

		if (StringUtils.isNotBlank(id)) {
			updateSql += "  id=?  where id=? ";
			parm.add(id);
			parm.add(id);
		}
		try {
			count = jdbc.update(updateSql, parm.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int insertOwner(Map<String, String> map) {
		final	String  sql="insert into hotelowner(id, account, name, mobile, idCard, kind, hotelName, status, applyStatus, inputDate, orderNo)" +
		" values (?,?,?,?,?,?,?,?,?,?,?)";
		int a = 0;
final   String time = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
final	String id = map.get("id");// 所属酒店
final   String account = (map.get("mobile")==null?"":map.get("mobile"));
final   String ownerName = (map.get("ownerName")==null?"":map.get("ownerName"));
final   String mobile = (map.get("mobile")==null?"":map.get("mobile"));
final   String hotelName = (map.get("hotelName")==null?"":map.get("hotelName"));
final   String idCard = (map.get("idCard")==null?"":map.get("idCard"));
final   String hotelKind = (map.get("hotelKind")==null?"":map.get("hotelKind"));
KeyHolder keyHolder = new GeneratedKeyHolder();
try {

			a = this.getJdbc().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, id);
					ps.setString(2, account);
					ps.setString(3, ownerName);
					ps.setString(4, mobile);
					ps.setString(5, idCard);
					ps.setString(6, hotelKind);
					ps.setString(7, hotelName);
					ps.setString(8, "0");
					ps.setString(9, "2");
					ps.setString(10, time);
					ps.setString(11, null);
					return ps;
				}
			}, keyHolder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (a > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public int updateOwer(Map<String, String> map) {
		List parm =new ArrayList();
		int count=0;
		  String  updateSql=" update  hotelowner set      ";
		   String ownerName = (map.get("ownerName")==null?"":map.get("ownerName"));
		   String hotelKind = (map.get("hotelKind")==null?"":map.get("hotelKind"));
		   String hotelName = (map.get("hotelName")==null?"":map.get("hotelName"));
		   String idCard = (map.get("idCard")==null?"":map.get("idCard"));
		   String owerid = (map.get("owerid")==null?"":map.get("owerid"));
		   String mobile = (map.get("mobile")==null?"":map.get("mobile"));

		if (StringUtils.isNotBlank(ownerName)) {
			updateSql += "  name =? ,";
			parm.add(ownerName);
		}
		if (StringUtils.isNotBlank(hotelKind)) {
			updateSql += "  Kind =?  ,";
			parm.add(hotelKind);
		}
		if (StringUtils.isNotBlank(hotelName)) {
			updateSql += "  hotelName=?  ,";
			parm.add(hotelName);
		}
		if (StringUtils.isNotBlank(mobile)) {
			updateSql += "  mobile=?  ,";
			parm.add(mobile);
		}
		if (StringUtils.isNotBlank(idCard)) {
			updateSql += "  idCard=? ,";
			parm.add(idCard);
		}

		if (StringUtils.isNotBlank(owerid)) {
			updateSql += "  id=?  where id=? ";
			parm.add(owerid);
			parm.add(owerid);
		}
		try {
			count = jdbc.update(updateSql, parm.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List getStatus(String account) {
		List list=new ArrayList();
	String sql=" select * from  hotelowner  where  account=?  ";
	try {
		  list=jdbc.queryForList(sql, new Object[]{account});
	} catch (Exception e) {
		e.printStackTrace();
	}
	return list;
	}

	@Override
	public int updateUserQy(String id, String qy) {
		int count=0;
	String sql=" update hotelowner set  ownerqy =?  where  id= ? ";
	try {
		count=jdbc.update(sql, new Object[]{qy,id});
	} catch (Exception e) {
		e.printStackTrace();
	}
		return count;
	}

	
	
}
