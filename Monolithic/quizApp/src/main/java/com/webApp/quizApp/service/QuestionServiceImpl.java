package com.webApp.quizApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webApp.quizApp.Dao.QuestionDao;
import com.webApp.quizApp.pojos.Question;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	QuestionDao questionDao;

	@Override
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		
		
	}

	@Override
	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		try {
			return new ResponseEntity<>(questionDao.getByCategory(category), HttpStatus.OK);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<String> addQuestion(Question question) {
		questionDao.save(question);
		
		return new ResponseEntity("Success", HttpStatus.CREATED);
		
	}

}
