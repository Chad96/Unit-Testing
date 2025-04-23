package validate_sa_id;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidateSaldTest {
    
    @Test
    void testValidIdNumbers() {
        assertTrue(ValidateSald.isIdNumberValid("2001014800086"));
        assertTrue(ValidateSald.isIdNumberValid("2909035800085"));
    }
    
    @Test
    void testInvalidLength() {
        assertFalse(ValidateSald.isIdNumberValid("200101480008"));
        assertFalse(ValidateSald.isIdNumberValid("20010148000861"));
    }
    
    @Test
    void testNonNumeric() {
        assertFalse(ValidateSald.isIdNumberValid("200101480008a"));
    }
    
    @Test
    void testInvalidYear() {
        assertFalse(ValidateSald.isIdNumberValid("9901014800085"));
    }
    
    @Test
    void testInvalidMonth() {
        assertFalse(ValidateSald.isIdNumberValid("2013014800084"));
    }
    
    @Test
    void testInvalidDay() {
        assertFalse(ValidateSald.isIdNumberValid("2001013200082"));
    }
    
    @Test
    void testInvalidGender() {
        assertFalse(ValidateSald.isIdNumberValid("2001019999086"));
    }
    
    @Test
    void testInvalidCitizenship() {
        assertFalse(ValidateSald.isIdNumberValid("2001014800286"));
    }
    
    @Test
    void testInvalidChecksum() {
        assertFalse(ValidateSald.isIdNumberValid("2001014800087"));
    }
    
    @Test
    void testInvalidDayForFebruary() {
        assertFalse(ValidateSald.isIdNumberValid("2002304800083"));
    }
    
    @Test
    void testGenderFemale() {
        assertTrue(ValidateSald.isIdNumberValid("2001014999086"));
    }
    
    @Test
    void testGenderMale() {
        assertTrue(ValidateSald.isIdNumberValid("2001016800084"));
    }
}