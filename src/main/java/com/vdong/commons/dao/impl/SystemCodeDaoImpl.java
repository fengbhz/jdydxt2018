package com.vdong.commons.dao.impl;

import com.mysql.jdbc.Statement;
import com.vdong.commons.bean.Attach;
import com.vdong.commons.dao.SystemCodeDao;
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
import java.util.List;
import java.util.Map;

@Repository("systemCodeDao")
@SuppressWarnings( { "rawtypes", "unchecked" })
public class SystemCodeDaoImpl implements SystemCodeDao {
	private final Logger logger = LoggerFactory.getLogger(SystemCodeDaoImpl.class);
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
	public List getList(String dmlb, String dmz) {
		List parm = new ArrayList();
		List list = new ArrayList();
		String sql = "   select   * from t_systemcode  t   where  status=0 ";
		if (StringUtils.isNotBlank(dmlb)) {
			sql += "  and  type=?  ";
			parm.add(dmlb);
		}

		if (StringUtils.isNotBlank(dmz)) {
			sql += "  and  type=?  ";
			parm.add(dmlb);
		}

		try {
			list = jdbc.queryForList(sql, parm.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int deleteAttach(String id) {
		int count = 0;
		String sql = " update t_attach set isdeleted='1'  where id=? ";
		try {
			count = jdbc.update(sql, new Object[] { Long.valueOf(id) });
			if (count > 0) {// 将文件也删除掉 暂时可不做
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List getAttach(Map<String, String> map) {
		List parm = new ArrayList();
		List list = new ArrayList();
		String id = map.get("id");
		String url = map.get("url");
		String originName = map.get("originName");
		String fileName = map.get("fileName");
		String isdeleted = map.get("isdeleted");
		String sql = " select * from t_attach where 1=1   ";
		
		if (StringUtils.isNotBlank(id)) {
			sql += " and  id=? ";
			parm.add(id);
		}
		
		if (StringUtils.isNotBlank(originName)) {
			sql += " and  originName=? ";
			parm.add(originName);
		}
		
		if (StringUtils.isNotBlank(fileName)) {
			sql += " and  fileName=? ";
			parm.add(fileName);
		}
		
		if (StringUtils.isNotBlank(url)) {
			sql += " and  url=? ";
			parm.add(url);
		}
		
		if (StringUtils.isNotBlank(isdeleted)) {
			sql += " and  isdeleted=? ";
			parm.add(isdeleted);
		}
		
		try {
			list = jdbc.queryForList(sql, parm.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int saveAttach(final Attach attach) {
		final String sql = " insert into t_attach(fileName,originName,url) values (?,?,?) ";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int a = 0;
		try {
			a = this.getJdbc().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, attach.getFileName());
					ps.setString(2, attach.getOriginName());
					ps.setString(3, attach.getUrl());
					return ps;
				}
			}, keyHolder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (a > 0) {
			return keyHolder.getKey().intValue();
		} else {
			return 0;
		}
	}

	@Override
	public int updateAttach(Attach attach) {
		int count = 0;
		List parm = new ArrayList();
		String sql = " update t_attach set ";
		
		
		if (StringUtils.isNotBlank(attach.getOriginName())) {
			sql += "   originName=?  ,";
			parm.add(attach.getOriginName());
		}
		
		if (StringUtils.isNotBlank(attach.getFileName())) {
			sql += "   fileName=? , ";
			parm.add(attach.getFileName());
		}
		
		if (StringUtils.isNotBlank(attach.getUrl())) {
			sql += "   url=? ,";
			parm.add(attach.getUrl());
		}
		
		if (StringUtils.isNotBlank(attach.getIsDeleted())) {
			sql += "   isdeleted=? ,";
			parm.add(attach.getIsDeleted());
		}
		
			sql += "   id= ?  where  id=?  ";
			parm.add(attach.getId());
			parm.add(attach.getId());
		
		
		try {
			count = jdbc.update(sql, new Object[] {parm.toArray()});
			if (count > 0) {// 将文件也删除掉 暂时可不做
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
