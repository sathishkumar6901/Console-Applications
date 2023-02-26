package com.zsgs.coursemanagement.trainer;

import java.util.List;

import com.zsgs.coursemanagement.dto.Course;
import com.zsgs.coursemanagement.dto.Trainer;
import com.zsgs.coursemanagement.repository.CourseRepository;

public class TrainerModel implements TrainerModelCallBack {
	private TrainerModelControllerCallBack trainerController;
	
	public TrainerModel(TrainerModelControllerCallBack trainerController){
		this.trainerController = trainerController;
	}
	
	public List<Course> myCourseList(String trainerId){
		return CourseRepository.getInstance().getTrainerCourse(trainerId);
	}
	public void addSkill(Trainer currTrainer, String[] skills) {
		CourseRepository.getInstance().addSkills(currTrainer,skills);
		trainerController.addSkillSuccess(currTrainer.getTrainerName()+", Your skill added successfully");
	}
	public interface TrainerModelControllerCallBack{

		void addSkillSuccess(String message);
		
	}
}
