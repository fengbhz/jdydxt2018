package com.vdong.services.impl;

import com.vdong.commons.bean.PriceManager;
import com.vdong.commons.dao.HotelManagerDao;
import com.vdong.commons.dao.ProductDao;
import com.vdong.commons.dao.UserManagerDao;
import com.vdong.services.HotelManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("hotelManagerService")
public class HotelManagerServiceImpl implements HotelManagerService{

@Autowired
private HotelManagerDao hotelManagerDao;

@Autowired
private UserManagerDao userManagerDao;


@Autowired
private ProductDao productDao;

	
	public int insertHotel(Map<String, String> map) {
	int c=	userManagerDao.insertUser(map);
	if(c==0){
		return 0;
	}
	    map.put("userId", String.valueOf(c));
	
		hotelManagerDao.insertHotel(map);
		
		return 0;
	}

	@Override
	public List getRoomList(Map<String, String> map) {
		return hotelManagerDao.getRoomList(map);
	}

	@Override
	public int insertProduct(PriceManager priceManager) {
		return productDao.insertEntrty(priceManager);
	}

	@Override
	public List getProductList(String roomId, String detailtime) {
		return productDao.getJList(roomId, detailtime);
	}

	@Override
	public List getProductListForDays(String roodId, String detailtime) {
		return productDao.getJListByday(roodId, detailtime);
	}

}
