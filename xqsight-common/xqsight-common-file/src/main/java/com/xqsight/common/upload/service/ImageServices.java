package com.xqsight.common.upload.service;


import com.xqsight.common.upload.image.ScaleParam;
import com.xqsight.common.upload.image.WatermarkParam;

public interface ImageServices {
	public boolean crop(String src, String dest, int x, int y, int width, int height);

	public boolean resize(String src, String dest, Integer width, Integer height, boolean exact);

	public boolean resize(String src, String dest, ScaleParam scaleParam);

	public boolean composite(String overlay, String src, String dest, Integer x, Integer y, Integer dissolve);

	public boolean composite(String overlay, String src, String dest, WatermarkParam watermarkParam);
}
