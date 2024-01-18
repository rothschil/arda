package io.github.rothschil.common.persistence.jpa.repository;

import io.github.rothschil.common.persistence.jpa.entity.AbstractPo;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * <p>抽象DAO层基类 提供一些简便方法<br/>
 * 想要使用该接口需要在spring配置文件的jpa:repositories中添加
 * <p/>
 * <p>泛型 ： T 表示实体类型；ID表示主键类型
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @since 1.0.0
 */
@NoRepositoryBean
public interface BaseRepository<T extends AbstractPo<ID>, ID extends Serializable> extends JpaRepository<T, ID> {

    /** 按照主键的数组，批量删除
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param ids   主键数组
     **/
    void delete(ID[] ids);

    /** 根据SQL，查询结果，获取结果列表
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param sql   原生SQL语句
     * @return List
     **/
    List<T> listBySql(String sql);

    /** 根据HQL，查询结果，获取结果列表
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param hql   hql HQL语句
     * @return List
     **/
    List<T> listByHql(String hql);

    /** 根据SQL，查询结果，获取结果列表
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param sql   原生SQL语句
     * @return Object
     **/
    Object getTarget(String sql);

    /**
     * 按照SQL执行修改命令
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param sql   原生SQL语句
     * @param args  参数
     **/
    void updateBySql(String sql,Object...args);

    /**
     * 按照HQL执行修改命令
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param hql   HQL语句
     * @param args  参数
     **/
    void updateByHql(String hql,Object...args);

    /**
     * 利用实体结合 Specification 默认设置进行分页
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param t 实体类
     * @param pageable  分页信息
     * @return Page
     **/
    Page<T> find(T t, Pageable pageable);

    /** 利用Specification 默认设置进行分页
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param spec  条件
     * @param pageable  分页信息
     * @return Page
     **/
    Page<T> find(Specification<T> spec, Pageable pageable);

    /**
     * 根据实体信息查询
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param t 非空
     * @param pageable  非空
     * @param list  多种查询条件,可以自定义实现，拓展为动态查询，可以为空，为空时候，自动从实体的属性中获取
     * @return Page
     **/
    Page<T> findByCriteriaQuery(T t, Pageable pageable,List<Predicate> list);

    /** 以SQL方式，执行批量插入
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param sql   原生SQL语句
     * @return int
     **/
    int batchInsert(String sql);

    /** 以list方式，执行批量插入
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param list  列表集合
     * @return int
     **/
    int batchInsert(List<T> list);

}
