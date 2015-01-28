package com.movement.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.util.PublicHelper;

@Service
@Transactional
public class FileService {

	public String saveImage(String name,String category,String iid,InputStream upImg){
		
		String image;
		try {
			image = PublicHelper.saveImage(upImg,category,iid,name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			image = null;
			e.printStackTrace();
		}
		
		return image;
		
	}
	
	public void deleteImage(String name,String category,String iid){
		
		PublicHelper.deleteImage(category, iid, name);
		
	}
	
}
