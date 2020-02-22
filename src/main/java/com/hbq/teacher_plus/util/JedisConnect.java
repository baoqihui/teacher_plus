package com.hbq.teacher_plus.util;

import redis.clients.jedis.Jedis;

public class JedisConnect {
	public static Jedis Conn(){
		Jedis jedis=new Jedis("120.27.244.176");
		return jedis;
	}
}
