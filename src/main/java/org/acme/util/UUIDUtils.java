package org.acme.util;

import java.util.UUID;

import org.acme.exception.InvalidUUIDException;

public class UUIDUtils {

    public static UUID parse(String value) {
        try {
            if (value == null || value.isBlank()) {
                throw new InvalidUUIDException();
            }

            return UUID.fromString(value);
        } catch (IllegalArgumentException exception) {
            throw new InvalidUUIDException();
        }
    }
}