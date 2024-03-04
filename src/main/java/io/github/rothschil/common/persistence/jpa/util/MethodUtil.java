package io.github.rothschil.common.persistence.jpa.util;

import io.github.rothschil.common.constant.Constant;
import io.github.rothschil.common.persistence.jpa.entity.AbstractPo;
import io.github.rothschil.common.utils.DateUtils;
import io.github.rothschil.common.utils.StringUtils;
import jakarta.persistence.Transient;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @since 1.0.0
 */
@Slf4j
public class MethodUtil<T extends AbstractPo<ID>, ID extends Serializable> {


    /**
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param po
     * @return Specification
     **/
    public static Specification getSpecification(AbstractPo po){
        return (root, query, cb) -> {
            List<Predicate> list = MethodUtil.getFieldValue(po,root,cb);
            Predicate[] pre = new Predicate[list.size()];
            pre = list.toArray(pre);
            CriteriaQuery<?> quy = query.where(pre);
            return quy.getRestriction();
        };
    }

    /**
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param entity    实体基类
     * @param root  root
     * @param cb    CriteriaBuilder
     * @return Predicate>
     **/
    public static List<Predicate> getFieldValue(AbstractPo entity, Root<?> root, CriteriaBuilder cb) {

        List<Predicate> lp = new ArrayList<>();
        Class<?> cls = entity.getClass();
        Field[] fields = cls.getDeclaredFields();
        Method[] methods = cls.getDeclaredMethods();
        Predicate predicate;
        for (Field field : fields) {
            try {
                //校验是否有GETTER、SETTER的方法
                if(!checkGetSetMethodAndAnnotation(methods,field)){
                    continue;
                }
                String fieldGetName = StringUtils.parGetName(field.getName());

                Method fieldSetMet = cls.getMethod(fieldGetName);

                Object o = fieldSetMet.invoke(entity);
                //Type conversion
                if (null == o) {
                    continue;
                }
                String fieldType = field.getType().getSimpleName();
                String value = o.toString();
                switch (fieldType) {
                    case Constant.BASIC_TYPE_INTEGER:
                    case Constant.BASIC_TYPE_INT:
                        predicate = doInteger(cb, root, field, value);
                        break;
                    case Constant.BASIC_TYPE_BIG_DECIMAL:
                        predicate = cb.equal(root.get(field.getName()).as(BigDecimal.class), new BigDecimal(value));
                        break;
                    case Constant.BASIC_TYPE_LONG:
                        predicate = cb.equal(root.get(field.getName()).as(Long.class), Long.valueOf(value));
                        break;
                    case Constant.BASIC_TYPE_DATE:
                        predicate = cb.equal(root.get(field.getName()).as(Date.class), DateUtils.parseDate(value));
                        break;
                    default:
                        predicate = cb.equal(root.get(field.getName()).as(String.class), value);
                        break;
                }
                lp.add(predicate);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                log.error(e.getMessage(), e.getCause());
            }
        }
        return lp;
    }


    /** 检查Set Get方法以及忽略Transient 的注解
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param methods   方法
     * @param field 字段
     * @return boolean
     **/
    public static boolean checkGetSetMethodAndAnnotation(Method[] methods,Field field){
        boolean result = true;
        String fieldGetName = StringUtils.parGetName(field.getName());
        String fieldSetName = StringUtils.parSetName(field.getName());
        //校验是否有GETTER、SETTER的方法
        if (!StringUtils.checkGetMet(methods, fieldGetName) || !StringUtils.checkSetMet(methods, fieldSetName)) {
            result = false;
        } else {
            //检查 Transient 注解
            Annotation annotation = field.getAnnotation(Transient.class);
            if(StringUtils.isNotNull(annotation)){
                result = false;
            }
        }
        return result;
    }

    /**
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param cb
     * @param root
     * @param field
     * @param value
     * @return Predicate
     **/
    public static Predicate doInteger(CriteriaBuilder cb,Root<?> root,Field field,String value){
        return cb.equal(root.get(field.getName()).as(Integer.class), Integer.valueOf(value));
    }

}
