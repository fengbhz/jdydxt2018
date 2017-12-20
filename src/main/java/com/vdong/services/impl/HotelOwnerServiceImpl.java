package com.vdong.services.impl;

import com.vdong.commons.dao.HotelOwnerDao;
import com.vdong.services.HotelOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("hotelOwnerService")
public class HotelOwnerServiceImpl implements HotelOwnerService {

@Autowired
private HotelOwnerDao hotelOwnerDao;

public boolean bindingYz(String phone) {
	return hotelOwnerDao.bindingYz(phone);
}
	
	



}
