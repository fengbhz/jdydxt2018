package com.vdong.controller;

import com.vdong.commons.bean.Msg;
import com.vdong.commons.db.DBManager;
import com.vdong.services.UserManagerService;
import com.vdong.commons.util.exportExcelUtil;
import com.vdong.commons.util.tool;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员用户管理
 *
 * @author Administrator
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    private UserManagerService userManagerService;

    @RequestMapping(value = {"platform/deleteUser"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @SuppressWarnings({"rawtypes", "unchecked"})
    public String deteleUser(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        boolean flag = true;
        Map map = new HashMap();
        String id = request.getParameter("id");
        try {
            flag = userManagerService.deleteUser(id);
            map.put("success", flag);
        } catch (Exception e) {
            map.put("success", false);
        }
        return "admin/user/list";
    }

    /**
     * 添加业主
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = {"platform/checkUser"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    @SuppressWarnings({"rawtypes", "unchecked"})
    public String checkUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean flag = true;
        Map map = new HashMap();
        String account = request.getParameter("account");
        String id = request.getParameter("id");
        String phone = request.getParameter("phone");
        try {
            String count = userManagerService.addUser(account, phone);
            if (id.equals(count) || count.equals("")) {
                flag = false;
            }
            map.put("success", flag);
        } catch (Exception e) {
            map.put("success", false);
        }
        JSONObject json = JSONObject.fromObject(map);
        return json.toString();
    }


    /**
     * 用户初始化
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = {"user/hotelUserInit"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    @SuppressWarnings({"rawtypes", "unchecked"})
    public String hotelUserInit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean flag = true;
        Map map = new HashMap();
        String id = tool.newGUID();
        String mobile = (request.getParameter("mobile") == null ? "" : request.getParameter("mobile"));
        String nickName = (request.getParameter("nickName") == null ? "" : request.getParameter("nickName"));
        String gender = (request.getParameter("gender") == null ? "" : request.getParameter("gender"));
        String language = (request.getParameter("language") == null ? "" : request.getParameter("language"));
        String city = (request.getParameter("city") == null ? "" : request.getParameter("city"));
        String province = (request.getParameter("province") == null ? "" : request.getParameter("province"));
        String country = (request.getParameter("country") == null ? "" : request.getParameter("country"));
        String avatarUrl = (request.getParameter("avatarUrl") == null ? "" : request.getParameter("avatarUrl"));
        String code = (request.getParameter("code") == null ? "" : request.getParameter("code"));
        map.put("id", id);
        map.put("mobile", mobile);
        map.put("nickName", nickName);
        map.put("gender", gender);
        map.put("language", language);
        map.put("city", city);
        map.put("province", province);
        map.put("country", country);
        map.put("avatarUrl", avatarUrl);
        map.put("code", code);
        try {
            userManagerService.userInit(map);
            map.put("flag", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
        }
        JSONObject json = JSONObject.fromObject(map);
        return json.toString();
    }


    /**
     * 业主权益添加（修改）
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = {"user/qy"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @SuppressWarnings({"rawtypes", "unchecked"})
    public String hotelUserQy(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean flag = true;
        Map map = new HashMap();
        String id = (request.getParameter("orderId") == null ? "" : request.getParameter("orderId"));
        String qy = (request.getParameter("qy") == null ? "" : request.getParameter("qy"));
        map.put("id", id);
        map.put("qy", qy);
        try {
            userManagerService.updateUserQy(id, qy);
            map.put("flag", "success");
        } catch (Exception e) {
            map.put("flag", false);
        }
        return "admin/owner/owner";
    }


    /**
     * 用户初始化
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = {"user/hotelUserUpdate"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    @SuppressWarnings({"rawtypes", "unchecked"})
    public String hotelUserUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean flag = true;
        Map map = new HashMap();
        String id = tool.newGUID();
        String mobile = (request.getParameter("mobile") == null ? "" : request.getParameter("mobile"));
        String nickName = (request.getParameter("nickName") == null ? "" : request.getParameter("nickName"));
        String gender = (request.getParameter("gender") == null ? "" : request.getParameter("gender"));
        String language = (request.getParameter("language") == null ? "" : request.getParameter("language"));
        String city = (request.getParameter("city") == null ? "" : request.getParameter("city"));
        String province = (request.getParameter("province") == null ? "" : request.getParameter("province"));
        String country = (request.getParameter("country") == null ? "" : request.getParameter("country"));
        String avatarUrl = (request.getParameter("avatarUrl") == null ? "" : request.getParameter("avatarUrl"));
        String code = (request.getParameter("code") == null ? "" : request.getParameter("code"));
        map.put("id", id);
        map.put("mobile", mobile);
        map.put("nickName", nickName);
        map.put("gender", gender);
        map.put("language", language);
        map.put("city", city);
        map.put("province", province);
        map.put("country", country);
        map.put("avatarUrl", avatarUrl);
        map.put("code", code);
        try {
            userManagerService.userInit(map);
            map.put("success", flag);
        } catch (Exception e) {
            map.put("success", false);
        }
        JSONObject json = JSONObject.fromObject(map);
        return json.toString();
    }


    /**
     * 后台添加业主信息
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = {"user/hotelUserAdd"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @SuppressWarnings({"rawtypes", "unchecked"})
    public String hotelUserAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean flag = true;
        Map map = new HashMap();

        String id = (request.getParameter("owerid") == null ? "" : request.getParameter("owerid"));
        String mobile = (request.getParameter("mobile") == null ? "" : request.getParameter("mobile"));
        String nickName = (request.getParameter("nickName") == null ? "" : request.getParameter("nickName"));
        String gender = (request.getParameter("gender") == null ? "" : request.getParameter("gender"));
        String language = (request.getParameter("language") == null ? "" : request.getParameter("language"));
        String city = (request.getParameter("city") == null ? "" : request.getParameter("city"));
        String province = (request.getParameter("province") == null ? "" : request.getParameter("province"));
        String country = (request.getParameter("country") == null ? "" : request.getParameter("country"));
        String avatarUrl = (request.getParameter("avatarUrl") == null ? "" : request.getParameter("avatarUrl"));
        String code = (request.getParameter("code") == null ? "" : request.getParameter("code"));
        String owerid = request.getParameter("owerid");
        String uid = request.getParameter("uid");
        String hotelKind = request.getParameter("hotelKind");
        String hotelName = request.getParameter("hotelName");
        String ownerName = request.getParameter("ownerName");
        String idCard = request.getParameter("idCard");
        map.put("id", id);
        map.put("owerid", owerid);
        map.put("hotelKind", hotelKind);
        map.put("hotelName", hotelName);
        map.put("ownerName", ownerName);
        map.put("idCard", idCard);
        map.put("mobile", mobile);
        map.put("nickName", nickName);
        map.put("gender", gender);
        map.put("language", language);
        map.put("city", city);
        map.put("province", province);
        map.put("country", country);
        map.put("avatarUrl", avatarUrl);
        map.put("code", code);
        map.put("uid", uid);
        try {
            if (StringUtils.isNotBlank(id)) {
                map.put("id", id);
                userManagerService.updateUserforOwer(map);
            } else {
                map.put("id", tool.newGUID());
                userManagerService.insertUserMsg(map);
            }
            map.put("success", flag);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        }
        return "admin/owner/owner";
    }


    /**
     * 业主信息导出【不做安全性处理】
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "yz/exportExcel")
    public void exportYzExcel(HttpServletRequest request, HttpServletResponse response) {

        String queryName = request.getParameter("queryName");
        String queryMobile = request.getParameter("queryMobile");
        String queryStatus = request.getParameter("queryStatus");
        String sql = "";
        String condition = "";
        if (queryName != null && !queryName.equals("")) {
            condition += " and o.name like '%" + queryName + "%' ";
        }
        if (queryMobile != null && !queryMobile.equals("")) {
            condition += " and o.mobile = '" + queryMobile + "' ";
        }
        if (queryStatus != null && !queryStatus.equals("")) {
            condition += " and o.status = '" + (queryStatus.equals("正常") ? "0" : "1") + "' ";
        }
        if (condition.equals("")) {
            sql = "select o.id, u.id as  uid,o.name, o.account, o.mobile, o.idCard, o.kind,o.hotelName, o.status, o.applyStatus, date_format(o.inputDate, '%Y-%m-%d') as inputDate, o.orderNo, u.virtualCoin from hotelowner o LEFT JOIN hoteluser u ON u.account = o.account ";
        } else {
            sql = "select o.id, u.id as  uid, o.name, o.account, o.mobile, o.idCard, o.kind,o.hotelName, o.status, o.applyStatus, date_format(o.inputDate, '%Y-%m-%d') as inputDate, o.orderNo, u.virtualCoin from hotelowner o LEFT JOIN hoteluser u ON u.account = o.account where 1=1 " + condition;
        }
        sql += "  order by o.inputDate desc ";
        System.out.print("sql" + sql);
        DBManager db = new DBManager();
        List list = db.find(sql);
        Map<String, String> remap = exportExcelUtil.exportQM(list, "YZ", request, response);
        try {
            exportExcelUtil.downLoadFile(remap.get("path"), response, remap.get("fileName"), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 赠送管理导出	【不做安全性处理】
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "money/exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        String queryName = request.getParameter("queryName");
        String queryFriend = request.getParameter("queryFriend");
        String sql = "";
        String condition = "";
        if (queryName != null && !queryName.equals("")) {
            condition += " and tw.name like '%" + queryName + "%' ";
        }
        if (queryFriend != null && !queryFriend.equals("")) {
            condition += " and tu.name  like '%" + queryFriend + "%' ";
        }
        if (condition.equals("")) {
            sql = "select tg.id,tw.name  as yzName,tg.sum,tu.nickname  as  friendName,tg.giveTime,tg.receiveTime   from   hotelgive   tg    left join    hotelowner  tw  on  tg.account=tw.account left   join  hoteluser  tu  on  tg.receiveAccount=tu.account";
        } else {
            sql = "select tg.id,tw.name  as yzName,tg.sum,tu.nickname  as  friendName,tg.giveTime,tg.receiveTime   from   hotelgive   tg    left join    hotelowner  tw  on  tg.account=tw.account    join  hoteluser  tu  on  tg.receiveAccount=tu.account where 1 = 1 " + condition + " order by tg.receiveTime";
        }
        DBManager db = new DBManager();
        List list = db.find(sql);
        Map<String, String> remap = exportExcelUtil.exportQM(list, "MONEY", request, response);
        try {
            exportExcelUtil.downLoadFile(remap.get("path"), response, remap.get("fileName"), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 赠送管理导出	【不做安全性处理】
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "friend/exportExcel")
    public void exportExcelForFriend(HttpServletRequest request, HttpServletResponse response) {
        String phone = request.getParameter("phone");
        String queryFriend = request.getParameter("queryFriend");
        String sql = "";
        String condition = "";
        if (phone != null && !phone.equals("")) {
            condition += " and tu.mobile = '" + phone + "' ";
        }
        if (queryFriend != null && !queryFriend.equals("")) {
            condition += " and tu.nickname  like '%" + queryFriend + "%' ";
        }
        if (condition.equals("")) {
            sql = "select tg.id ,tu.mobile,tu.virtualCoin as sum,tu.nickname  as  friendName,tg.giveTime,tg.receiveTime   from   hoteluser  tu      join  hotelgive   tg   on  tg.receiveAccount=tu.account  where   tu.userkind='0'";
        } else {
            sql = "select tg.id,tu.mobile,tu.virtualCoin as sum,tu.nickname  as  friendName,tg.giveTime,tg.receiveTime   from    hoteluser  tu      join   hotelgive   tg    on  tg.receiveAccount=tu.account where   tu.userkind='0' " + condition + " ";
        }
        sql += "  order by tg.giveTime desc ";
        DBManager db = new DBManager();
        List list = db.find(sql);
        Map<String, String> remap = exportExcelUtil.exportQM(list, "FRIEND", request, response);
        try {
            exportExcelUtil.downLoadFile(remap.get("path"), response, remap.get("fileName"), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 赠送管理导出	【不做安全性处理】
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "coin/exportExcel")
    public void exportExcelCoin(HttpServletRequest request, HttpServletResponse response) {
        String account = request.getParameter("account");
        String type = request.getParameter("type");
        System.out.println("type------>" + type);

        String sql = "";
        String condition = "";
        if (account != null && !account.equals("")) {
            condition += " and tu.account = '" + account + "' ";
        }

        if (type != null && !type.equals("")) {
            type = type.equals("充值") ? "0" : "1";
            condition += " and tu.type = '" + type + "' ";
        }
        sql = "select *  from   t_log  tu   where   1=1  ";
        if (condition.equals("")) {

        } else {
            sql = sql + condition;
        }
        System.out.print("sql----->" + sql);

        DBManager db = new DBManager();
        List list = db.find(sql);
        Map<String, String> remap = exportExcelUtil.exportQM(list, "COIN", request, response);
        try {
            exportExcelUtil.downLoadFile(remap.get("path"), response, remap.get("fileName"), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 后台添加业主信息
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = {"user/getOwner"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    @SuppressWarnings({"rawtypes", "unchecked"})
    public String getOwner(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map map = new HashMap();
        String mobile = (request.getParameter("mobile") == null ? "" : request.getParameter("mobile"));
        try {
            List list = userManagerService.getOwner(mobile);
            map.put("success", true);
            map.put("data", list);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        }
        JSONObject json = JSONObject.fromObject(map);
        return json.toString();
    }


    /**
     * 业主页面信息查询
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = {"owner/ownerList"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    @SuppressWarnings({"rawtypes", "unchecked"})
    public String ownerList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map _map = new HashMap();
        DBManager db = new DBManager();
        String queryName = request.getParameter("queryName");
        String queryMobile = request.getParameter("queryMobile");
        String queryStatus = request.getParameter("queryStatus");
        String _curPage = request.getParameter("curPage") == null ? "1" : request.getParameter("curPage");
        String _pageNum = request.getParameter("pageNum") == null ? "" : request.getParameter("pageNum");
        String sql = "";
        String condition = "";
        if (queryName != null && !queryName.equals("")) {
            condition += " and o.name like '%" + queryName + "%' ";
        }
        if (queryMobile != null && !queryMobile.equals("")) {
            condition += " and o.mobile = '" + queryMobile + "' ";
        }
        if (queryStatus != null && !queryStatus.equals("")) {
            condition += " and o.status = '" + (queryStatus.equals("正常") ? "0" : "1") + "' ";
        }
        //condition+=" and li  ";
        sql = "select o.id, u.id as  uid,o.name, o.ownerqy, o.account, o.mobile, o.idCard, o.kind,o.hotelName, o.status, o.applyStatus, date_format(o.inputDate, '%Y-%m-%d') as inputDate, o.orderNo, u.virtualCoin from hotelowner o LEFT JOIN hoteluser u ON u.account = o.account where 1=1 ";
        if (!condition.equals("")) {
            sql = sql + condition;
        }
        sql += "  order by o.inputDate desc ";
        System.out.print("sql" + sql);


        int pageNum = Integer.parseInt(_pageNum);
        int currPage = Integer.parseInt(_curPage);

        int start = pageNum * (currPage - 1);
        int end = 20;
        String ywSql = tool.paginationFormysql(sql.toString(), start, end);//拼装sql（分页）
        System.out.println("拼装的sql  " + ywSql);
        List mqryList;
        int count;
        try {
            mqryList = db.find(ywSql);
            count = db.find(sql.toString()).size();
            System.out.println("获取分页后信息：" + mqryList);

            _map.put("rowcount", count);
            //总共有多少页
            int pageCount = 0;
            if (count > 0) {
                pageCount = (count - 1) / pageNum + 1;
            }
            _map.put("pagecount", pageCount);
            _map.put("info", mqryList);


        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject json = JSONObject.fromObject(_map);
        return json.toString();
    }


    /**
     * 业主页面信息查询
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = {"owner/test"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    //@ResponseBody
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void test(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入这个方法");
    }


    @RequestMapping(value = {"owner/ownerQy"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    @SuppressWarnings({"rawtypes", "unchecked"})
    public String ownerQy(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String id = request.getParameter("oid");
        String sql = "  select * from  hotelowner where  id='" + id + "'";
        Map _map = new HashMap();
        DBManager db = new DBManager();
        List list = db.find(sql.toString());
        if (null != list && list.size() > 0) {
            Map map = (Map) list.get(0);
            String qy = String.valueOf(map.get("ownerqy"));
            _map.put("data", qy);
            _map.put("success", true);
        } else {
            _map.put("success", false);
        }
        JSONObject json = JSONObject.fromObject(_map);
        return json.toString();

    }

    @RequestMapping(value = {"user/holidaylist"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String hotelList(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Msg msg = new Msg();
        String queryName = request.getParameter("queryName");
        String queryFriend = request.getParameter("queryFriend");
        String _curPage = request.getParameter("curPage") == null ? "1" : request.getParameter("curPage");
        String _pageNum = request.getParameter("pageNum") == null ? "" : request.getParameter("pageNum");
        Map map = new HashMap();
        Map _map = new HashMap();
        Connection conn = DBManager.getConnection();
        DBManager db = new DBManager();
        String sql = "";
        String condition = "";
        if (queryName != null && !queryName.equals("")) {
            condition += " and tw.name like '%" + queryName.trim() + "%' ";
        }
        if (queryFriend != null && !queryFriend.equals("")) {
            condition += " and tu.nickname  like '%" + queryFriend.trim() + "%' ";
        }
        if (condition.equals("")) {
            sql = "select tg.id,tw.name  as yzName,tg.sum,tu.nickname  as  friendName, date_format(tg.giveTime,'%Y-%c-%d %h:%i:%s') giveTime ,date_format(tg.receiveTime,'%Y-%c-%d %h:%i:%s') receiveTime    from   hotelgive   tg    left join    hotelowner  tw  on  tg.account=tw.account left   join  hoteluser  tu  on  tg.receiveAccount=tu.account";
        } else {
            sql = "select tg.id,tw.name  as yzName,tg.sum,tu.nickname  as  friendName,tg.giveTime,tg.receiveTime   from   hotelgive   tg    left join    hotelowner  tw  on  tg.account=tw.account    join  hoteluser  tu  on  tg.receiveAccount=tu.account where 1 = 1 " + condition + " order by tg.receiveTime";
        }
        int pageNum = Integer.parseInt(_pageNum);
        int currPage = Integer.parseInt(_curPage);
        int start = pageNum * (currPage - 1);
        int end = 20;
        String ywSql = tool.paginationFormysql(sql.toString(), start, end);//拼装sql（分页）
        System.out.println("拼装的sql  " + ywSql);
        List mqryList;
        int count;
        try {
            mqryList = db.find(ywSql);
            count = db.find(sql.toString()).size();
            System.out.println("获取分页后信息：" + mqryList);

            _map.put("rowcount", count);
            //总共有多少页
            int pageCount = 0;
            if (count > 0) {
                pageCount = (count - 1) / pageNum + 1;
            }
            _map.put("pagecount", pageCount);
            _map.put("info", mqryList);


        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject json = JSONObject.fromObject(_map);
        return json.toString();
    }


    @RequestMapping(value = {"user/fiendlist"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String fiendList(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Msg msg = new Msg();
        String phone = request.getParameter("phone");
        String queryFriend = request.getParameter("queryFriend");
        String _curPage = request.getParameter("curPage") == null ? "1" : request.getParameter("curPage");
        String _pageNum = request.getParameter("pageNum") == null ? "" : request.getParameter("pageNum");
        Map map = new HashMap();
        Map _map = new HashMap();
        Connection conn = DBManager.getConnection();
        DBManager db = new DBManager();
        String sql = "";
        String condition = "";
        if (phone != null && !phone.equals("")) {
            condition += " and tu.mobile = '" + phone + "' ";
        }
        if (queryFriend != null && !queryFriend.equals("")) {
            condition += " and tu.nickname  like '%" + queryFriend + "%' ";
        }
        if (condition.equals("")) {
            sql = "select tu.mobile,tu.virtualCoin as sum,tu.nickname  as  friendName from   hoteluser  tu        where   tu.userkind='0'";
        } else {
            sql = "select tu.mobile,tu.virtualCoin as sum,tu.nickname  as  friendName   from    hoteluser  tu      where   tu.userkind='0' " + condition + " ";
        }
        sql += "   ";
        int pageNum = Integer.parseInt(_pageNum);
        int currPage = Integer.parseInt(_curPage);
        int start = pageNum * (currPage - 1);
        int end = 20;
        String ywSql = tool.paginationFormysql(sql.toString(), start, end);//拼装sql（分页）
        System.out.println("拼装的sql  " + ywSql);
        List mqryList;
        int count;
        try {
            mqryList = db.find(ywSql);
            count = db.find(sql.toString()).size();
            System.out.println("获取分页后信息：" + mqryList);

            _map.put("rowcount", count);
            //总共有多少页
            int pageCount = 0;
            if (count > 0) {
                pageCount = (count - 1) / pageNum + 1;
            }
            _map.put("pagecount", pageCount);
            _map.put("info", mqryList);


        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject json = JSONObject.fromObject(_map);
        return json.toString();
    }

}
