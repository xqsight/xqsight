package com.xqsight.common.image;

import org.apache.commons.lang3.StringUtils;

public class WatermarkParam {
	private Boolean watermark;
	private String imagePath;
	private Integer alpha;
	private Integer position;
	private Integer paddingX;
	private Integer paddingY;
	private Integer minWidth;
	private Integer minHeight;

	public WatermarkParam(Boolean watermark) {
		this(watermark, null);
	}

	public WatermarkParam(Boolean watermark, String imagePath) {
		this(watermark, imagePath, null, null, null, null, null, null);
	}

	public WatermarkParam(Boolean watermark, String imagePath, Integer alpha,
			Integer position, Integer paddingX, Integer paddingY,
			Integer minWidth, Integer minHeight) {
		this.watermark = watermark;
		this.imagePath = imagePath;
		this.alpha = alpha;
		this.position = position;
		this.paddingX = paddingX;
		this.paddingY = paddingY;
		this.minWidth = minWidth;
		this.minHeight = minHeight;

	}

	public float getAlphaFloat() {
		float alpha = (float) getAlpha();
		return alpha / (float) 100;
	}

	public Boolean getWatermark() {
		return watermark != null && watermark
				&& StringUtils.isNotBlank(getImagePath());
	}

	public void setWatermark(Boolean watermark) {
		this.watermark = watermark;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getAlpha() {
		return alpha != null ? alpha : 50;
	}

	public void setAlpha(Integer alpha) {
		this.alpha = alpha;
	}

	public Integer getPosition() {
		return position != null ? position : 9;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getPaddingX() {
		return paddingX != null ? paddingX : 20;
	}

	public void setPaddingX(Integer paddingX) {
		this.paddingX = paddingX;
	}

	public Integer getPaddingY() {
		return paddingY != null ? paddingY : 20;
	}

	public void setPaddingY(Integer paddingY) {
		this.paddingY = paddingY;
	}

	public Integer getMinWidth() {
		return minWidth != null ? minWidth : 300;
	}

	public void setMinWidth(Integer minWidth) {
		this.minWidth = minWidth;
	}

	public Integer getMinHeight() {
		return minHeight != null ? minHeight : 300;
	}

	public void setMinHeight(Integer minHeight) {
		this.minHeight = minHeight;
	}

}
