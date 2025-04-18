package nl.markschuurmans.core.commands.annotations;

import nl.markschuurmans.core.commands.enums.CommandSenderType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandInfo {
    String name();
    String permission() default "";
    String[] aliases() default {};
    CommandSenderType allowedSender() default CommandSenderType.ANY;
}