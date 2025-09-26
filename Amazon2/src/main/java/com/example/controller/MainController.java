package com.example.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Device;
import com.example.model.Devices;

@Controller
public class MainController {
	
	
	@Autowired
	private RestTemplate rs;
	
	@RequestMapping("/")
	public String home1() {
		return "home.html";
	}
	
	
	@RequestMapping("/home")
	public String home() {
		return "home.html";
	}
	
	@RequestMapping("/blackberry")
	public ModelAndView blackberry() {
		ModelAndView mv=new ModelAndView("/blackberry");
		//rs=new RestTemplate();
		Devices deviceList=rs.getForObject("http://Blackberry/blackberry", Devices.class);
		mv.addObject("blackberry", deviceList);
		return mv;
	}
//	
//	@RequestMapping("/apple")
//	public String apple() {
//		return "apple.html";
//	}
//	
	
	
	@RequestMapping("/apple")
	public ModelAndView apple() {
		ModelAndView mv=new ModelAndView("apple");
		Device d1=new Device(1,"Black-1","Smart Phone");
		
		ArrayList<Device> myList=new ArrayList();
		myList.add(d1);
		
		Devices deviceList=new Devices(myList,"apple");
		mv.addObject("apple", deviceList);
		return mv;
	}
	
	
}
