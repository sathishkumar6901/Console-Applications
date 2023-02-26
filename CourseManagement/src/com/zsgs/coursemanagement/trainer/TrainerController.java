package com.zsgs.coursemanagement.trainer;

import java.util.List;

import com.zsgs.coursemanagement.dto.Course;
import com.zsgs.coursemanagement.dto.Trainer;
import com.zsgs.coursemanagement.trainer.TrainerModel.TrainerModelControllerCallBack;

public class TrainerController implements TrainerControllerCallBack, TrainerModelControllerCallBack {
	private TrainerViewCallBack trainerView;
	private TrainerModelCallBack trainerModel;
	
	public TrainerController(TrainerViewCallBack trainerView) {
		this.trainerView = trainerView;
		this.trainerModel = new TrainerModel(this);
	}
	
	public List<Course> myCourseList(String trainerId){
		return trainerModel.myCourseList(trainerId);
	}
	public void addSkill(Trainer currTrainer, String skill){
		String[] skills = skill.split(",");
		trainerModel.addSkill(currTrainer,skills);
	}
	
	/*******************Model to Controller****************/
	public void addSkillSuccess(String message) {
		trainerView.addSkillSuccess(message);
	}
}
