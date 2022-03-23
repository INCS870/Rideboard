package com.rideboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView init(Model model) throws Exception {
		model.addAttribute("host_ip",  java.net.InetAddress.getLocalHost());
		Object userObj = com.rideboard.common.Utils.getSession("security.userid");
		if(userObj != null) {
			return new ModelAndView("redirect:/main");
		}
		return new ModelAndView("login");
	}
}
