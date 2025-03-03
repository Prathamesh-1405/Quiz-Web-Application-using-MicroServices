package com.quizapp.ms.quiz_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.ms.quiz_service.DTO.QuestionDto;
import com.quizapp.ms.quiz_service.DTO.QuizAnswerDto;
import com.quizapp.ms.quiz_service.DTO.QuizDto;
import com.quizapp.ms.quiz_service.service.QuizService;


@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
		
		return quizService.createQuiz(quizDto.getCategory(), quizDto.getNumQuestions(), quizDto.getTitle());
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionDto>> getQuizQuestions(@PathVariable int id){
		return quizService.getQuizQuestions(id);
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<QuizAnswerDto> answers){
		return quizService.submitQuiz(id, answers);
	}

}
