package giuseppetavella.demo_login_system.helpers;

import giuseppetavella.demo_login_system.exceptions.InvalidUUIDStringException;

import java.util.UUID;

public class StringHelper {
    /**
     * Parse a string to UUID.
     *
     * @throws InvalidUUIDStringException if the string is an invalid UUID or null
     * @return a valid UUID 
     */
    public static UUID parseUUID(String itemIdAsStr) throws InvalidUUIDStringException 
    {
        if (itemIdAsStr == null) {
            throw new InvalidUUIDStringException("<ID is null>");
        }
        
        try {
            return UUID.fromString(itemIdAsStr);
        } catch(IllegalArgumentException ex) {
            throw new InvalidUUIDStringException(itemIdAsStr);
        }
    }
}
