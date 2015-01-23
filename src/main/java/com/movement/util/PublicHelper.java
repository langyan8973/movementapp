package com.movement.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.movement.bussiness.MoneyOfInterval;
import com.sina.sae.storage.SaeStorage;
import com.sina.sae.util.SaeUserInfo;

public class PublicHelper {
	static Logger logger = Logger.getLogger(PublicHelper.class.getName());
	public static String encryptPassword(Integer uid, String password) {
		return DigestUtils.sha1Hex(StringUtils.join(new String[] {
				uid.toString(), password }));
	}

	public static String encryptUser(Integer uid, String password, Integer utype) {
		String token = encryptPassword(uid, password);
		return StringUtils.join(
				new String[] { uid.toString(), token, utype.toString() }, '|');
	}

	public static String saveImage(InputStream upImg,String catgory,String iid, String oldName)
			throws IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[16384];
		while ((nRead = upImg.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}
		buffer.flush();
		byte[] bs = buffer.toByteArray();

		if (bs.length > 0) {
			String id = UUID.randomUUID().toString();
			String image = id + ".jpg";

			BufferedImage bi = ImageIO.read(new ByteArrayInputStream(bs));

			String tmpPath;
			if (PublicConfig.isLocal()) {
				tmpPath = System.getProperty("java.io.tmpdir");
			} else {
				tmpPath = SaeUserInfo.getSaeTmpPath() + "/";
			}

			File originFile = new File(tmpPath + image);
			if (originFile.isDirectory()) {
				ImageIO.write(bi, "jpg", originFile);
			} else {
				originFile.mkdirs();
				ImageIO.write(bi, "jpg", originFile);
			}

			// 生成不同规格的图片
			File smallFile = saveScaledImage(bi, 100, 75, tmpPath + "small"
					+ image);

			// 将图片保存
			if (PublicConfig.isLocal()) {
				File oFile = new File(PublicConfig.getImagePath()+catgory+File.separator+iid+File.separator
						+ "origin" + File.separator);
				if(!oFile.isDirectory()){
					oFile.mkdirs();
				}
				File sFile = new File(PublicConfig.getImagePath()+catgory+File.separator+iid+File.separator
						+ "small" + File.separator);
				if(!sFile.isDirectory()){
					sFile.mkdirs();
				}
				originFile.renameTo(new File(oFile, image));
				smallFile.renameTo(new File(sFile,image));

				if (!StringUtils.isBlank(oldName)) {
					File oldoriginFile = new File(oFile, oldName);
					File oldsmallFile = new File(sFile, oldName);
					
					if (oldoriginFile.isFile() && oldoriginFile.exists()) {
						oldoriginFile.delete();
					}
					if (oldsmallFile.isFile() && oldsmallFile.exists()) {
						oldsmallFile.delete();
					}
				}
				
//				originFile.renameTo(new File(PublicConfig.getImagePath()
//						+ "origin\\", image));
//				smallFile.renameTo(new File(PublicConfig.getImagePath()
//						+ "small\\", image));
//
//				if (!StringUtils.isBlank(oldName)) {
//					File oldoriginFile = new File(PublicConfig.getImagePath()
//							+ "origin\\", oldName);
//					File oldsmallFile = new File(PublicConfig.getImagePath()
//							+ "small\\", oldName);
//					
//					if (oldoriginFile.isFile() && oldoriginFile.exists()) {
//						oldoriginFile.delete();
//					}
//					if (oldsmallFile.isFile() && oldsmallFile.exists()) {
//						oldsmallFile.delete();
//					}
//				}

			} else {
				SaeStorage ss = new SaeStorage();
				ss.upload("menuimages", originFile.getAbsolutePath(), "origin/"
						+ originFile.getName());
				ss.upload("menuimages", smallFile.getAbsolutePath(), "small/"
						+ originFile.getName());

				if (!StringUtils.isBlank(oldName)) {
					if (ss.fileExists("menuimages", "origin/" + oldName)) {
						ss.delete("menuimages", "origin/" + oldName);
					}
					if (ss.fileExists("menuimages", "small/" + oldName)) {
						ss.delete("menuimages", "small/" + oldName);
					}
				}
			}

			return image;
		}
		return null;
	}
	
	public static void deleteImage(String catgory,String iid,String oldName){
		if(PublicConfig.isLocal()){
			if (!StringUtils.isBlank(oldName)) {
				File oldoriginFile = new File(PublicConfig.getImagePath()+catgory+File.separator+iid+File.separator
						+ "origin" + File.separator, oldName);
				File oldsmallFile = new File(PublicConfig.getImagePath()+catgory+File.separator+iid+File.separator
						+ "small" + File.separator, oldName);
				
				if (oldoriginFile.isFile() && oldoriginFile.exists()) {
					oldoriginFile.delete();
				}
				if (oldsmallFile.isFile() && oldsmallFile.exists()) {
					oldsmallFile.delete();
				}
			}
		}
		else{
			if (!StringUtils.isBlank(oldName)) {
				SaeStorage ss = new SaeStorage();
				if (ss.fileExists("menuimages", "origin/" + oldName)) {
					ss.delete("menuimages", "origin/" + oldName);
				}
				if (ss.fileExists("menuimages", "small/" + oldName)) {
					ss.delete("menuimages", "small/" + oldName);
				}
			}
		}
	}

	private static File saveScaledImage(BufferedImage bi, int width,
			int height, String filePath) throws IOException {
		Image bigImage = bi.getScaledInstance(width, height,
				BufferedImage.SCALE_SMOOTH);
		BufferedImage big = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		big.createGraphics().drawImage(bigImage, 0, 0, null);
		File bigFile = new File(filePath);
		ImageIO.write(big, "png", bigFile);
		return bigFile;
	}
	
	public static HashMap<String, MoneyOfInterval> getStatisticsMap(Date startDate,Date endDate){
		GregorianCalendar gc=new GregorianCalendar(); 
		gc.setTime(startDate); 
		HashMap<String, MoneyOfInterval> maps = new HashMap<String, MoneyOfInterval>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		while (endDate.after(gc.getTime())) {			
			String timekey = format.format(gc.getTime());
			MoneyOfInterval md = new MoneyOfInterval();
			md.setDayDate(format.format(gc.getTime()));
			md.setMoney(0);
			maps.put(timekey, md);
			gc.add(5, 1);
		}
		return maps;
	}
	
	public static HashMap<String, MoneyOfInterval> getMStatisticsMap(Date startDate,Date endDate){
		GregorianCalendar gc=new GregorianCalendar(); 
		gc.setTime(startDate); 
		HashMap<String, MoneyOfInterval> maps = new HashMap<String, MoneyOfInterval>();
		DateFormat format = new SimpleDateFormat("yyyy-MM");
		while (endDate.after(gc.getTime())) {			
			String timekey = format.format(gc.getTime());
			MoneyOfInterval md = new MoneyOfInterval();
			md.setDayDate(timekey);
			md.setMoney(0);
			maps.put(timekey, md);
			gc.add(2, 1);
		}
		return maps;
	}
	
	public static String convertDatestringFormat(String dateString){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
		String dString = "";
		try {
			Date date = format.parse(dateString);
			dString = format2.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dString;
	}

	public static String convertYearstringFormat(String dateString){
		DateFormat format = new SimpleDateFormat("yyyy-MM");
		DateFormat format2 = new SimpleDateFormat("yyyy");
		String dString = "";
		try {
			Date date = format.parse(dateString);
			dString = format2.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dString;
	}
}