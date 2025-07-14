package infrastructure;

import java.time.Instant;
import java.time.Duration;

public class MockSessionManager {

    private static final long TIMEOUT_SECONDS = 5;
    private Instant lastActivity = Instant.now();

    public void refreshSession() {
        lastActivity = Instant.now();
    }

    public boolean isSessionExpired() {
        return Duration.between(lastActivity, Instant.now()).getSeconds() > TIMEOUT_SECONDS;
    }

    public long getRemainingSeconds() {
        return Math.max(0, TIMEOUT_SECONDS - Duration.between(lastActivity, Instant.now()).getSeconds());
    }
}
