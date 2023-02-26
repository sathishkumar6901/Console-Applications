package com.zsgs.coursemanagement.trainer;

import java.util.List;

import com.zsgs.coursemanagement.dto.Course;
import com.zsgs.coursemanagement.dto.Trainer;

public interface TrainerModelCallBack {

	List<Course> myCourseList(String trainerId);

	void addSkill(Trainer currTrainer, String[] skills);

}
