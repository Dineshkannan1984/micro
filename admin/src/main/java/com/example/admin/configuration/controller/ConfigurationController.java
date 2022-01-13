package com.example.admin.configuration.controller;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import com.example.admin.configuration.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigurationController {
    // private static Logger logger = LoggerFactory.getLogger(AuthenticateController.class);

	@Autowired
	Config config;

	@RequestMapping(value = "/config", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String config() throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		String jsonString = "{";

		BeanInfo beanInfo = Introspector.getBeanInfo(Config.class);
		for (PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {
			String propertyName = propertyDesc.getName();
			Object value = propertyDesc.getReadMethod().invoke(config);

			jsonString=jsonString + "\"" + propertyName + "\":\"" + value + "\",";
		}

		if(jsonString.endsWith(","))
		{
			jsonString = jsonString.substring(0,jsonString.length() - 1);
		}
		jsonString = jsonString + "}";

		return jsonString;
	}
}
