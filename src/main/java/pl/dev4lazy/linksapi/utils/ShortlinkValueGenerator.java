package pl.dev4lazy.linksapi.utils;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShortlinkValueGenerator {

    private static final int ID_SIZE = 10;

    public static String generateShortLinkValue() {
        UUID uuid = UUID.randomUUID();
        String uuidWithoutDash = uuid.toString().replace("-", "");
        return uuidWithoutDash.substring(0, ID_SIZE);
    }
}
