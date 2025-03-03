package com.quizapp.ms.question_service.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuizAnswerDto {
	
	private int id;
	
	private String response;

}
