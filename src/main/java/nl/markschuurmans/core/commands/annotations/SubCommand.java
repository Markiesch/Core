package nl.markschuurmans.core.commands.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SubCommand {
    String name();
    String permission() default "";
}