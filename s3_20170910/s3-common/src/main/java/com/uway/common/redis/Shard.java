package com.uway.common.redis;


import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Shard implements Serializable{ 
  
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 7822859078695269210L;
	public static  final int NODE_NUM = 1000; // 每个机器节点关联的虚拟节点个数  
    public long maxNum;
    public int currentNode = 1; //当前是第几个节点
    public int currentNum = 0; //当前节点的数量
    

  
    public Shard(long maxNum){
    	this.maxNum = maxNum;
    }
  
    public  synchronized  int  getShardInfo() {  
    	currentNum++;
    	if(currentNum > NODE_NUM){
    		currentNum = 1;
    		currentNode++;
    	}
    	return currentNode;
    }  
  
    
    /** 
     *  MurMurHash算法，是非加密HASH算法，性能很高， 
     *  比传统的CRC32,MD5，SHA-1（这两个算法都是加密HASH算法，复杂度本身就很高，带来的性能上的损害也不可避免） 
     *  等HASH算法要快很多，而且据说这个算法的碰撞率很低. 
     *  http://murmurhash.googlepages.com/ 
     */  
    private Long hash(String key) {  
        ByteBuffer buf = ByteBuffer.wrap(key.getBytes());  
        int seed = 0x1234ABCD;  
        ByteOrder byteOrder = buf.order();  
        buf.order(ByteOrder.LITTLE_ENDIAN);  
        long m = 0xc6a4a7935bd1e995L;  
        int r = 47;  
        long h = seed ^ (buf.remaining() * m);  
        long k;  
        while (buf.remaining() >= 8) {  
            k = buf.getLong();  
            k *= m;  
            k ^= k >>> r;  
            k *= m;  
            h ^= k;  
            h *= m;  
        }  
  
        if (buf.remaining() > 0) {  
            ByteBuffer finish = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);  
            finish.put(buf).rewind();  
            h ^= finish.getLong();  
            h *= m;  
        }  
  
        h ^= h >>> r;  
        h *= m;  
        h ^= h >>> r;  
  
        buf.order(byteOrder);  
        return h;  
    }  
    
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
    /*	Long startTime = System.currentTimeMillis();
    	Shard obj = new Shard(1000L);
    	hash("1");*/
    	//System.out.println(obj.getShardInfo("114391711743323594"));
    	/*for(int i =0; i < 1000; i++){
    		System.out.println(obj.getShardInfo("adsfsdf"+i));
    	}
    	System.out.println(System.currentTimeMillis() - startTime);
    	Map<Integer, Integer> bb = allotOfRandom(60,50);
    	System.out.println(JSON.toJSONString(bb));*/

	}
  
}  