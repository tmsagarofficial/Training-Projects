package com.example.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Device;
import com.example.model.Devices;

@RestController
public class MainController {
	
	@GetMapping("/blackberry")
	public Devices blackberry() {
		
		Device d1 = new Device(1, "Black-1", "Smart Phone--from-2");
	    Device d2 = new Device(2, "Black-2", "Smart Phone--from-2");
	    Device d3 = new Device(3, "Black-3", "Feature Phone--from-2");
	    Device d4 = new Device(4, "Black-4", "Tablet--from-2");
	    Device d5 = new Device(5, "Black-5", "Smart Watch--from-2");
		
		ArrayList<Device> myList=new ArrayList();
		myList.add(d1);
		myList.add(d2);
		myList.add(d3);
		myList.add(d4);
		myList.add(d5);
		
		Devices deviceList=new Devices(myList,"blackberry");
		return deviceList;
	}
//	
//	@RequestMapping("/apple")
//	public String apple() {
//		return "apple.html";
//	}
//	
	
	
	@GetMapping("/apple")
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
