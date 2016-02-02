package com.winry;

import com.blade.Blade;

public class App {

	public static void main(String[] args) {
		Blade blade = Blade.me();
		blade.isDev(true);
		blade.setAppConf("blade.properties");
		blade.get("/", (request, response) -> {
			response.render("index");
		});
		blade.listen(9001).start();
	}
}
