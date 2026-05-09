package com.rslakra.swing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Skipped when {@code java.awt.headless=true} (default for {@code buildMaven.sh} / CI).
 */
@DisabledIfSystemProperty(named = "java.awt.headless", matches = "true")
class SwingUtilityTest {

    @Test
    void setSystemLookAndFeel_whenDisplayAvailable() {
        assertDoesNotThrow(SwingUtility::setSystemLookAndFeel);
    }
}
