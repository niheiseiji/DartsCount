package com.example.demo.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Survey;

@Repository
public class SurveyDaoImpl implements SurveyDao {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SurveyDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void insertSurvey(Survey survey) {
		jdbcTemplate.update("INSERT INTO survey(age, gender, comment, created) VALUES(?,?,?,?)" ,
				survey.getAge(),survey.getGender(), survey.getComment(), survey.getCreated());
	}

	@Override
	public List<Survey> getAll() {
		System.out.println(1);
		String sql = "SELECT id, age, gender, comment, created FROM survey";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
		System.out.println(resultList);
		List<Survey> list = new ArrayList<Survey>();
		
		for(Map<String, Object> result: resultList) {
			Survey survey = new Survey();
			survey.setId((int)result.get("id"));
			survey.setAge((int)result.get("age"));
			survey.setGender((int)result.get("gender"));
			survey.setComment((String)result.get("comment"));
			survey.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
			list.add(survey);
		}
		return list;
	}

}
