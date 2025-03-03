package com.quizapp.ms.question_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quizapp.ms.question_service.DTO.QuestionDto;
import com.quizapp.ms.question_service.DTO.QuizAnswerDto;
import com.quizapp.ms.question_service.Dao.QuestionDao;
import com.quizapp.ms.question_service.pojos.Question;

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
		
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, int numQuestions) {
		List<Integer> questions=questionDao.findRandomQuestionsByCategory(categoryName);
		List<Integer> randNQue= questions.stream().limit(numQuestions).collect(Collectors.toList());
		return new ResponseEntity<>(randNQue, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<QuestionDto>> getQuestionsFromIds(List<Integer> questionIds) {
		List<QuestionDto> questionDtos =new ArrayList<>();
		List<Question> questions=new ArrayList<>();
		for(Integer i: questionIds) {
			questions.add(questionDao.findById(i).get());
		}
		for(Question q : questions) {
			questionDtos.add(new QuestionDto(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4()));
		}
		return new ResponseEntity<>(questionDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Integer> getScore(List<QuizAnswerDto> answers) {
		int marks=0;
		for(QuizAnswerDto answer: answers) {
			Question question =questionDao.findById(answer.getId()).get();
			if(answer.getResponse().equals(question.getRightAnswer())) {
				marks++;
			}
		}
		return new ResponseEntity<>(marks, HttpStatus.OK);
	}
	
	
	
}



