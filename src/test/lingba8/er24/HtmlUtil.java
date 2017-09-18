package com.rongji.cms.commons;

import org.springframework.web.util.HtmlUtils;

import com.rongji.dfish.util.Utils;

public class HtmlUtil {

	/**
	 * 将 HTML 特殊字符转义为 HTML 通用转义序列
	 * @param input
	 * @return
	 */
	public static String htmlEscape(String input) {
		return HtmlUtil.htmlEscape(input);
	}
	
	public static String htmlUnescape(String input){
		return HtmlUtil.htmlUnescape(input);
	}

	public static String filterHtmlTags(String srcStr){
		String result = new String();
		if(Utils.notEmpty(srcStr)){
			result = srcStr.replaceAll(" ", "").replaceAll("&nbsp;", "").replaceAll("<[.[^<]]*>", "");
		}
		return result;
	
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringBuilder str = new StringBuilder().append("<dl>&nbsp  <dt><strong>百科消息</strong>：")
		.append("</dt></dl><dl>  <dd><a href=\"http://www.baidu.com/search/baike/usertask/qiangciduoli/list.html\" target=\"_blank\">抢词多礼活动获奖公告</a></dd></dl><dl>  <dd><a href=\"http://baike.baidu.com/quanwei/\" target=\"_blank\">百度百科权威合作</a></dd></dl><dl>  <dd><a href=\"http://baike.baidu.com/cms/topic/master/index.html\" target=\"_blank\">百科分类管理主页</a></dd></dl><dl>  <dd><a href=\"http://hi.baidu.com/%B0%D9%B6%C8%B0%D9%BF%C6%B9%AB%B8%E6/blog/item/5ee765f04f4960c97831aa57.html\" target=\"_blank\">新编辑模块功能介绍</a></dd></dl>");
		String r = filterHtmlTags("<dl>  <dt><strong>百科消息</strong>：</dt></dl><dl>  <dd><a href=\"http://www.baidu.com/search/baike/usertask/qiangciduoli/list.html\" target=\"_blank\">抢词多礼活动获奖公告</a></dd></dl><dl>  <dd><a href=\"http://baike.baidu.com/quanwei/\" target=\"_blank\">百度百科权威合作</a></dd></dl><dl>  <dd><a href=\"http://baike.baidu.com/cms/topic/master/index.html\" target=\"_blank\">百科分类管理主页</a></dd></dl><dl>  <dd><a href=\"http://hi.baidu.com/%B0%D9%B6%C8%B0%D9%BF%C6%B9%AB%B8%E6/blog/item/5ee765f04f4960c97831aa57.html\" target=\"_blank\">新编辑模块功能介绍</a></dd></dl>");
		//System.out.println(r);
		System.out.println(HtmlUtil.htmlEscape(str.toString()));
		System.out.println(HtmlUtil.htmlUnescape(str.toString()));
	}
}
