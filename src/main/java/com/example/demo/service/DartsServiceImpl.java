package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Darts;
import com.example.demo.repository.DartsDao;

@Service
public class DartsServiceImpl implements DartsService {

	private final DartsDao dao;
	
	@Autowired
	DartsServiceImpl(DartsDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void save(Darts darts) {
		dao.insertDarts(darts);
	}

//	@Override
//	public List<Inquiry> getAll() {
//		return dao.getAll();
//	}
//
//	@Override
//	public void update(Inquiry inquiry) {
//		if(dao.updateInquiry(inquiry) == 0) {
//			throw new InquiryNotFoundException("cant find the same ID");
//		}
//	}

}
