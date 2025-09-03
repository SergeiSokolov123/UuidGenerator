package org.example.logic;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UuidService {

    public String generateRandomUuid() {
        return UUID.randomUUID().toString();
    }
}
