package bleshadow.dagger.releasablereferences;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
@Target({ElementType.ANNOTATION_TYPE})
@Documented
public @interface CanReleaseReferences {
}
