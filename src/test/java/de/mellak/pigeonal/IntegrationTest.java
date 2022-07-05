package de.mellak.pigeonal;

import de.mellak.pigeonal.Pigeonalpoc2App;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = Pigeonalpoc2App.class)
public @interface IntegrationTest {
}
