package com.gr.urlshortener.ws.dto;

import java.io.Serializable;
import java.util.List;

public class GraphDataDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<String> labels;
	private List<Long> data;

	public GraphDataDto() {
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<Long> getData() {
		return data;
	}

	public void setData(List<Long> data) {
		this.data = data;
	}
}