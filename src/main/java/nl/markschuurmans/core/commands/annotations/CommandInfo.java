package nl.markschuurmans.core.commands.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandInfo {
    String name();
    String permission() default "";
    String[] aliases() default {};
    boolean playerOnly() default false;
}