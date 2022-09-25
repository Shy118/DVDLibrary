package com.dvdlibrary;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dvdlibrary.controller.DVDLibraryController;

public class App {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		
		DVDLibraryController controller = context.getBean("control", DVDLibraryController.class);
        controller.run();
        
        ConfigurableApplicationContext congifctx = (ConfigurableApplicationContext) context;
		congifctx.close();
	}

}
