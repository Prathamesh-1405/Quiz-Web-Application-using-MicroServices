package com.quizapp.ms.question_service.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.quizapp.ms.question_service.DTO.QuestionDto;
import com.quizapp.ms.question_service.DTO.QuizAnswerDto;
import com.quizapp.ms.question_service.pojos.Question;


public interface QuestionService {

	ResponseEntity<List<Question>> getAllQuestions();

	ResponseEntity<List<Question>> getQuestionByCategory(String category);

	ResponseEntity<String> addQuestion(Question question);
	ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, int numQuestions);

	ResponseEntity<List<QuestionDto>> getQuestionsFromIds(List<Integer> questionIds);

	ResponseEntity<Integer> getScore(List<QuizAnswerDto> answers);

}
