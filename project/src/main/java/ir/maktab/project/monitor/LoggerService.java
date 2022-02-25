package ir.maktab.project.monitor;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

@Service
public class LoggerService implements HealthIndicator {
    private final String LOGGER_SERVICE = "logger service";

    @Override
    public Health health() {
        if (isLoggerServiceGood()) {
            return Health.up().withDetail(LOGGER_SERVICE, "service is running").build();
        }
        return Health.down().withDetail(LOGGER_SERVICE, "service is not available").build();
    }

    private boolean isLoggerServiceGood() {
        return true;
    }
}
