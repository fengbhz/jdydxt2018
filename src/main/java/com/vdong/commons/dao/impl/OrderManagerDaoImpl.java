package com.vdong.commons.dao.impl;

import com.mysql.jdbc.Statement;
import com.vdong.commons.dao.BaseDao;
import com.vdong.commons.dao.OrderManagerDao;
import com.vdong.commons.util.Constants;
import com.vdong.commons.util.DateUtil;
import com.vdong.commons.util.PinYin;
import com.vdong.commons.util.tool;
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

@Repository("orderManagerDao")
@SuppressWarnings({"rawtypes", "unchecked"})
public class OrderManagerDaoImpl extends BaseDao implements OrderManagerDao {

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

    public int checkLogin(String username, String password) {
        int count = 0;
        try {
            String sql = " select * from  admin   where userName=? and userPw=?   ";
            count = jdbc.queryForList(sql, new Object[]{username, password})
                    .size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public List getUserMsg(String username) {

        List list = new ArrayList();
        try {
            String sql = "  select a.*,t.code,t.name as roleName from  admin a left join t_role t  on a.roleid=t.id  where username=? ";
            list = jdbc.queryForList(sql, new Object[]{username});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public int orderManager(String account, int c, String type) {
        int count = 0;
        String sql = " update hoteluser set  virtualCoin=virtualCoin + ? where  account=? ";
        try {
            count = jdbc.update(sql, new Object[]{c, account});
            Map remap = new HashMap();
            remap.put("careate_sql",sql);
            insertLog(remap);
        } catch (Exception e) {
            logger.info("orderUpdate---->" + e.getMessage());
            e.printStackTrace();
        }
        return count;
    }

    public boolean orderUpdate(String id) {
        String sql = "update hotelorder set  orderStatus = '5' where id=? ";
        int count = 0;
        try {
            count = jdbc.update(sql, new Object[]{id});
        } catch (Exception e) {
            logger.info("orderUpdate---->" + e.getMessage());
            e.printStackTrace();
        }
        if (count < 1) {
            return false;
        }
        return true;
    }

    public List getAccountByorderId(String id) {
        List list = new ArrayList();
        try {
            String sql = " select * from  hotelorder   where id=?  ";
            list = jdbc.queryForList(sql, new Object[]{id});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int orderManager(String account, String c, String type) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int insertOrder(Map<String, String> map) {
        int a = 0;
        final String time = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                .format(new Date());
        final String id = map.get("id");
        final String hotelId = map.get("hotelId");// 所属酒店
        final String account = (map.get("account") == null ? "" : map
                .get("account"));
        final String custom = (map.get("custom") == null ? "" : map
                .get("custom"));
        final String itemId = (map.get("itemId") == null ? "" : map
                .get("itemId"));
        final String itemName = (map.get("itemName") == null ? "" : map
                .get("itemName"));
        final String orderId = PinYin.cn2py(itemName)
                + DateUtil.dateFormatShort(new Date()) + tool.function();
        final String mobile = (map.get("mobile") == null ? "" : map
                .get("mobile"));
        final String idCard = (map.get("idCard") == null ? "" : map
                .get("idCard"));
        final String roomNum = (map.get("roomNum") == null ? "" : map
                .get("roomNum"));
        final String checkInDate = (map.get("checkInDate") == null ? "" : map
                .get("checkInDate"));
        final String checkOutDate = (map.get("checkOutDate") == null ? "" : map
                .get("checkOutDate"));
        final String sum = (map.get("sum") == null ? "" : map.get("sum"));
        final String payWay = (map.get("payWay") == null ? "" : map
                .get("payWay"));
        final String sql = "insert into hotelorder"
                + "(id, orderId, itemId, itemName, account, name, mobile, idCard, roomNum, checkInDate, "
                + " checkOutDate, sum, payStatus, payWay, orderStatus, orderTime, orderOrigin, orderUser,remark,hotelId)"
                + "  values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {

            a = this.getJdbc().update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection conn)
                        throws SQLException {
                    PreparedStatement ps = conn.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, id);
                    ps.setString(2, orderId);
                    ps.setString(3, itemId);
                    ps.setString(4, itemName);
                    ps.setString(5, account);
                    ps.setString(6, custom);
                    ps.setString(7, mobile);
                    ps.setString(8, idCard);
                    ps.setString(9, roomNum);
                    ps.setString(10, checkInDate);
                    ps.setString(11, checkOutDate);
                    ps.setString(12, sum);
                    ps.setString(13, "0");
                    ps.setString(14, payWay);
                    ps.setString(15, Constants.NON_PAY);
                    ps.setString(16, time);
                    ps.setString(17, "");
                    ps.setString(18, "");
                    ps.setString(19, "");
                    ps.setString(20, hotelId);
                    return ps;
                }
            }, keyHolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public Map<String, String> updateOrderStatus(String id, String status) {
        Map re = new HashMap();
        int count = 0;
        String sql = "  ";

        try {
            if (Constants.CHECKEDIN_STAY.equals(status)) {//已入住，核销
                sql = "  update hotelorder  set  orderStatus= ?,Verifitime=?  where  id=? ";
                count = jdbc.update(sql, new Object[]{status, new Date(), id});
            } else {
                sql = "  update hotelorder  set  orderStatus= ?  where  id=? ";
                count = jdbc.update(sql, new Object[]{status, id});
            }
            if (count > 0) {
                re.put("success", "true");
            }
        } catch (Exception e) {
            re.put("success", "false");
            e.printStackTrace();
        }
        return re;
    }

    public List getOrderDetail(String id) {
        List list = new ArrayList();
        String sql = " select o.*,r.roomName,s.hotelName, CONCAT_WS('',s.hotelName,r.roomName) as hotelroom ,t.dmmc,m.msg_map as msgmap,m.moldcode from  hotelorder o   ";
        sql += "  left   join  hotelroom      r  on  o.itemId=r.id   ";
        sql += "  left   join  hotelsupplier  s  on  s.id=r.pid ";
        sql += "  left   join (select * from  t_systemcode   where  type='OS')  t  on  t.dmz=o.orderstatus  ";
        sql += "  left   join  t_msg  m  on  m.flag=o.orderstatus where  o.id=? ";

        try {
            list = jdbc.queryForList(sql, new Object[]{id});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateOrderMsg(Map<String, String> remap) {
        List list = new ArrayList();
        StringBuffer sql = new StringBuffer();
        String id = (remap.get("id") == null ? "" : remap.get("id"));// //
        // 实际上后台传过来的是酒店的ID
        String checkInDate = (remap.get("checkInDate") == null ? "" : remap
                .get("checkInDate"));
        String checkOutDate = (remap.get("checkOutDate") == null ? "" : remap
                .get("checkOutDate"));
        String name = (remap.get("name") == null ? "" : remap.get("name"));
        String mobile = (remap.get("mobile") == null ? "" : remap.get("mobile"));
        String roomNum = (remap.get("roomNum") == null ? "" : remap
                .get("roomNum"));
        String refuNum = (remap.get("refuNum") == null ? "" : remap
                .get("refuNum"));
        String status = (remap.get("status") == null ? "" : remap.get("status"));
        if (StringUtils.isNotBlank(checkInDate)) {
            sql.append(" checkInDate=? , ");
            list.add(checkInDate.trim());
        }

        if (StringUtils.isNotBlank(checkOutDate)) {
            sql.append(" checkOutDate=? , ");
            list.add(checkOutDate.trim());
        }

        if (StringUtils.isNotBlank(name)) {
            sql.append(" name=?  ,");
            list.add(name.trim());
        }

        if (StringUtils.isNotBlank(mobile)) {
            sql.append(" mobile=?  ,");
            list.add(mobile.trim());
        }

        if (StringUtils.isNotBlank(roomNum)) {
            sql.append(" roomNum=?  ,");
            list.add(roomNum.trim());
        }

        String U_sql = "  update  hotelOrder  set  " + sql.toString()
                + " id=?  where  id=?";

        System.out.println("U_sql:---->" + U_sql);
        list.add(id);
        list.add(id);
        try {
            jdbc.update(U_sql.toString(), list.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getMsg(String orderstatus, String type) {
        String sql = " select msg_text  as  str from  t_msg where   status='0' and  type=?  and flag=?   ";
        String str = "";
        List list = new ArrayList();

        try {
            list = jdbc.queryForList(sql, new Object[]{type, orderstatus});
            if (null != list && list.size() > 0) {
                Map map = (Map) list.get(0);
                str = (String) map.get("str");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

}