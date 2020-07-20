package ch.voulgarakis.doodle.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
@ToString
public class User {
    private String name;
}
