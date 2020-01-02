package com.boot;


import com.boot.base.BaseJunit;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * redis主从测试
 * @author TANGXIAN
 */
public class RedisTest extends BaseJunit {
    @Test
    public void testMasterSlaver() {
        Jedis jedis = new Jedis("localhost", 6379);
        System.out.println(jedis.info("Replication"));
        jedis = new Jedis("localhost", 6380);
        System.out.println(jedis.info("Replication"));
        jedis = new Jedis("localhost", 6381);
        System.out.println(jedis.info("Replication"));
        jedis = new Jedis("localhost", 26379);
        System.out.println(jedis.info("sentinel"));
        jedis = new Jedis("localhost", 26479);
        System.out.println(jedis.info("sentinel"));
        jedis = new Jedis("localhost", 26579);
        System.out.println(jedis.info("sentinel"));
    }
}
