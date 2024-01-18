package io.github.rothschil.common.persistence.jpa.repository.impl;

import io.github.rothschil.common.persistence.jpa.entity.AbstractPo;
import io.github.rothschil.common.persistence.jpa.repository.BaseRepository;
import io.github.rothschil.common.persistence.jpa.util.MethodUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 自定义实现的 Base Repository
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @since 1.0.0
 */
public class BaseRepositoryImpl<T extends AbstractPo<ID>, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager entityManager;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }


    /** 按照主键的数组，批量删除
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param ids   主键数组
     **/
    @Override
    public void delete(ID[] ids) {


    }


    /** 根据SQL语句获取目标
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param sql   原生SQL语句
     * @return Object
     **/
    @Override
    public Object getTarget(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        return query.getSingleResult();
    }

    /**
     * 按照SQL执行修改命令
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param sql   原生SQL语句
     * @param args  参数
     **/
    @Override
    public void updateBySql(String sql, Object... args) {

        Query query = entityManager.createNativeQuery(sql);
        int i = 0;
        for (Object arg : args) {
            query.setParameter(++i, arg);
        }
        query.executeUpdate();
    }


    /**
     * 按照HQL执行修改命令
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param hql   HQL语句
     * @param args  参数
     **/
    @Override
    public void updateByHql(String hql, Object... args) {
        Query query = entityManager.createQuery(hql);
        int i = 0;
        for (Object arg : args) {
            query.setParameter(++i, arg);
        }
        query.executeUpdate();
    }


    /** 根据SQL，查询结果，获取结果列表
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param sql   原生SQL语句
     * @return List
     **/
    @Override
    public List<T> listBySql(String sql) {
        return entityManager.createNativeQuery(sql).getResultList();
    }

    /** 根据HQL，查询结果，获取结果列表
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param hql   hql HQL语句
     * @return List
     **/
    @Override
    public List<T> listByHql(String hql){
        return  entityManager.createQuery(hql).getResultList();
    }

    /** 以SQL方式，执行批量插入
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param sql   原生SQL语句
     * @return int
     **/
    @Override
    public int batchInsert(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        return query.executeUpdate();
    }

    /** 以list方式，执行批量插入
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param list  列表集合
     * @return int
     **/
    @Override
    public int batchInsert(List<T> list) {
        int i = 0;
        for (T t : list) {
            i++;
            entityManager.persist(t);
            if (i % 1000 == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        return 0;
    }

    /** 利用Specification 默认设置进行分页，不想使用分页，可以使用 Pageable.unpaged()
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param spec  条件
     * @param pageable  分页信息
     * @return Page
     **/
    @Override
    public Page<T> find(Specification<T> spec, Pageable pageable) {
        return super.findAll(spec, pageable);
    }

    /**
     * 利用实体结合 Specification 默认设置进行分页，不想使用分页，可以使用 Pageable.unpaged()
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param t 实体类
     * @param pageable  分页信息
     * @return Page
     **/
    @Override
    public Page<T> find(T t, Pageable pageable) {
        Specification<T> spec = MethodUtil.getSpecification(t);
        return find(spec, pageable);
    }

    /**
     * 根据实体信息查询，不想使用分页，可以使用 Pageable.unpaged()
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param t 非空
     * @param pageable  非空
     * @param list  多种查询条件,可以自定义实现，拓展为动态查询，可以为空，为空时候，自动从实体的属性中获取
     * @return Page
     **/
    @Override
	public Page<T> findByCriteriaQuery(T t,Pageable pageable,List<Predicate> list) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery query = cb.createQuery();
		// Root 定义查询的From子句中能出现的类型
		Root<T> root = query.from(t.getClass());

        // 多种查询条件,可以自定义实现，拓展为动态查询，可以为空，为空时候，自动从实体的属性中获取
        if(list.isEmpty()){
            list = MethodUtil.getFieldValue(t,root,cb);
        }

        List<Expression<?>> grouping = new ArrayList<Expression<?>>();
        grouping.add(root.get("id"));
        grouping.add(root.get("flag"));
        grouping.add(root.get("localCode"));
        grouping.add(root.get("localName"));
        grouping.add(root.get("lv"));
        grouping.add(root.get("supLocalCode"));
        grouping.add(root.get("url"));
        query.multiselect(
                root.get("id"),
                root.get("flag"),
                root.get("localCode"),
                root.get("localName"),
                root.get("lv"),
                root.get("supLocalCode"),
                root.get("url"),
                cb.sum(root.get("id")));


		query.where(list.toArray(new Predicate[list.size()]));
        query.groupBy(grouping);

        TypedQuery<T> typedQuery = entityManager.createQuery(query);
//        typedQuery.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
//        typedQuery.setMaxResults(pageable.getPageSize());


        long total = getTotal(query);
		List<T> content = total > typedQuery.getFirstResult() ? typedQuery.getResultList() : Collections.<T>emptyList();
		return new PageImpl<T>(content, pageable, total);
    }

    /**
     * 获取数量
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param query query 查询CriteriaQuery
     * @return int
     **/
    private int getTotal(CriteriaQuery query){
        List totals = entityManager.createQuery(query).getResultList();
        if(totals.isEmpty()){
            return 0;
        }
        return totals.size();
    }
}
