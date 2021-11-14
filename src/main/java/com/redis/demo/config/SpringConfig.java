package com.redis.demo.config;


import com.redis.demo.dao.Programmer;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.util.StringUtils;

@Configuration
public class SpringConfig {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.jedis.pool.max-total}")
    private int redisPoolMaxtotal;

    @Value("${redis.jedis.pool.max-idle}")
    private int redisPoolMaxIdle;

    @Value("${redis.jedis.pool.min-idle}")
    private int redisPoolMinIdle;

    @Bean
    public JedisClientConfiguration getJedisClientConfiguration(){
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jedisPoolingClientConfigurationBuilder
                = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        GenericObjectPoolConfig genericObjectPoolConfig= new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(redisPoolMaxIdle);
        genericObjectPoolConfig.setMinIdle(redisPoolMinIdle);
        genericObjectPoolConfig.setMaxTotal(redisPoolMaxtotal);
        return jedisPoolingClientConfigurationBuilder.build();
    }
    @Bean
    public JedisConnectionFactory getJedisConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration= new RedisStandaloneConfiguration();

        redisStandaloneConfiguration.setHostName(host);

        if(StringUtils.hasLength(password)){
            redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        }

        redisStandaloneConfiguration.setPort(port);

        return new JedisConnectionFactory(redisStandaloneConfiguration,getJedisClientConfiguration());

    }

    @Bean
    public RedisTemplate getRedisTemplate(){

        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();

        redisTemplate.setConnectionFactory(getJedisConnectionFactory());

        return redisTemplate;
    }

    @Bean
    @Qualifier("listOperations")
    public ListOperations<String, Programmer> getListOfOps(RedisTemplate redisTemplate){

        return redisTemplate.opsForList();

    }


    @Bean
    @Qualifier("setOperations")
    public SetOperations<String, Programmer> getSetOfOps(RedisTemplate redisTemplate){

        return redisTemplate.opsForSet();

    }

    @Bean
    @Qualifier("hashOperations")
    public HashOperations<String, String, Programmer> getHashOfOps(RedisTemplate redisTemplate){

        return redisTemplate.opsForHash();

    }

}
