package edu.dufe.student.registry.utils;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

public class JsonUtils {
	public static String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

	public static Object convertJsonToObject(String json, Class clazz) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Object bean = mapper.readValue(json, clazz);
		return bean;
	}

	public static boolean validateJsonSchema(Object object, String jsonSchemaFilePath) throws Exception {

		String convertedStr = convertObjectToJson(object);
		String schemaStr = readJsonFileAsString(jsonSchemaFilePath);

		JsonNode objectNode = JsonLoader.fromString(convertedStr);
		JsonNode schemaNode = JsonLoader.fromString(schemaStr);

		ProcessingReport report = JsonSchemaFactory.byDefault().getValidator().validateUnchecked(schemaNode,
				objectNode);
		Iterator<ProcessingMessage> it = report.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		return report.isSuccess();
	}

	private static String readJsonFileAsString(String filePath) throws Exception {
		ClassPathResource cpr = new ClassPathResource(filePath);
		byte[] bdata = FileCopyUtils.copyToByteArray(cpr.getInputStream());
		String str = new String(bdata, StandardCharsets.UTF_8);
		return str;
	}
}
