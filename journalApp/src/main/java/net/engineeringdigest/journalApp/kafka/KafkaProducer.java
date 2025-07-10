//package net.engineeringdigest.journalApp.kafka;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaProducer {
//
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    private final String TOPIC = "weekly-sentiments";
//
//    public void sendMessage(String message) {
//        kafkaTemplate.send(TOPIC, message);
//    }
//}
//
//
