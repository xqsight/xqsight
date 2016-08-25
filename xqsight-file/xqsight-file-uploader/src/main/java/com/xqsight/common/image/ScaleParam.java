package com.xqsight.common.image;

public class ScaleParam {
	private Boolean scale;
	private Boolean exact;
	private Integer width;
	private Integer height;

	public ScaleParam(Boolean scale, Boolean exact, Integer width, Integer height) {
		this.scale = scale;
		this.exact = exact;
		this.width = width;
		this.height = height;
	}

	public Boolean getScale() {
		return scale != null && scale && (width != null || height != null);
	}

	public void setScale(Boolean scale) {
		this.scale = scale;
	}

	public Boolean getExact() {
		return exact != null ? exact : false;
	}

	public void setExact(Boolean exact) {
		this.exact = exact;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

}
