package com.movement.dao;

import java.util.List;

import com.movement.bussiness.Activity;
import com.movement.bussiness.ActivityAttachment;

public interface ActivityAttachmentDao extends GenericDao<ActivityAttachment, Integer> {

	public List<ActivityAttachment> getByActivity(Activity activity);
	
}
