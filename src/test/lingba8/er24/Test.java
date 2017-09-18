package test.lingba8.er24;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import test.lingba8.er24.Diff_match_patch.Diff;

public class Test {

	public static void main(String[] arge) {
		Diff_match_patch dmp = new Diff_match_patch();
		String der = dmp.getHtmlDiffString("我方在“福市财政资金补助项目管理系统”的项目实施过程，为保证该项目的工程进度、质量，符合数办项目的实施管理规范与要求。",
				"我方在“福州市财政资金补助项目管理系统”的项目实施过程，为保证该haha项目的工程进度、质量，符合目的实施管理规范与要求。");
		System.out.println(der);

	}

	public static void test2() {
		Diff_match_patch diff_match_patch = new Diff_match_patch();
		List<Map<String, String>> data = g.fromJson(sc.decrypt(strr),new TypeToken<List<Map<String, String>>>() {}.getType());
		List<Object[]> viewList = new ArrayList<Object[]>();

		for (Map<String, String> map : data) {

			String altbe = map.get("altbe");
			String altaf = map.get("altaf");
			altbe = altbe.replace("\n", "<br>").replace(";", "<br>");
			altaf = map.get("altaf").replace("\n", "<br>").replace(";", "<br>");
			LinkedList<Diff> t = diff_match_patch.diff_main(HtmlUtil.filterHtmlTags(map.get("altbe")),
					HtmlUtil.filterHtmlTags(map.get("altaf")));
			for (Diff diff : t) {
				if (!"EQUAL".equals(diff.operation.toString())) {
					altbe = altbe.replace(diff.text, "<em class='f-required'>" + diff.text + "</em>");
					altaf = altaf.replace(diff.text, "<em class='f-required'>" + diff.text + "</em>");
				}
			}

			viewList.add(new Object[] { map.get("changedate").substring(0, 11), map.get("altitem"), altbe, altaf });
		}
	}
	public static String strr = "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"
	+ "/RQI0wlTIDTzu7/+y/8D4KY6f3N4AQA=";
}
