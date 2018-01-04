import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    @Test
    public void testJedis(){
//        第一步：创建一个Jedis对象。需要指定服务端的ip及端口。
        Jedis jedis = new Jedis("192.168.25.128",6379);
//        第二步：使用Jedis对象操作数据库，每个redis命令对应一个方法。
        String test = jedis.get("test");
//        第三步：打印结果。
        System.out.println(test);
//        第四步：关闭Jedis
        jedis.close();


    }
}
