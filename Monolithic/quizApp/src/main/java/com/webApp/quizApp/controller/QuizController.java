package com.webApp.quizApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.webApp.quizApp.DTO.QuestionDto;
import com.webApp.quizApp.DTO.QuizAnswerDto;
import com.webApp.quizApp.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
		
		return quizService.createQuiz(category, numQ, title);
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
