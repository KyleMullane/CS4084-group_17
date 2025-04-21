//Code not working currently

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class UserAccountSystemTest {

    private DBHandler db;

    @Before
    public void setUp() {
        db = new DBHandler(Test); 
        db.clearDatabase();
    }

    @Test
    @DisplayName("Test 1.1.1 - Account Creation")
    public void testAccountCreation() {
        assertFalse(db.isLoginValid(user)); // User shouldn't exist yet
        User user = new User("TestName", "12345");
        db.addUser(user);
        assertTrue(db.isLoginValid(user)); // Now the user should exist
    }

    @Test
    @DisplayName("Test 1.1.2 - Account Creation (Duplicate)")
    public void testDuplicateAccountCreation() {
        User user = new User("jane_doe", "12345");
        db.addUser(user);
        assertTrue(db.isLoginValid(user));
        boolean exists = db.isLoginValid(user);
        if (exists) {
            assertTrue(true);
        } else {
            fail("Duplicate user allowed!"); //Test should fail or Pass this exception.
        }
    }

    @Test
    @DisplayName("Test 1.2 - Login with Correct Credentials")
    public void testSuccessfulLogin() {
        User user = new User("John", "12345");
        db.addUser(user);
        assertTrue(db.isLoginValid(new User("John", "12345"))); //Test should Pass
    }

    @Test
    @DisplayName("Test 1.3 - Login with Incorrect Credentials")
    public void testFailedLoginIncorrectPassword() {
        User user = new User("bob", "12345");
        db.addUser(user);
        assertFalse(db.isLoginValid(new User("bob", "wrongpass"))); //Test should Fail
    }

    @Test
    @DisplayName("Test 1.4 - Login with Non-Existing Username Fails")
    public void testFailedLoginNonExistingUser() {
        assertFalse(db.isLoginValid(new User("Jane", "12345"))); //Test should fail
    }

    @Test
    @DisplayName("Test 1.5 - Register New User and Log In Successfully")
    public void testRegisterAndLogin() {
        User user = new User("charlie", "12345");
        assertFalse(db.isLoginValid(user));
        db.addUser(user);
        assertTrue(db.isLoginValid(user)); //Test should Pass
    }

    @Test
    @DisplayName("Test 1.6 - Account Creation (Empty Username or Password)")
    public void testEmptyUsernamePassword() {
        User emptyUsername = new User("", "12345");
        User emptyPassword = new User("validUser", "");
        assertFalse(db.isLoginValid(emptyUsername));
        assertFalse(db.isLoginValid(emptyPassword)); //Test should Fail
    }

    @Test
    @DisplayName("Test 2.1 - Trip Creation")
    public void testSuccessfulTripCreation() {
        Trip trip = new Trip("New York", "Paris", "2025-06-01", "2025-06-10", 2000.0);
        db.addTrip(trip);
        List<Trip> trips = db.getTrips();

        assertEquals(1, trips.size());
        Trip savedTrip = trips.get(0);
        assertEquals("Paris", savedTrip.getDestination());
        assertEquals("New York", savedTrip.getDeparture());
        assertEquals("2025-06-01", savedTrip.getDateDeparture());
        assertEquals("2025-06-10", savedTrip.getDateReturn());
        assertEquals(2000.0, savedTrip.getBudget()); //Test should Pass
    }

    @Test
    @DisplayName("Test 2.2.1 - Trip Creation (Invalid Budget)")
    public void testTripCreationInvalidBudget() {
        try {
            Trip invalidTrip = new Trip("Tokyo", "Seoul", "2025-07-01", "2025-07-05", 2000);
            db.addTrip(invalidTrip);
            fail("Expected NumberFormatException was not thrown");
        } catch (NumberFormatException e) {
            assertTrue(true); //Test should fail or pass exception
        }
    }

    @Test
    @DisplayName("Test 2.2.2 -  Trip Creation (Invalid Budget)")
    public void testTripCreationInvalidBudget() {
        try {
            Trip invalidTrip = new Trip("Tokyo", "Seoul", "2025-07-01", "2025-07-05", 2000.00);
            db.addTrip(invalidTrip);
            fail("Expected NumberFormatException was not thrown");
        } catch (NumberFormatException e) {
            assertTrue(true); //Test should fail or pass exception
        }
    }

    @Test
    @DisplayName("Test 2.3 - Trip Creation (Empty Fields)")
    public void testTripCreationMissingFields() {
        Trip incompleteTrip = new Trip("", "Paris", "2025-06-01", "", 1200.0);
        db.addTrip(incompleteTrip);
        List<Trip> trips = db.getTrips();
        assertEquals(1, trips.size(), "Trip should be added even with missing return date");
        assertEquals("", trips.get(0).getDeparture()); //Test should fail, missing required fields.
    }

    @Test
    @DisplayName("Test 2.4 - Trip Creation (Negative Budget)")
    public void testTripCreationNegativeBudget() {
        Trip trip = new Trip("Berlin", "Rome", "2025-08-01", "2025-08-05", -500.0);
        db.addTrip(trip);
        List<Trip> trips = db.getTrips();
        assertEquals(1, trips.size());
        assertTrue(trips.get(0).getBudget() < 0, "Trip with negative budget was still added"); //Test should fail as trip is Invalid
    }

    @Test
    @DisplayName("Test 2.5 - Trip Creation (Empty Return Date)")
    public void testTripCreationNoReturnDate() {
        Trip trip = new Trip("Miami", "Cancun", "2025-09-01", "", 1000.0);
        db.addTrip(trip);

        List<Trip> trips = db.getTrips();
        assertEquals(1, trips.size());
        assertEquals("", trips.get(0).getDateReturn(), "Return date should be allowed to be empty");//Test should Pass
    }
}
