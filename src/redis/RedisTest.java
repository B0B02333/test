package redis;

import redis.clients.jedis.Jedis;

public class RedisTest {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.212.113", 6379);
		jedis.set("foo", "bar");
		String value = jedis.get("test");
		System.out.println(value);

	}

}
