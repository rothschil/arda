package io.github.rothschil.common.persistence.jpa.service;

import io.github.rothschil.common.persistence.jpa.entity.AbstractPo;
import io.github.rothschil.common.utils.Reflections;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Service基类
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @since 1.0.0
 */
@Transactional(readOnly = true,rollbackFor = Exception.class)
public abstract class BaseService<T extends AbstractPo<?>, ID extends Serializable> {

    protected JpaRepository<T, ID> jpaRepository;

    public BaseService() {}
    /**
     * 重要
     * **/
    public abstract void setJpaRepository(JpaRepository<T, ID> jpaRepository);


    /**
     * 按照主键查询
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param t  实例
     * @return List<T>   实体信息
     **/
    public List<T> findByEntity(T t) {
        return jpaRepository.findAll(getExample(t));
    }

    /**
     * 按照主键查询
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param t 实体信息
     * @param page 页
     * @param size 每页数量
     * @return Page<T>   实体信息
     **/
    public Page<T> findPageByEntity(int page, int size, T t) {
        size=size==0?10:size;
        return jpaRepository.findAll(getExample(t),PageRequest.of(page, size));
    }

    /**
     * 按照主键查询
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param t 实体信息
     * @return Page<T>   实体信息
     **/
    private Example getExample(T t){
        ExampleMatcher matcher = ExampleMatcher.matching();
        List<String> fields = new ArrayList<String>();
        Reflections.getField(t,fields);
        for (String fld: fields){
            matcher.withMatcher(fld,ExampleMatcher.GenericPropertyMatchers.exact());
        }
        return Example.of(t,matcher);
    }

    /**
     * 保存单个实体
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param t 实体信息
     * @return T  返回id对应的实体
     **/
    @Transactional(rollbackFor = Exception.class)
    public T save(T t) {
        return jpaRepository.save(t);
    }

    /**
     * 保存
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param t 实体信息
     * @return T  返回id对应的实体
     **/
    @Transactional(rollbackFor = Exception.class)
    public T saveAndFlush(T t) {
        t = save(t);
        jpaRepository.flush();
        return t;
    }

    /**
     * 根据主键删除相应实体
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param id 主键
     **/
    @Transactional(rollbackFor = Exception.class)
    public void delete(ID id) {
        jpaRepository.delete(findOne(id));
    }

    /**
     * 删除实体
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param t  实体
     **/
    @Transactional(rollbackFor = Exception.class)
    public void delete(T t) {
        jpaRepository.delete(t);
    }

    /**
     * 按照主键查询
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param id  id 主键
     * @return T   返回id对应的实体
     **/
    public T findOne(ID id) {
        return jpaRepository.getOne(id);
    }

    /**
     * 实体是否存在
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param id  id 主键
     * @return boolean   存在 返回true，否则false
     **/
    public boolean exists(ID id) {
        return findOne(id)==null;
    }

    /**
     * 统计实体总数
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @return long
     **/
    public long count() {
        return jpaRepository.count();
    }

    /**
     * 查询所有实体
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @return List<T>
     **/
    public List<T> findAll() {
        return jpaRepository.findAll();
    }


    /**
     * 按照顺序查询所有实体
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param sort  排序实例
     * @return List<T>
     **/
    public List<T> findAll(Sort sort) {
        return jpaRepository.findAll(sort);
    }

    /**
     * 分页及排序查询实体
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param pageable  分页及排序数据
     * @return Page<T>
     **/
    public Page<T> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable);
    }


    /**
     * 分页
     * @author <a href="https://github.com/rothschil">Sam</a>
     * @param page  页数
     * @param size  每页数量
     * @return Page<T>
     **/
    public Page<T> findEntityNoCriteria(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        return findAll(pageable);
    }

}
