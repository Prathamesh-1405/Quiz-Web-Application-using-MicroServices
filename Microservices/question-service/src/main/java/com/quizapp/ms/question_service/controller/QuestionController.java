package com.quizapp.ms.question_service.controller;

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

import com.quizapp.ms.question_service.DTO.QuestionDto;
import com.quizapp.ms.question_service.DTO.QuizAnswerDto;
import com.quizapp.ms.question_service.pojos.Question;
import com.quizapp.ms.question_service.service.QuestionService;


@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionService.getAllQuestions();
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
		return questionService.getQuestionByCategory(category);
	}
	
	@PostMapping("addQuestion")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}
	
	//generate	
	//getQuestion(question Id)
	//getScore
		
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz
			(@RequestParam String categoryName, @RequestParam int numQuestions){
		return questionService.getQuestionsForQuiz(categoryName, numQuestions);
	}
	
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionDto>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
		return questionService.getQuestionsFromIds(questionIds);
	}
	
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<QuizAnswerDto> answers){
		return questionService.getScore(answers);
	}

}
