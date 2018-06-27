package guo.ping.jedis;

import guo.ping.taotao.rest.component.JedisClient;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JedisTest {

    /**
     * 单机版测试
     */
    @Test
    public void testJedis() {
        // 创建一个Jedis对象
        Jedis jedis = new Jedis("192.168.74.128", 6379);
        // 设置连接密码
        jedis.auth("123456");
        jedis.set("single", "hello single redis");
        String str = jedis.get("single");
        System.out.println(str);
        jedis.close();
    }

    /**
     * 使用连接池
     */
    @Test
    public void testJedisPool() {
        // 创建一个连接池对象 (系统中应该是单例的)
        JedisPool jedisPool = new JedisPool("192.168.74.128", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.auth("123456");
        String str = jedis.get("cliet1");
        System.out.println(str);
        // jedis必须关闭
        jedis.close();

        // 系统关闭时关闭连接池
        jedisPool.close();
    }

    /**
     * 连接redis集群
     */
    @Test
    public void testJedisCluster() throws IOException {
        // 创建节点集合
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.74.128", 7001));
        nodes.add(new HostAndPort("192.168.74.128", 7002));
        nodes.add(new HostAndPort("192.168.74.128", 7003));
        nodes.add(new HostAndPort("192.168.74.128", 7004));
        nodes.add(new HostAndPort("192.168.74.128", 7005));
        nodes.add(new HostAndPort("192.168.74.128", 7006));

        // 创建一个JedisCluster对象，在系统中是单例的。
        JedisCluster jedisCluster = new JedisCluster(nodes, 2000, 2000, 5,
                "123456", new GenericObjectPoolConfig());
        jedisCluster.set("cluster", "hello cluster redis");
        String str = jedisCluster.get("cluster");
        System.out.println(str);

        // 系统关闭时关闭jedisCluster
        jedisCluster.close();
    }

    /**
     * 测试spring配置
     * @throws Exception
     */
    @Test
    public void testJedisClientSpring() throws Exception {
        //创建一个spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        //从容器中获得JedisClient对象
        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
        //jedisClient操作redis
        jedisClient.set("cliet1", "1000");
        String string = jedisClient.get("cliet1");
        System.out.println(string);
    }
}
