package com.vdong.commons.util;

import com.vdong.commons.db.DBManager;

import java.util.List;

public class JdbcUtil {

	public String test() {
		String sql = "   select * from hotelowner    where  account not  in (select account from  hoteluser)";

		DBManager db = new DBManager();
		List list = db.find(sql);
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				
		}

		}

		return null;

	}

}
