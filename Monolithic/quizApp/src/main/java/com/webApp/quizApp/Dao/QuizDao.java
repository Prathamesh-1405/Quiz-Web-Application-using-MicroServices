package com.webApp.quizApp.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webApp.quizApp.pojos.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer>{

}
