package utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TestInfo {
    String testCaseName();
    String testCaseId();
    String owner() default "Dhruv";
    String priority() default "Medium";
}
