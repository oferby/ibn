package com.huawei.ibn.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
//@EnableNeo4jRepositories(basePackages = "com.huawei.ibn")
//@EnableTransactionManagement
public class PersistenceContext {

//    @Bean
//    public SessionFactory getSessionFactory() {
//        return new SessionFactory(configuration(), "com.huawei.ibn");
//    }
//
//    @Bean
//    public Neo4jTransactionManager transactionManager() throws Exception {
//        return new Neo4jTransactionManager(getSessionFactory());
//    }
//
//    @Bean
//    public org.neo4j.ogm.config.Configuration configuration() {
//        return new org.neo4j.ogm.config.Configuration.Builder()
//                .uri("http://10.100.99.85:7474")
//                .build();
//    }

}
