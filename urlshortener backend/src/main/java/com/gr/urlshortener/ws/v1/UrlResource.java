package com.gr.urlshortener.ws.v1;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.gr.urlshortener.core.model.Clicks;
import com.gr.urlshortener.core.model.UrlInfo;
import com.gr.urlshortener.ws.dto.GraphDataDto;
import com.gr.urlshortener.ws.dto.StatisticsDto;
import com.gr.urlshortener.ws.dto.UrlInfoDto;
import com.gr.urlshortener.ws.util.DateUtil;

import eu.bitwalker.useragentutils.UserAgent;

@Path("")
public class UrlResource extends BaseResource {

	private static final String CLICKS = "clicks";
	private static final String BROWSERS = "browsers";
	private static final String PLATFORMS = "platforms";

	@GET
	@Path("/stats/{id}")
	public StatisticsDto getStats(@PathParam("id") int id) {

		UrlInfoDto urlInfo = new UrlInfoDto();

		GraphDataDto clicks = new GraphDataDto();
		GraphDataDto browsers = new GraphDataDto();
		GraphDataDto platforms = new GraphDataDto();

		urlInfo = getUrlService().getUrlInfo(id);
		browsers = getUrlService().getBrowsersData(id);
		platforms = getUrlService().getPlatformsData(id);
		clicks = getUrlService().getClicksData(id);

		HashMap<String, GraphDataDto> statsData = new HashMap<>();
		statsData.put(CLICKS, clicks);
		statsData.put(BROWSERS, browsers);
		statsData.put(PLATFORMS, platforms);

		StatisticsDto stats = new StatisticsDto();

		stats.setStats(statsData);
		stats.setUrlInfo(urlInfo);
		return stats;
	}

	@POST
	public Response shortenUrl(UrlInfo urlInfo) {
		String shortUrl = getUrlService().shortenUrl(urlInfo.getOriginalUrl());

		if (shortUrl == null) {
			return Response.status(Status.BAD_REQUEST).entity("Url doesn't Exist").build();
		}
		return Response.status(Status.OK).entity(shortUrl).build();
	}

	@GET
	@Path("/{shortUrl}")
	public Response redirectToLongUrl(@PathParam("shortUrl") String shortUrl,
			@HeaderParam("user-agent") String userAgentString) throws Exception {
		URI longUrl = null;
		Date dateClicked = new Date();
		UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
		String browser = userAgent.getBrowser().getName();
		String platform = userAgent.getOperatingSystem().getName();
		try {
			UrlInfo urlInfo = getUrlService().getLongUrl(shortUrl);
			if (urlInfo != null) {
				longUrl = new URI(urlInfo.getOriginalUrl());
				Clicks click = new Clicks();
				click.setBrowser(browser);
				click.setPlatform(platform);
				click.setClick(1);
				click.setUrlinfo(urlInfo);
				click.setDateClicked(dateClicked);
				int clickCount = getUrlService().getCount(urlInfo.getId());
				getUrlService().addClicks(click);
				getUrlService().incrementClickInUrlInfo(clickCount + 1, urlInfo.getId());
				/**
				 * after adding the click in table checking if URL is expired if expired ->
				 * return bad request else redirect to the requested Page
				 */
				if (DateUtil.getDaysBetween(dateClicked, urlInfo.getExpiryDate()) > 0) {
					System.out.println(DateUtil.getDaysBetween(dateClicked, urlInfo.getExpiryDate()));
					return Response.seeOther(longUrl).build();
				} else
					return Response.status(Status.BAD_REQUEST).entity("Url Expired").build();
			} else
				return Response.status(Status.BAD_REQUEST).entity("longUrl doesn't Exist").build();
		} catch (Exception aex) {
			throw new Exception(aex);
		}
	}

	@GET
	@Path("/all")
	public Response getAllUrlInfos() {
		List<UrlInfo> urlInfos = getUrlService().getAllUrlInfos();
		if (urlInfos.size() > 0) {
			return Response.status(Status.OK).entity(urlInfos).build();
		}
		return Response.status(Status.OK).entity("no urls exists").build();

	}

}