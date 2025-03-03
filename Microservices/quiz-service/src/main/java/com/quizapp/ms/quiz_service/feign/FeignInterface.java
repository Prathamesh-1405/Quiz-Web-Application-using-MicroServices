package com.quizapp.ms.quiz_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.quizapp.ms.quiz_service.DTO.QuestionDto;
import com.quizapp.ms.quiz_service.DTO.QuizAnswerDto;


@FeignClient("Question-Service")
public interface FeignInterface {
	
	@GetMapping("question/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam int numQuestions);
	
	@PostMapping("question/getQuestions")
	public ResponseEntity<List<QuestionDto>> getQuestionsFromId(@RequestBody List<Integer> questionIds);
	
	@PostMapping("question/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<QuizAnswerDto> answers);
}
