package com.gr.urlshortener.ws.v1;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gr.urlshortener.core.service.UrlService;
import com.gr.urlshortener.core.service.UrlServiceLocal;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BaseResource {

	UrlServiceLocal urlService = UrlService.getService();

	protected UrlServiceLocal getUrlService() {
		return urlService;
	}
}