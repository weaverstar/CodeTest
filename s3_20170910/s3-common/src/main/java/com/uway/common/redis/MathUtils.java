package com.uway.common.redis;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;

public class MathUtils {

	/* 
     * 随机分配 
     * @param num 分布的数量
     * @param max 总量
     * key 秒数
     * value 个数
     */  
    public static Map<Integer, Integer> allotOfRandom(int num, int max ){  
    	Map<Integer, Integer> allot=new ConcurrentHashMap<Integer, Integer>(); //保存分配的信息  
        if(num > 0 && max >0){  
            for(int i=0;i< max;i++){  
                    int r_user = new Random().nextInt(num);  
                    if(allot.containsKey(r_user)){  
                    	Integer sum = allot.get(r_user);  
                        sum++;
                        allot.put(r_user, sum);  
                    }else{  
                        allot.put(r_user, 1);  
                    }  
            }  
        }  
        return allot;  
    }  
    
    public static void main(String[] args) {
		System.out.println(JSON.toJSON(allotOfRandom(6, 30)));
	}
}
