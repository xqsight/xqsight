package com.xqsight.sso.shiro.cache;///**
//package com.xqsight.sso.shiro.cache;
//
//import java.io.Serializable;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.session.UnknownSessionException;
//import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//
//public class RedisSessionDAO extends AbstractSessionDAO {
//
//    private Logger                   logger = LogManager.getLogger(getClass());
//    /**
//     * shiro-redis的session对象前缀
//     */
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    /**
//     * The Redis key prefix for the sessions 
//     */
//    private String keyPrefix = "sro-s:";
//
//    @Override
//    public void update(Session session) throws UnknownSessionException {
//        this.saveSession(session);
//    }
//
//    /**
//     * save session
//     * @param session
//     * @throws UnknownSessionException
//     */
//    private void saveSession(Session session) throws UnknownSessionException{
//        if(session == null || session.getId() == null){
//            logger.error("session or session id is null");
//            return;
//        }
//        long timeoutMilli = getTimeout();
//        session.setTimeout(timeoutMilli);      
//        this.redisTemplate.opsForValue().set(getRedisKey(session.getId()), session, timeoutMilli, TimeUnit.MILLISECONDS);
//    }
//
//    @Override
//    public void delete(Session session) {
//        if (session == null || session.getId() == null) {
//            logger.error("session or session id is null");
//            return;
//        }
//        redisTemplate.delete(this.getRedisKey(session.getId()));
//
//    }
//
//    @Override
//    public Collection<Session> getActiveSessions() {
//        Set<Session> sessions = new HashSet<Session>();
//
//        Set<String> keys = redisTemplate.keys(this.keyPrefix + "*");
//        if (keys != null && keys.size() > 0) {
//            for (String key : keys) {
//                Session s ;
//                try {
//                    s = (Session) redisTemplate.opsForValue().get(key);
//                    sessions.add(s);
//                } catch (Exception e) {
//                    logger.error("从Redis中读取Session失败", e);
//                }
//            }
//        }
//
//        return sessions;
//    }
//
//    @Override
//    protected Serializable doCreate(Session session) {
//        Serializable sessionId = this.generateSessionId(session);
//        this.assignSessionId(session, sessionId);
//        this.saveSession(session);
//        return sessionId;
//    }
//
//    @Override
//    protected Session doReadSession(Serializable sessionId) {
//        if (sessionId == null) {
//            logger.error("session id is null");
//            return null;
//        }
//
//        Session s;
//        try {
//            s = (Session) redisTemplate.opsForValue().get(this.getRedisKey(sessionId));
//            return s;
//        } catch (Exception e) {
//            logger.error("从Redis中读取Session失败", e);
//        }
//        return null;
//    }
//
//    /**
//     * 通过{@link ShiroConstants#REDIS_TIMEOUT_KEY REDIS_TIMEOUT_KEY}从Redis中获取Shrio Session超时时间，若获取不到，则使用默认时间{@linkplain ShiroConstants#SHIRO_DEFAULT_EXPIRE_MILLISEC SHIRO_DEFAULT_EXPIRE_MILLISEC}
//     * @return
//     */
//    private Long getTimeout() {
//         Long timeout = null;
//         try {
//             timeout = (Long) redisTemplate.opsForValue().get(ShiroConstants.REDIS_TIMEOUT_KEY);
//             logger.trace("get timeout from redis, timeout={}", timeout);
//        } catch (Exception e) {
//            logger.error("获取Shrio Session超时时间失败", e);
//        }
//         return timeout == null ? ShiroConstants.SHIRO_DEFAULT_EXPIRE_MILLISEC : timeout;
//    }
//
//    private String getRedisKey(Serializable sessionId) {
//        return this.keyPrefix + sessionId;
//    }
//
//    /**
//     * Returns the Redis session keys
//     * prefix.
//     * @return The prefix
//     */
//    public String getKeyPrefix() {
//        return keyPrefix;
//    }
//
//    /**
//     * Sets the Redis sessions key 
//     * prefix.
//     * @param keyPrefix The prefix
//     */
//    public void setKeyPrefix(String keyPrefix) {
//        this.keyPrefix = keyPrefix;
//    }
//
//}
