package com.vdong.commons.dao.impl;

import com.mysql.jdbc.Statement;
import com.vdong.commons.bean.PriceManager;
import com.vdong.commons.dao.ProductDao;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
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
import java.util.List;

@Repository("productDao")
@SuppressWarnings("unchecked")
public class ProductDaoImpl implements ProductDao {
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

	@Override
	public int insertEntrty(final PriceManager p) {
		final String sql = " insert into t_detail_price "
				+ " (room_id,hotel_id,detailtime,rmb_price,djb_price,daily_stock,ms_Price,js_Price,max_buy,mix_buy,isdeleted,payway,priceRetail) values(?,?,?,?,?,?,?,?,?,?,?,?,?)  ";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int a = 0;
		try {
			a = this.getJdbc().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1,  p.getRoomId());
					ps.setInt(2, 0);
					ps.setString(3, p.getDate());
					ps.setInt(4, Integer.parseInt(StringUtils.isBlank(p.getPrice()) ? "0" : p.getPrice()));
					ps.setInt(5, Integer.parseInt(StringUtils.isBlank(p.getDjbprice()) ? "0" : p.getDjbprice()));
					ps.setInt(6, Integer.parseInt(StringUtils.isBlank(p.getStock()) ? "0" : p.getStock()));
					ps.setInt(7, Integer.parseInt(StringUtils.isBlank(p.getPriceMarket()) ? "0" : p.getPriceMarket()));
					ps.setInt(8, Integer.valueOf(StringUtils.isBlank(p.getPriceSettlement()) ? "0" : p.getPriceSettlement()));
					ps.setInt(9, Integer.valueOf(StringUtils.isBlank(p.getBuyNumMax()) ? "0" : p.getBuyNumMax()));
					ps.setInt(10, Integer.valueOf(StringUtils.isBlank(p.getBuyNumMin()) ? "0" : p.getBuyNumMin()));
					ps.setString(11,StringUtils.isBlank(p.getStatus()) ? "0" : p.getStatus());
					ps.setString(12, (StringUtils.isBlank(p.getPaytype()) ? "0" : p.getPaytype()));
					ps.setString(13, StringUtils.isBlank(p.getPriceRetail()) ? "0" : p.getPriceRetail());
					return ps;
				}
			}, keyHolder);
		} catch (DuplicateKeyException e) {// 主键重复的异常，进行更新处理
			List parm = new ArrayList();
			String updaSql = " update t_detail_price set  ";
			if (StringUtils.isNotBlank(p.getBuyNumMax())) {
				updaSql += "  max_buy=  ? , ";
				parm.add(Integer.valueOf(p.getBuyNumMax()));
			}

			if (StringUtils.isNotBlank(p.getBuyNumMin())) {
				updaSql += "  mix_buy=  ? ,";
				parm.add(Integer.valueOf(p.getBuyNumMin()));
			}

			if (StringUtils.isNotBlank(p.getPrice())) {
				updaSql += "  rmb_price =  ? ,";
				parm.add(Integer.valueOf(p.getPrice()));
			}

			if (StringUtils.isNotBlank(p.getDjbprice())) {
				updaSql += "  djb_price =  ? ,";
				parm.add(Integer.valueOf(p.getDjbprice()));
			}


			if (StringUtils.isNotBlank(p.getPaytype())) {
				updaSql += "  payway=  ? ,";
				parm.add(Integer.valueOf(p.getPaytype()));
			}


			if (StringUtils.isNotBlank(p.getStatus())) {
				updaSql += "   isdeleted=  ? ,";
				parm.add(Integer.valueOf(p.getStatus()));
			}

			
			if (StringUtils.isNotBlank(p.getPriceMarket())) {
				updaSql += "   ms_Price=  ? ,";
				parm.add(Integer.valueOf(p.getPriceMarket()));
			}
			
			if (StringUtils.isNotBlank(p.getPriceRetail())) {
				updaSql += "   PriceRetail=  ? ,";
				parm.add(Integer.valueOf(p.getPriceRetail()));
			}
			
			
			
			if (StringUtils.isNotBlank(p.getPriceSettlement())) {
				updaSql += "   js_Price=  ? ,";
				parm.add(Integer.valueOf(p.getPriceSettlement()));
			}
			updaSql += "   room_id=  ?  where  room_id=  ? and  detailtime=  ?  "  ;
			parm.add(p.getRoomId());
			parm.add(p.getRoomId());
			parm.add(p.getDate());
			jdbc.update(updaSql,parm.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	/**
	 * 当前时间（根据时间和房型ID来判断）
	 * 
	 * @return
	 */
	
	public List getJList(String rooId, String detailtime) {
		List list = new ArrayList();
		List parm = new ArrayList();
		String sql = " select room_id,hotel_id,DATE_FORMAT(detailtime,'%Y-%m-%d') as date,rmb_price as price,djb_price as djbprice,daily_stock as stock , "
				+ "    ms_Price as priceMarket,js_Price as priceSettlement,max_buy as buyNumMax,mix_buy as buyNumMin,payway as paytype,isdeleted as status,priceRetail "
				+ " from  t_detail_price  where  room_id=  ?   ";
		parm.add(rooId);
		if (StringUtils.isNotBlank(detailtime)) {
			sql += " and   MONTH(?)-MONTH (NOW())<= 6 and   MONTH(?)-MONTH (NOW())>=0  and     detailtime=  ?   ";
			parm.add(detailtime);
			parm.add(detailtime);
			parm.add(detailtime);
		}
		try {
			list = jdbc.queryForList(sql, parm.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 当前时间（根据时间和房型ID来判断）
	 * 
	 * @return
	 */
	public List getJListByday(String rooId, String detailtime) {
		List list = new ArrayList();
		List parm = new ArrayList();
		String sql = " select room_id,hotel_id,DATE_FORMAT(detailtime,'%Y-%m-%d') as date,rmb_price as price,djb_price as djbprice,daily_stock as stock , "
				+ "    ms_Price as priceMarket,js_Price as priceSettlement,max_buy as buyNumMax,mix_buy as buyNumMin,payway as paytype,isdeleted as status,priceRetail "
				+ " from  t_detail_price  where  DAYOFYEAR(detailtime)-DAYOFYEAR (NOW())<= 60 and room_id=  ?   and DAYOFYEAR(detailtime) - DAYOFYEAR(NOW())>=0   ";
		parm.add(rooId);
		if (StringUtils.isNotBlank(detailtime)) {
			sql += " and  detailtime=  ?   ";
			parm.add(detailtime);
		}
		try {
			list = jdbc.queryForList(sql, parm.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
