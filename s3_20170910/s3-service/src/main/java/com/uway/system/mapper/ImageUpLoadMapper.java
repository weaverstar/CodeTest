package com.uway.system.mapper;

import java.util.List;

import com.uway.system.entity.ImageUpLoad;


public interface ImageUpLoadMapper{
	/**
	 * 保存图片信息
	 * @param imageUpLoad
	 */
	public void persist(ImageUpLoad imageUpLoad);
	
	/**
	 * 修改图片描述信息
	 * @param imageUpLoad
	 * @return
	 */
	
	public int updateImageDescById(ImageUpLoad imageUpLoad);
	
	/**
	 * 删除图片
	 * @param imageUpLoad
	 * @return
	 */
	public int removeById(ImageUpLoad imageUpLoad);
	
	/**
	 * 查找图片信息
	 * @param imageUpLoad
	 * @return
	 */
	public ImageUpLoad findById(ImageUpLoad imageUpLoad);
	
	/**
	 * 根据外键查找多张图片信息
	 * @param imageUpLoad
	 * @return
	 */
	public List<ImageUpLoad> findImagesByFkId(ImageUpLoad imageUpLoad);
	
	
}
