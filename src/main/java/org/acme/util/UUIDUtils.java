package org.acme.util;

import java.util.UUID;

import org.acme.exception.InvalidUUIDException;

public class UUIDUtils {

    public static UUID parse(String value) {
        try {
            return UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new InvalidUUIDException();
        }
    }
}