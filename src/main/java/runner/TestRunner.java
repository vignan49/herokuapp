package runner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import testsuite.apitests.CreateBookings;
import testsuite.apitests.GetBookings;
import testsuite.apitests.UpdateBooking;

@RunWith(Suite.class)
@Suite.SuiteClasses
        (
                {
                        CreateBookings.class,
                        UpdateBooking.class,
                        GetBookings.class,
                }
        )
public class TestRunner {
}