package com.quizapp.ms.quiz_service.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizapp.ms.quiz_service.pojos.Quiz;


@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer>{

}
