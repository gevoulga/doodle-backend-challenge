package ch.voulgarakis.doodle.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
@ToString
public class Poll {

    @Id
    private String id;
    private long initiated;
    private String title;
    private User initiator;
//    "id": "xsd4cv89t5f5um4b",
//            "adminKey": "r44d7piq",
//            "latestChange": 1485521573482,
//            "initiated": 1485521569056,
//            "participantsCount": 4,
//            "inviteesCount": 0,
//            "type": "TEXT",
//            "hidden": true,
//            "preferencesType": "YESNO",
//            "state": "OPEN",
//            "locale": "fr_CH",
//            "title": "Qui sont les superhéros Marvel les plus oufs?",
//            "initiator": {
//        "name": "John Doe",
//                "email": "mh+sample@doodle.com",
//                "notify": false
//    },
}
