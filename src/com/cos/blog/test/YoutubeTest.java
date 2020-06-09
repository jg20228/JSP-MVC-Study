package com.cos.blog.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class YoutubeTest {

	@Test
	public void preview() {
		String content = "<a href=\"https://youtu.be/TgOu00Mf3kI\">\r\n" + "https://youtu.be/TgOu00Mf3kI\r\n" + "</a>";
		System.out.println(content);
		
		Document doc = Jsoup.parse(content);
		
		Elements els = doc.select("a");
		Element el = els.get(0);
		
		String value = el.attr("href");
		System.out.println(value);
		
		if(value.contains("https://www.youtube.com") || 
				value.contains("https://youtu.be")) {
			System.out.println("유튜브네?");
			String[] id = value.split("=");

			el.after("<iframe src=\"http://www.youtube.com/embed/"+id[1]+"\" width=\"800px\" height=\"400px\" frameborder=\"0\" allowfullscreen=\"\"></iframe>");
			el.after("<br/>");
		}else {
			System.out.println("유튜브아니네?");
		}
		
		String arr[] = value.split("/");
		for (String s : arr) {
		System.out.println(s);	
		}
		System.out.println(doc);
	}
}
