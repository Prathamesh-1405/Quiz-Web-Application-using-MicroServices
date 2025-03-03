package com.quizapp.ms.quiz_service.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.quizapp.ms.quiz_service.DTO.QuestionDto;
import com.quizapp.ms.quiz_service.DTO.QuizAnswerDto;



public interface QuizService {

	ResponseEntity<String> createQuiz(String category, int numQ, String title);

	ResponseEntity<List<QuestionDto>> getQuizQuestions(int id);

	ResponseEntity<Integer> submitQuiz(int id, List<QuizAnswerDto> answers);

}
