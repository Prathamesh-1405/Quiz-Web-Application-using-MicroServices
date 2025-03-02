package com.webApp.quizApp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.webApp.quizApp.pojos.Question;

public interface QuestionService {

	ResponseEntity<List<Question>> getAllQuestions();

	ResponseEntity<List<Question>> getQuestionByCategory(String category);

	ResponseEntity<String> addQuestion(Question question);

}
