package com.ctel.model;

import java.io.Serializable;

public class KongConfigReq implements Serializable {

	private static final long serialVersionUID = -2674105403220709534L;
	private String name;
	private String hosts;
	private String upstream_url;
	private String uris;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHosts() {
		return hosts;
	}

	public void setHosts(String hosts) {
		this.hosts = hosts;
	}

	public String getUpstream_url() {
		return upstream_url;
	}

	public void setUpstream_url(String upstream_url) {
		this.upstream_url = upstream_url;
	}

	public String getUris() {
		return uris;
	}

	public void setUris(String uris) {
		this.uris = uris;
	}
}
