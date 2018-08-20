package com.gr.urlshortener.main;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.gr.urlshortener.ws.exception.WsExceptionHandler;
import com.gr.urlshortener.ws.filter.WsCorsFilter;
import com.gr.urlshortener.ws.v1.UrlResource;

/**
 * This is main configuration file for rest application using rest easy
 * 
 * @author ufarooq
 */
@ApplicationPath("")
public class Main extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public Main() {
		
		classes.add(UrlResource.class);
		classes.add(WsExceptionHandler.class);
		classes.add(WsCorsFilter.class);
		
		System.setProperty("jsse.enableSNIExtension", "false");
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
