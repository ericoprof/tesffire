package br.com.erico.testfire.common;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        FileInputStream serviceAccount =
            new FileInputStream("src/main/resources/firebase/testfire-42871-firebase-adminsdk-pr0n4-45338dcd60.json");

        FirebaseOptions options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build();

        // Inicializa o FirebaseApp apenas se ele não existir
        if (FirebaseApp.getApps().isEmpty()) {
            return FirebaseApp.initializeApp(options);
        } else {
            return FirebaseApp.getInstance();
        }
    }

    @Bean
    public Firestore firestore(FirebaseApp firebaseApp) {
        // Certifica-se que o FirebaseApp é passado e já foi inicializado
        return FirestoreClient.getFirestore();
    }
}
