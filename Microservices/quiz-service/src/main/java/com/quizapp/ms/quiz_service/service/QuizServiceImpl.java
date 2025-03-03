package com.quizapp.ms.quiz_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quizapp.ms.quiz_service.DTO.QuestionDto;
import com.quizapp.ms.quiz_service.DTO.QuizAnswerDto;
import com.quizapp.ms.quiz_service.Dao.QuizDao;
import com.quizapp.ms.quiz_service.feign.FeignInterface;
import com.quizapp.ms.quiz_service.pojos.Quiz;


@Service
@Transactional
public class QuizServiceImpl implements QuizService{
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	FeignInterface client;
	
	@Override
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Integer> questionIds=client.getQuestionsForQuiz(category, numQ).getBody();
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questionIds);
		quizDao.save(quiz);
		return new ResponseEntity<>("success!", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<QuestionDto>> getQuizQuestions(int id) {
		
		Optional<Quiz> quiz=quizDao.findById(id);
		List<Integer> questionIds=quiz.get().getQuestionIds();
		ResponseEntity<List<QuestionDto>> qList= client.getQuestionsFromId(questionIds);

		return qList;
	}

	@Override
	public ResponseEntity<Integer> submitQuiz(int id,List<QuizAnswerDto> answers) {
		ResponseEntity<Integer> result=client.getScore(answers);
		
		return result;
	}

}
