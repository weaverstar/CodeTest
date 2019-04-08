package com.uway.common.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogQueue<T> {

	private static int defaultCount = 20;

	private final BlockingQueue<T> blockingQueue = new LinkedBlockingQueue<T>();
	
	/** 日志对象  */
	private Logger logger = LoggerFactory.getLogger(this.getClass());	
	

	/**
	 * 往队列放置元素
	 * 
	 * @param treeNode
	 * @throws InterruptedException
	 */
	public void put(T treeNode) throws InterruptedException {
		blockingQueue.put(treeNode);
	}
	
	/**
	 * 获取队列数量
	 * @return
	 */
	public  int size() {
		return blockingQueue.size();
	}
	 
	public boolean isEmpty(){
		return blockingQueue.isEmpty();
	}
	
	public T take(){
		return blockingQueue.poll();
	}
	/**
	 * 获取即将批量发送的treeNodes
	 * 
	 * @return
	 */
	public List<T> getTasks() {
		List<T> result = new ArrayList<T>();
		long start = System.nanoTime();
		blockingQueue.drainTo(result, defaultCount);
		logger.info("获取{}条，耗费时间为（毫秒）：{}" , (result == null ? 0:result.size())  , (System.nanoTime() - start) / 10000000 );
		return result;
	}

	public BlockingQueue<T> getBlockingQueue() {
		return blockingQueue;
	}
	
	
}
