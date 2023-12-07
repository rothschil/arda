// package io.github.rothschil.common.config.cache.aspect;
//
//
// import cn.hutool.core.bean.BeanPath;
// import cn.hutool.core.util.ObjectUtil;
// import cn.hutool.core.util.StrUtil;
// import com.alibaba.fastjson.JSON;
// import com.alibaba.fastjson.JSONObject;
// import com.github.benmanes.caffeine.cache.Cache;
// import com.google.common.collect.Lists;
// import io.github.rothschil.common.config.cache.annotation.Cacheable;
// import io.github.rothschil.common.handler.AopSpelProcess;
// import io.github.rothschil.common.utils.NativeUtil;
// import io.github.rothschil.common.utils.ToolUtils;
// import lombok.extern.slf4j.Slf4j;
// import org.apache.commons.lang3.StringUtils;
// import org.aspectj.lang.ProceedingJoinPoint;
// import org.aspectj.lang.annotation.Around;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Pointcut;
// import org.aspectj.lang.reflect.MethodSignature;
// import org.redisson.api.RedissonClient;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.cache.annotation.CacheEvict;
// import org.springframework.cache.annotation.CachePut;
// import org.springframework.scheduling.annotation.Async;
// import org.springframework.stereotype.Component;
//
// import java.lang.reflect.Method;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.stream.Collectors;
//
// /**
//  * 缓存查询注解，详情请查看 {@link CacheAspect}<br/>
//  *
//  * 1、修订内容有支持三木运算，目前只能支持到二级运算，对 value 做了更进一步的支持</br>
//  * 用法有三种：
//  * <ul>
//  * <li>a)、直接使用字符串 <b>KEY</b></li>
//  * <li>b)、使用 <b>示例.属性名</b> 如：dataConfig.areacode</li>
//  * <li>c)、使用 <b>{#示例.属性名}</b> 如：{#dataConfig.areacode}</li>
//  * </ul><br/>
//  * 2、新增 Caffeine 作为一级缓存，替代原先 Redis/TeleCache 在框架中的作用，并将Redis/TeleCache作用属性下沉为系统架构的二级缓存。
//  * Cacheable 中 enableCaffeine 设置为 true，开启，默认为 关闭 false 具体使用如下：<br/>
//  * <span>
//  *
//  *     @Cacheable(prefix = "DataConfigServiceImpl:getDataConfigByDataName",value = "dataName",ttl = 2,enableCaffeine = true)
//  * </span>
//  */
// @Slf4j
// @Aspect
// @Component
// public class CacheAspect {
//
//     @Autowired
//     private RedissonClient redissonClient;
//
//     @Value("${rothschil.cache.prefix}")
//     private String cachePrefix;
//
//     @Autowired
//     Cache<String, Object> caffeineCache;
//
//     @Autowired
//     private AopSpelProcess aopSpelProcess;
//
//     @Pointcut("@annotation(io.github.rothschil.common.config.cache.annotation.Cacheable) && execution(* io.github.rothschil..*.*(..)))")
//     public void cacheablePointcut() {
//     }
//
//
//     @Around("cacheablePointcut()")
//     public Object cacheableAround(ProceedingJoinPoint point) throws Throwable {
//         MethodSignature methodSignature = (MethodSignature) point.getSignature();
//         Method method = methodSignature.getMethod();
//         Cacheable cacheable = method.getAnnotation(Cacheable.class);
//         //缓存key获取
//         String key = getKey(point);
//         Object result = null;
//
//         if (cacheable.enableCaffeine()){
//             result =caffeineCache.getIfPresent(key);
//             // result = caffeineCache.asMap().get(key);
//             log.info("[hit CaffeineCache] {}",result);
//         }
//         if (null==result) {
//             result = aspectHandler(point,key,method);
//         }
//         return result;
//     }
//
//     protected Object aspectHandler(ProceedingJoinPoint point,String key,Method method){
//         MethodSignature methodSignature = (MethodSignature) point.getSignature();
//         String className = point.getTarget().getClass().getName();
//         Cacheable cacheable = method.getAnnotation(Cacheable.class);
//         Object result = null;
//         redissonClient.set
//         String obj = redisRepository.get(key);
//         long pttl = redisRepository.pttl(key);
//         if(!StrUtil.isEmpty(obj)){
//             try {
//                 Object parse = JSON.parse(obj);
//                 result = JSONObject.parseObject(parse.toString(), methodSignature.getReturnType());
//                 String resultStr = null;
//                 if (null!=result) {
//                     resultStr = JSONObject.toJSONString(result);
//                 }
//                 if (!StrUtil.isEmpty(resultStr)) {
//                     resultStr = resultStr.substring(0, Math.min(resultStr.length(), 200)) + (resultStr.length() > 200 ? "..." : "");
//                 }
//                 log.warn("[Hit Cache Class=Method] [Key] {}={}\n{}={},ttl={}ms", className,method.getName(),key, resultStr, pttl);
//                 if (cacheable.enableCaffeine()){
//                     // 命中缓存重新刷新到本地站点
//                     caffeineCache.put(key,result);
//                 }
//                 // break;
//             } catch (Exception e) {
//                 log.error("error json str = {}", obj);
//                 // break;
//             }
//         } else{
//             try {
//                 result = point.proceed();
//                 String resultStr = null;
//                 if (null!=result) {
//                     resultStr = JSONObject.toJSONString(result);
//                 }
//                 if (!StrUtil.isEmpty(resultStr)) {
//                     resultStr = resultStr.substring(0, Math.min(resultStr.length(), 200)) + (resultStr.length() > 200 ? "..." : "");
//                 }
//                 log.warn("[Save Cache Class=Method] [Key] {}={}\n{}={},ttl={}ms", className,method.getName(),key, resultStr, pttl);
//                 saveCache(cacheable, key, result);
//                 // break;
//             } catch (Throwable e) {
//                 log.error("[Enhancement mode] msg={}",e.getMessage());
//                 // break;
//             }
//         }
//         // try {
//         //     Thread.sleep(100);
//         // } catch (Exception e) {
//         //     log.error("[key] ={},Exception Msg = {}",key,e.getMessage());
//         // }
//         return result;
//     }
//
//     @Async
//     protected void saveCache(Cacheable annotation, String key, Object result) {
//         String rt = null;
//         try {
//             rt = redisRepository.setEx(key, JSONObject.toJSONString(result), ToolUtils.getTTL(annotation.ttl()));
//             log.info("[Enhancement mode] key={} Storge Status{}", key,rt);
//         } catch (Exception e) {
//             log.error("[Enhancement Exception] key={} Storge Status{}", key,rt);
//         }
//
//         if (annotation.enableCaffeine()){
//             caffeineCache.put(key,result);
//         }
//     }
//
//
//     @Deprecated
//     @Around("cachePutPointcut()")
//     public Object cachePutAround(ProceedingJoinPoint point) throws Throwable {
//         MethodSignature methodSignature = (MethodSignature) point.getSignature();
//         //缓存注解获取
//         CachePut cachePut = methodSignature.getMethod().getAnnotation(CachePut.class);
//         //缓存key获取
//         String key = getKey(point);
//         //缓存覆盖
//         Object result = point.proceed();
//         if (!ObjectUtil.isEmpty(result)) {
//             redisRepository.set(key, JSONObject.toJSONString(result));
//             if (-1 != cachePut.ttl()) {
//                 redisRepository.expire(key, ToolUtils.getTTL(cachePut.ttl()));
//             }
//         }
//         return result;
//     }
//
//     @Deprecated
//     @Around("cacheEvictPointcut()")
//     public Object cacheEvictAround(ProceedingJoinPoint point) throws Throwable {
//         MethodSignature methodSignature = (MethodSignature) point.getSignature();
//         //缓存注解获取
//         CacheEvict cacheEvict = methodSignature.getMethod().getAnnotation(CacheEvict.class);
//         //缓存key获取
//         String key = getKey(point);
//         //缓存删除
//         Object result = point.proceed();
//         redisRepository.del(key);
//         return result;
//     }
//
//     /** 获取缓存key
//      * @author <a href="mailto:WCNGS@QQ.COM">Sam</a>
//      * @param point
//      * @return String
//      **/
//     public String getKey(ProceedingJoinPoint point) {
//         MethodSignature methodSignature = (MethodSignature) point.getSignature();
//         Method method = methodSignature.getMethod();
//         boolean flag = false;
//         //缓存key获取
//         String key = null;
//         //缓存key表达式、前缀获取
//         String keyExpression = null;
//         String keyPrefix = "";
//         String pattern = "^\\{#\\w+\\.\\w+\\}$";
//         //获取Cacheable注解缓存key表达式、前缀
//         if (method.isAnnotationPresent(Cacheable.class)) {
//             Cacheable cacheable = method.getAnnotation(Cacheable.class);
//             String content = ToolUtils.buildContent(cacheable);
//             if (ToolUtils.operation(content, pattern)) {
//                 flag = true;
//             }
//             String value = getAnnnotationValue(cacheable, point);
//             keyExpression = !StrUtil.isEmpty(cacheable.key()) ? cacheable.key() : value;
//             keyPrefix = cacheable.prefix();
//         }
//
//         //缓存key缺省为方法签名
//         if (StrUtil.isEmpty(keyExpression) || point.getArgs().length == 0) {
//             key = methodSignature.toString();
//         } else {
//             //解析缓存key表达式生成缓存key
//             //方法参数对象构建
//             if (!flag) {
//                 Map<String, Object> paramMap = new HashMap<>();
//                 for (int i = 0; i < point.getArgs().length; i++) {
//                     String parameter = methodSignature.getParameterNames()[i];
//                     Object arg = point.getArgs()[i];
//                     paramMap.put(parameter, arg);
//                 }
//                 //根据方法参数对象解析缓存key表达式生成缓存key
//                 BeanPath beanPathResolver = new BeanPath(keyExpression);
//                 try {
//                     key = beanPathResolver.get(paramMap).toString();
//                 } catch (NullPointerException e) {
//                     log.error("[空指针异常 ] Params is {}", paramMap);
//                 }
//             } else {
//                 key = keyExpression;
//             }
//         }
//         //前缀缺省为方法名
//         if (StrUtil.isEmpty(keyPrefix)) {
//             keyPrefix = methodSignature.getMethod().getName();
//         }
//         //拼接前缀
//         key = cachePrefix + ":"+ NativeUtil.judgmentEnv().getCode()+":" + keyPrefix + ":" + key;
//         return key;
//     }
//
//     /** 获取存在Spel表达式的属性
//      * @author <a href="mailto:WCNGS@QQ.COM">Sam</a>
//      * @param annotation
//      * @param joinPoint
//      * @return String
//      **/
//     protected String getAnnnotationValue(Cacheable annotation, ProceedingJoinPoint joinPoint) {
//         String content = ToolUtils.buildContent(annotation);
//         List<String> templates = Lists.newArrayList(content);
//         templates = templates.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
//         HashMap<String, String> processMap = aopSpelProcess.processBeforeExec(templates, joinPoint);
//         return processMap.get(content);
//     }
//
// }
