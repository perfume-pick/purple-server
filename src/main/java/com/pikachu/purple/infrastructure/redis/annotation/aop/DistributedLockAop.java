package com.pikachu.purple.infrastructure.redis.annotation.aop;

import com.pikachu.purple.infrastructure.redis.annotation.DistributedLock;
import com.pikachu.purple.infrastructure.redis.util.SpringELParser;
import java.lang.reflect.Method;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

/**
 * @DistributedLock 선언 시 수행되는 Aop class
 */
@Aspect
@Component
@RequiredArgsConstructor
public class DistributedLockAop {

    private static final String REDISSON_LOCK_PREFIX = "LOCK:";

    private final RedissonClient redissonClient;
    private final AopForTransaction aopForTransaction;

    @Around("@annotation(com.pikachu.purple.infrastructure.redis.annotation.DistributedLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);

        String key =
            REDISSON_LOCK_PREFIX + distributedLock.name() + ":" + SpringELParser.getDynamicValue(
                signature.getParameterNames(),
                joinPoint.getArgs(),
                distributedLock.key()
            );
        RLock rLock = redissonClient.getLock(key);

        try {
            boolean available = rLock.tryLock(
                distributedLock.waitTime(),
                distributedLock.leaseTime(),
                distributedLock.timeUnit()
            );
            if (!available) {
                return false;
            }

            try {
                return aopForTransaction.proceed(joinPoint);
            } finally {
                rLock.unlock();
            }

        } catch (InterruptedException e) {
            throw new InterruptedException();
        }
    }

}
