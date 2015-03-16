package com.huayu.web.platform.factory;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.config.EasyFactoryConfiguration;

import com.huayu.web.platform.utils.string.CamelCaseUtils;

public class BasicAutomationFactory {
	public static VelocityEngine getVelocityInstance() {
		
		VelocityEngine velocity = new VelocityEngine();
		velocity.setProperty(Velocity.RESOURCE_LOADER, "class");
		velocity.setProperty("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		velocity.init();
		
		EasyFactoryConfiguration config = new EasyFactoryConfiguration();
		config.toolbox(Scope.APPLICATION).tool("camelCaseTool", CamelCaseUtils.class).restrictTo("");
//		ToolboxFactory factory = config.createFactory();
		
		return velocity;
	}
	
	
}
