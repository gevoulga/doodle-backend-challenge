package ch.voulgarakis.doodle.configuration;

import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
public class MongoConfig {
    //        extends AbstractReactiveMongoConfiguration {
    private static final String MONGO_DB_URL = "localhost";
    static final String MONGO_DB_NAME = "embeded_db";

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(MongoClient mongoClient) {
        return new ReactiveMongoTemplate(mongoClient, MONGO_DB_NAME);
    }

//    @SneakyThrows
//    @Override
//    public MongoClient reactiveMongoClient() {
//        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
//        mongo.setBindIp(MONGO_DB_URL);
//        com.mongodb.MongoClient mongoClient = mongo.getObject();
//        new MongoClientImpl(mongoClient)
//
//        return MongoClients.create(MONGO_DB_URL);
//    }

//    @Override
//    protected String getDatabaseName() {
//        return MONGO_DB_NAME;
//    }

//    @Bean
//    public MongoTemplate mongoTemplate() throws IOException {
//        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
//        mongo.setBindIp(MONGO_DB_URL);
//        MongoClient mongoClient = mongo.getObject();
//        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);
//        return mongoTemplate;
//    }
}