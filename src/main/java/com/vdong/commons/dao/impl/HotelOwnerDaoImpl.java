package com.vdong.commons.dao.impl;

import com.vdong.commons.dao.BaseDao;
import com.vdong.commons.dao.HotelOwnerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository("hotelOwnerDao")
public class HotelOwnerDaoImpl implements HotelOwnerDao {
	private final Logger logger = LoggerFactory.getLogger(BaseDao.class);
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

	public boolean bindingYz(String phone) {
		String        sql = " update  hotelowner set  applystatus=3  where  (applystatus=2  or applystatus=3 or applystatus=1)  and  mobile=?  ";
		String sql_status = " update  hoteluser  set  userkind ='1'  where mobile=?  ";  //  更新用户为业主
		int count = 0;
		try {
			count = jdbc.update(sql, new Object[] { phone });
			 if(count>0){
				 jdbc.update(sql_status, new Object[] { phone });
			   }
		} catch (Exception e) {
			logger.info("bindingYz---->"+e.getMessage());
			e.printStackTrace();
		}
		if (count < 1) {
			return false;
		}
		return true;
	}
}
