package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Darts;

@Repository
public class DartsDaoImpl implements DartsDao {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public DartsDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void insertDarts(Darts darts) {
		//prepairedstatmentでSQLインジェクションを防ぐ			
		jdbcTemplate.update("INSERT INTO darts(total_score) VALUES(?)" ,
				darts.getTotalScore());
	}

//	@Override
//	public List<Inquiry> getAll() {
//		String sql = "SELECT id, name, email, contents, created FROM inquiry";
//		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
//		List<Inquiry> list = new ArrayList<Inquiry>();
//		
////		eintityにデータをつめる
////		serviceとDAOとのやりとりではエンティティを使う
//		for(Map<String, Object> result: resultList) {
//			Inquiry inquiry = new Inquiry();
//			inquiry.setId((int)result.get("id"));
//			inquiry.setName((String)result.get("name"));
//			inquiry.setEmail((String)result.get("email"));
//			inquiry.setContents((String)result.get("contents"));
//			inquiry.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
//			list.add(inquiry);
//		}
//		
//		return list;
//	}
//
//	@Override
//	public int updateInquiry(Inquiry inquiry) {
//		return jdbcTemplate.update("UPDATE inquiry SET name = ?, email = ?, contents = ? WHERE id = ?",
//				inquiry.getName(), inquiry.getEmail(), inquiry.getContents(), inquiry.getCreated());
//	}

}
