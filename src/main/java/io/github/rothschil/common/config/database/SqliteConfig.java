// package io.github.rothschil.common.config.database;
//
//
// import io.github.rothschil.common.config.DynamicDataSource;
// import io.github.rothschil.common.utils.sqlite.SqliteBuilder;
// import io.github.rothschil.common.utils.sqlite.SqliteUtils;
// import org.apache.ibatis.session.SqlSessionFactory;
// import org.mybatis.spring.SqlSessionFactoryBean;
// import org.mybatis.spring.SqlSessionTemplate;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
// import javax.sql.DataSource;
// import java.sql.SQLException;
//
// @Configuration
// public class SqliteConfig {
//
//     @Value("${common.sqlite.url}")
//     private String dataSourceUrl;
//
//     @Value("${common.sqlite.tablename}")
//     private String tablename;
//
//     @Bean(name = "sqliteDataSource")
//     public DataSource sqliteDataSource(){
//         SqliteUtils.initSqliteFile(SqliteUtils.getFilePath(dataSourceUrl));
//         DataSource dataSource  = SqliteBuilder.create().url(dataSourceUrl).build();
//         try {
//             //尝试初始化数据库-表不存在时创建
//             SqliteUtils.initProDb(dataSource.getConnection(),tablename);
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return dataSource;
//     }
//
//     /**
//      * session工厂
//      */
//     @Bean(name = "sqlSessionFactory")
//     public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource dataSource) throws Exception {
//         SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//         sessionFactoryBean.setDataSource(dataSource);
//         sessionFactoryBean.setMapperLocations(
//                 new PathMatchingResourcePatternResolver().
//                         getResources("classpath:mapper/**/*.xml"));
//         return sessionFactoryBean.getObject();
//
//     }
//
//     /**
//      * session模板
//      * @param sqlSessionFactory
//      * @return
//      */
//     @Bean(name = "sqlSessionTemplate")
//     public SqlSessionTemplate ComSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//         return new SqlSessionTemplate(sqlSessionFactory);
//     }
//
//     /**
//      * 动态数据源
//      * @param dataSource
//      * @return
//      */
//     @Bean(name = "dynamicDataSource")
//     public DynamicDataSource dynamicDataSource(@Qualifier("sqliteDataSource") DataSource dataSource){
//         return  new DynamicDataSource(dataSource);
//     }
//
// }
