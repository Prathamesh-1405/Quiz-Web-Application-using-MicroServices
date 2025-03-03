package com.quizapp.ms.question_service.pojos;

import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Data
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String questionTitle;
	
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String category;
	private String rightAnswer;
	
	private String difficultyLevel;

}
