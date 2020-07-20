package ch.voulgarakis.doodle.configuration;

import ch.voulgarakis.doodle.model.Poll;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.List;

@Component
public class Prefiller implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Prefiller.class);

    private final ReactiveMongoTemplate mongoTemplate;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    private final String primer;

    @Autowired
    public Prefiller(ReactiveMongoTemplate mongoTemplate,
                     ResourceLoader resourceLoader,
                     ObjectMapper objectMapper, @Value("${datasource.primer}") String primer) {
        this.mongoTemplate = mongoTemplate;
        this.resourceLoader = resourceLoader;
        this.objectMapper = objectMapper;
        this.primer = primer;
    }

    @Override
    public void run(ApplicationArguments args) throws IOException {
        LOGGER.info("Loading mongo db with primer dataset: {}", primer);

        File file = resourceLoader.getResource(primer).getFile();
        byte[] bytes = Files.readAllBytes(file.toPath());

        String json = new String(bytes);

        List<Poll> polls = objectMapper.readValue(json, new TypeReference<>() {
        });

        Long insertedIntoMongo = mongoTemplate.insertAll(polls)
                .count().block(Duration.ofSeconds(10));

        LOGGER.info("Loaded in mongo db {}/{} entries", insertedIntoMongo, polls.size());

//        BsonArray bsonValues = BsonArray.parse(json);
//
//        List<Document> docs = bsonValues.stream()
//                .flatMap(v -> {
//                    if (v instanceof BsonDocument) {
//                        BsonDocument bson = (BsonDocument) v;
//                        bson.toJson()
//
//                        return Stream.of(Document.parse(bson.toJson()));
//                    } else {
//                        LOGGER.warn("Invalid format: {}", v);
//                        return Stream.empty();
//                    }
//                }).collect(Collectors.toList());
//
//        mongoClient
//                .getDatabase(MONGO_DB_NAME)
//                .getCollection("poll")
//                .insertMany(docs);
    }
}
