package com.webApp.quizApp.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.webApp.quizApp.pojos.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{

	List<Question> getByCategory(String category);
	
//	@Query(value="SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery=true)
//	List<Question> findRandomQuestionByCategory(String category, int numQ);
//	@Repository
//	public interface QuestionRepository extends JpaRepository<Question, Long> {

//	    @Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT ?1", 
//	           nativeQuery = true)
//	    List<Question> findRandomQuestions(@Param("category") String category, int numQ);
//	}
	@Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RAND()", nativeQuery = true)
		List<Question> findRandomQuestionsByCategory(String category);




}
