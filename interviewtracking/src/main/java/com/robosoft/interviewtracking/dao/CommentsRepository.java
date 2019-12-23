package com.robosoft.interviewtracking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.robosoft.interviewtracking.model.CommentModel;

public interface CommentsRepository extends JpaRepository<CommentModel, Integer>{
	@Query("select c from CommentModel c where c.interviewId = :interviewId and c.interviewId in (select i.interviewId from InterviewProcessModel i where c.round = i.round and i.status = :status)")
	CommentModel findByInterviewId(@Param("interviewId") String interviewId, @Param("status") boolean status);

}
