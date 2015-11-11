package com.enjoyor.common.util;

import java.util.Map;

/**
 * @author : wxq
 * @date : 2012-12-7 下午2:05:07 读取resources.properties里面的值并放入缓存中
 * @update : ljb
 **/

public class ResourcesProperties {

	private Map<String, String> resourcesProperties;

	private static ResourcesProperties rp = null;

	private ResourcesProperties() {
	}

	public synchronized static ResourcesProperties getInstance() {
		if (rp == null) {
			rp = new ResourcesProperties();
		}
		return rp;
	}

	public Map<String, String> getResourcesPropreties() {
		if (resourcesProperties == null || resourcesProperties.isEmpty()) {
			resourcesProperties = StringUtil
					.getAllPropertiesFromResources("resources.properties");
		}
		return resourcesProperties;
	}

}
