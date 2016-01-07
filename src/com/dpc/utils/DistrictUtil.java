package com.dpc.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DistrictUtil {

	public static void main(String[] args) throws Exception {
		
		Document doc = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/index.html").get();
		Elements pros = doc.getElementsByClass("provincetr");
		for(Element e : pros){
			Elements alist = e.select("a[href]");
			for(Element a : alist){
				String ahref = a.attr("href");
				Document cityDoc = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/"+ahref).post();
				Elements citys = cityDoc.getElementsByClass("citytr");
				System.err.println(citys);
			}
			
		}
	}
}
