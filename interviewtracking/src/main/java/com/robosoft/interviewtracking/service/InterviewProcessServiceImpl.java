package com.robosoft.interviewtracking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robosoft.interviewtracking.dao.InterviewTrackingRepository;
import com.robosoft.interviewtracking.model.InterviewProcessModel;

@Service
public class InterviewProcessServiceImpl implements InterviewProcessService{
	@Autowired
	InterviewTrackingRepository intrepo;

	@Override
	public List<Integer> add() {
		//List<Integer> idList = intrepo.getId();
		int[] idList = intrepo.getId();
		for(int id = 0; id<idList.length; id++)
		{
			InterviewProcessModel intmodel = new InterviewProcessModel();
			intmodel.setCandidateId(idList[id]);
			System.out.println(intmodel);
			intrepo.save(intmodel);
		}
		
		return null;
	}

	
	
	
}