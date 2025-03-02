package com.webApp.quizApp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.webApp.quizApp.DTO.QuestionDto;
import com.webApp.quizApp.DTO.QuizAnswerDto;

public interface QuizService {

	ResponseEntity<String> createQuiz(String category, int numQ, String title);

	ResponseEntity<List<QuestionDto>> getQuizQuestions(int id);

	ResponseEntity<Integer> submitQuiz(int id, List<QuizAnswerDto> answers);

}
