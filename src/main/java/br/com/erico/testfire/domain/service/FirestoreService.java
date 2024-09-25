package br.com.erico.testfire.domain.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.google.cloud.firestore.Firestore;

@Service
public class FirestoreService {

    private final Firestore firestore;

    // Construtor com injeção de dependência
    public FirestoreService(Firestore firestore) {
        this.firestore = firestore;
    }

    public String addUser(String userId, String email) throws Exception {
        var docRef = firestore.collection("users").document(userId);

        var data = new HashMap<String, Object>();
        data.put("email", email);

        var result = docRef.set(data);

        return result.get().getUpdateTime().toString();
    }
}
