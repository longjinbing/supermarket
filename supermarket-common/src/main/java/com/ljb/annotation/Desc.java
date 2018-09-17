package com.ljb.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Desc {

	public String group() default "系统设置";
	
	public String name() default "管理";
	
	public int type() default 0;
	
	public String url() default "#";
	
	public String icon() default "glyphicon glyphicon-cog";

	public int status() default 1;
	
}
