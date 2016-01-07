package com.dpc.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Sig {

	public static void main(String[] args) throws Exception{
		Document doc = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/index.html").post();
		Elements pros = doc.getElementsByClass("provincetr");
		for(Element e : pros){
			Elements provinceList = e.select("a[href]");
			for(Element a : provinceList){
				String ahref = a.attr("href");
				String code = ahref.substring(0,2);
				String name = a.text();
				
				Document cityDoc = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/"+ahref).post();
				Elements citys = cityDoc.getElementsByClass("citytr");
				for(Element city : citys){
					Elements cityA = city.select("a");
					String cityCode = cityA.get(0).text();
					String cityName = cityA.get(1).text();
					 
				}
			}
		}
	}
}
