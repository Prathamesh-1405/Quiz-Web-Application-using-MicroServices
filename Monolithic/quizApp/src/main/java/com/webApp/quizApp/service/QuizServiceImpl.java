package com.webApp.quizApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webApp.quizApp.DTO.QuestionDto;
import com.webApp.quizApp.DTO.QuizAnswerDto;
import com.webApp.quizApp.Dao.QuestionDao;
import com.webApp.quizApp.Dao.QuizDao;
import com.webApp.quizApp.pojos.Question;
import com.webApp.quizApp.pojos.Quiz;

@Service
@Transactional
public class QuizServiceImpl implements QuizService{
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDao questionDao;
	
	@Override
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		
		List<Question> allQuestions=questionDao.findRandomQuestionsByCategory(category);
		List<Question> randomSubset=allQuestions.stream().limit(numQ).collect(Collectors.toList());
		Quiz quiz =new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(randomSubset);
		quizDao.save(quiz);
		return new ResponseEntity<>("success!", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<QuestionDto>> getQuizQuestions(int id) {
		
		Optional<Quiz> quiz=quizDao.findById(id);
		List<Question> questions=quiz.get().getQuestions();
		List<QuestionDto> qList= new ArrayList<>();
		
		for(Question q: questions) {
			qList.add(new QuestionDto(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4()));
		}
		return new ResponseEntity<>(qList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Integer> submitQuiz(int id,List<QuizAnswerDto> answers) {
		Optional<Quiz> quiz= quizDao.findById(id);
		List<Question> questions = quiz.get().getQuestions();
		
		int marks=0;
		int i=0;
		for(QuizAnswerDto answer: answers) {
			if(answer.getResponse().equals(questions.get(i).getRightAnswer())) {
				marks++;
			}
			i++;
		}
		return new ResponseEntity<>(marks, HttpStatus.OK);
	}

}
