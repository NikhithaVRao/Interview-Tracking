package com.robosoft.interviewtracking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robosoft.interviewtracking.model.CommentModel;

public interface CommentsRepository extends JpaRepository<CommentModel, Integer>{

}
