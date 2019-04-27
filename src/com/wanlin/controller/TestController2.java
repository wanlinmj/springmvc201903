package com.wanlin.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kangwl_pc
 *
 */
@Controller
@RequestMapping("/test")
public class TestController2 {

	// http://localhost/springmvc/test/hellopage?name=xxx123
    @RequestMapping("/hellopage")
    public String getHello(String name ,Map<String ,Object> map){
        String res = "reeee";
        System.out.println("============ hellopage ===================== " + name);	
        map.put("res", res + name);
        return "/hellopage";
    }

}
