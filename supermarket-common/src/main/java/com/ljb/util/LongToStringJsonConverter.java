package com.ljb.util;

import java.math.BigInteger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class LongToStringJsonConverter extends ObjectMapper {
    /**
     * 
     */
	private static final long serialVersionUID = 3223645203459453114L;;
    
	public LongToStringJsonConverter() {
		super();
		SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance); 
        registerModule(simpleModule);
		// TODO 自动生成的构造函数存根
	}
}
