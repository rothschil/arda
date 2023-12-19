// package io.github.rothschil.common.config.database;
//
//
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.stereotype.Component;
//
//
// @ConfigurationProperties(prefix = "common.sqlite")
// @Component
// public class DatabaseSqliteConfiguration {
//
//     @Value("${url}")
//     private String url;
//
//     @Value("${username}")
//     private String username;
//
//     @Value("${password}")
//     private String password;
//
//     @Value("${tablename}")
//     private String tablename;
//
//     public String getTablename() {
//         return tablename;
//     }
//
//     public void setTablename(String tablename) {
//         this.tablename = tablename;
//     }
//
//     public String getUrl() {
//         return url;
//     }
//
//     public void setUrl(String url) {
//         this.url = url;
//     }
//
//     public String getUsername() {
//         return username;
//     }
//
//     public void setUsername(String username) {
//         this.username = username;
//     }
//
//     public String getPassword() {
//         return password;
//     }
//
//     public void setPassword(String password) {
//         this.password = password;
//     }
// }
