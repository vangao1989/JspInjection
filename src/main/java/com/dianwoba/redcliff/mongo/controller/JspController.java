package com.dianwoba.redcliff.mongo.controller;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

@Controller
public class JspController {

	@RequestMapping("/jspTest")
	public String helloJsp(Map<String, Object> map) {
		System.out.println("JspController.helloJsp()");
		return "JspTest";
	}

	@RequestMapping(value = "/handleJava", method = RequestMethod.GET)
	public String inputTest(Map<String, Object> map) {
		System.out.println("JspController.inputTest()");
		return "InputTest";
	}

	@RequestMapping(value = "/handleJava", method = RequestMethod.POST)
	public ModelAndView handleJavaInput(@RequestParam("javaCode") String javaCode) {
		System.out.println("HelloController.handleJavaInput()");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("javaCode", javaCode);

		OutputStreamWriter pw = null;

		String path = this.getClass().getClassLoader().getResource("/").getPath();
		path = path.replace("classes", "jsp");
		try {
			pw = new OutputStreamWriter(new FileOutputStream(path + "JavaCode.jsp"), "UTF-8");
			pw.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>");
			pw.write("<%@ page import=\"org.springframework.web.context.support.WebApplicationContextUtils\"%>");
			pw.write("<%@ page import=\"org.springframework.context.ApplicationContext\"%>");
			pw.write("<%@ page import=\"com.alibaba.fastjson.JSON\"%>");
			pw.write("<%@ page isELIgnored=\"false\"%>");
			pw.write("<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>");
			pw.write(
					"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\"http://www.w3.org/TR/html4/loose.dtd\">");
			pw.write("<html>");
			pw.write("<head>");
			pw.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
			pw.write("<title>run java</title>");
			pw.write("</head>");
			pw.write("<body>");
			pw.write(
					"<table border=\"0\"><tr><td><form action=\"/handleJava\" method=\"POST\"><fieldset><legend>java runtime input:</legend>javaCode:<br>");
			pw.write(
					"<textarea id=\"javaCode\" name=\"javaCode\" rows=\"35\" cols=\"100\">${javaCode}</textarea>");
			pw.write("<br><br><input type=\"submit\" value=\"Submit\"></fieldset></form></td><td>");
			pw.write(
					"<form><fieldset><legend>java runtime output:</legend>result:<br><textarea id=\"result\" name=\"result\" rows=\"35\" cols=\"100\">");

			// 注入代码
			pw.write(javaCode);
			// 注入代码

			pw.write(
					"</textarea><br><br><button type=\"button\" onclick=\"document.getElementById('javaCode').value='';document.getElementById('result').value='';\">clear</button></fieldset></form></td></tr></table>");
			pw.write("</body>");
			pw.write("</html>");

			JSON.toJSON(path).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				pw.flush();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ModelAndView("JavaCode", model);
	}

}
