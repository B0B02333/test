package test.lingba8.er24;

import static com.rongji.dfish.framework.FrameworkConstants.LOGIN_USER_KEY;
import static com.rongji.dfish.framework.FrameworkHelper.getLocale;
import static com.rongji.dfish.util.Utils.outPutXML;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.rongji.cms.commons.Constants;
import com.rongji.cms.commons.Diff_match_patch;
import com.rongji.cms.commons.HtmlUtil;
import com.rongji.cms.commons.component.RJCMSEditor;
import com.rongji.cms.persistence.CmsArHistory;
import com.rongji.cms.persistence.CmsModel;
import com.rongji.cms.service.ServiceLocator;
import com.rongji.cms.webapp.BaseActionController;
import com.rongji.cms.webapp.cm.business.ModelMethods;
import com.rongji.cms.webapp.cm.view.ArticleHisVersionView;
import com.rongji.dfish.dao.Page;
import com.rongji.dfish.engines.xmltmpl.BaseView;
import com.rongji.dfish.engines.xmltmpl.DialogPosition;
import com.rongji.dfish.engines.xmltmpl.Scroll;
import com.rongji.dfish.engines.xmltmpl.ViewFactory;
import com.rongji.dfish.engines.xmltmpl.command.AlertCommand;
import com.rongji.dfish.engines.xmltmpl.command.CommandGroup;
import com.rongji.dfish.engines.xmltmpl.command.JSCommand;
import com.rongji.dfish.engines.xmltmpl.command.UpdateCommand;
import com.rongji.dfish.engines.xmltmpl.command.jsdoc.JSCmdLib;
import com.rongji.dfish.engines.xmltmpl.component.FormPanel;
import com.rongji.dfish.framework.FrameworkHelper;
import com.rongji.dfish.framework.handler.Framework;
import com.rongji.dfish.util.DateUtils;
import com.rongji.dfish.util.Utils;
import com.rongji.dfish.util.String.StringUtils;
/**
 * 文章历史版本管理动作类
 * <p>Title: 榕基RJ-CMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 榕基软件开发有限公司</p>
 * 
 * @author HQJ
 * @version 1.0
 * @since	1.0.0	HQJ		2010-01-04
 */
public class ArticleHisVersionController extends BaseActionController {
	/**
	 * 文章回收站管理首页
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Locale loc = getLocale(request);
		ViewFactory viewFactory = ViewFactory.getViewFactory(Framework.getSkin(request));
		
		String userId = (String) request.getSession().getAttribute(LOGIN_USER_KEY);
		String arId = Utils.getParameter(request, "arId");
		
		String currentPage = request.getParameter("cp");
		Page page = FrameworkHelper.createPersonalPage(userId, currentPage); 
		List dataList = ServiceLocator.getArticleHisVersionService().getArticleHisVersions(arId);
		// 权限校验(是否有管理站点/栏目文章回收站列表的权限)
		// --------------------待完善 ----------------------------------------------
		String alert = null;
		if (StringUtils.isNotBlank(alert)) {
			outPutXML(response, new AlertCommand("alt", alert, "img/p/alert-warn.gif", DialogPosition.middle, 5));
			return null;
		}
		BaseView view = ArticleHisVersionView.buildIndexView(loc, viewFactory, page, dataList, null, null, arId);
//		view.addLoadEvent("loadPlayer()");//
		outPutXML(response, view);
		return null;
	}
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] ids = request.getParameterValues("selectItem"); // 获取要删除文章的ID序列
		if ((ids != null) && (ids.length > 0)) { 
			for (String id : ids) {
				ServiceLocator.getArticleHisVersionService().deleteArticleVersion(id, false);
			}
			AlertCommand ac = new AlertCommand("ac", "删除成功",
					"img/p/alert-info.gif", DialogPosition.southeast, 3);
			CommandGroup cg = new CommandGroup(null);
			cg.add(ac);
			cg.add(JSCmdLib.reload(null, null));
			outPutXML(response, cg);
		} else {
			AlertCommand alert = new AlertCommand("alt", "请先选择要删除的文章！", "img/p/alert-warn.gif", DialogPosition.middle, 5);
			outPutXML(response, alert);
			return null;
		}
		return null;
	}
		
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView restore(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Locale loc = getLocale(request);
		ViewFactory viewFactory = ViewFactory.getViewFactory(Framework.getSkin(request));
		
		String userId = (String) request.getSession().getAttribute(LOGIN_USER_KEY);
		String arId = Utils.getParameter(request, "arId");
		String[] ids = request.getParameterValues("selectItem"); // 获取要还原文章的ID序列
		if (ids != null && ids.length ==1 && Utils.notEmpty(arId)) { 
//			CmsArticle ar = DAOFactory.getCmsArticleDao().findById(arId);
//			ar.setArContent(ServiceLocator.getArticleHisVersionService().getArticleHisVersionById(arId,ids[0]).getVerContent());
//			ServiceLocator.getArticleService().updateArticle(ar);
//			System.out.println(ServiceLocator.getArticleHisVersionService().getArticleHisVersionById(arId,ids[0]).getVerContent());
			CommandGroup cg = new CommandGroup(null);
			cg.add(new JSCommand("","VM('/').f('arContent').edt.setContent('"+
					ServiceLocator.getArticleHisVersionService().getArticleHisVersionById(arId,ids[0]).getVerContent()
					+"');"));//已将内容恢复至编辑器内!
//			cg.add(new JSCommand("","VM('/').reload();"));
			AlertCommand ac = new AlertCommand("ac", "内容恢复成功！",
					"img/p/alert-info.gif", DialogPosition.southeast, 3);
			cg.add(ac);
			outPutXML(response, cg);
		} else {
			AlertCommand alert = new AlertCommand("alt", "请先选择一个要还原的版本！", "img/p/alert-warn.gif", DialogPosition.middle, 5);
			outPutXML(response, alert);
			return null;
		}
		return null;
	}
	
	/**
	 * 清空回收站
	 * <p>删除站点回收站的所有站点</p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView clearAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Locale loc = getLocale(request);
		ViewFactory viewFactory = ViewFactory.getViewFactory(Framework.getSkin(request));
		// 获取数据
		String userId = (String) request.getSession().getAttribute(LOGIN_USER_KEY);
		String siteId = Utils.getParameter(request, "siteId");
		// 权限校验(清空回收站点权限)
		List list = null;
		if (Utils.notEmpty(list)) {
			// 根据站点ID获取在站点回收站可删除的子站点列表
		} else {
			AlertCommand alert = new AlertCommand("alt", "文章回收站没有可以删除的文章!", "img/p/alert-warn.gif", DialogPosition.middle, 5);
			outPutXML(response, alert);
			return null;
		}
		return null;
	}
	
	/**
	 * 还原所有
	 * <p>还原站点回收站的所有站点</p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView restoreAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Locale loc = getLocale(request);
		ViewFactory viewFactory = ViewFactory.getViewFactory(Framework.getSkin(request));
		// 获取数据
		String userId = (String) request.getSession().getAttribute(LOGIN_USER_KEY);
		String siteId = Utils.getParameter(request, "siteId");
		// 权限校验(还原所有站点权限)
		List list = null;
		if (Utils.notEmpty(list)) {
			// 根据站点ID获取在站点回收站可还原的子站点列表
		} else {
			AlertCommand alert = new AlertCommand("alt", "文章回收站没有可以还原的站文章!", "img/p/alert-warn.gif", DialogPosition.middle, 5);
			outPutXML(response, alert);
			return null;
		}
		return null;
	}
	
	/**
	 * 查询
	 * <p></p>
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Locale loc = getLocale(request);
		ViewFactory viewFactory = ViewFactory.getViewFactory(Framework.getSkin(request));
		String userId = (String) request.getSession().getAttribute(LOGIN_USER_KEY);
		String currentPage = request.getParameter("cp");
		// 实例page，设置当前页和每页显示条数
		Page page = FrameworkHelper.createPersonalPage(userId, currentPage);
		List<CmsModel> dataList = ModelMethods.findModelList(request, page);
		// 刷新列表和翻页
//		CommandGroup cg = ModelView.updateView(request, loc, viewFactory, dataList, page, null, 0, 0);
//		outPutXML(response, cg);
		return null;
	}
	
	/**
	 * 翻页
	 * <p>翻页面板</p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView changePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Locale loc = getLocale(request);
		ViewFactory viewFactory = ViewFactory.getViewFactory(Framework.getSkin(request));
		String userId = (String) request.getSession().getAttribute(LOGIN_USER_KEY);
		String currentPage = request.getParameter("cp");
		// 实例page，设置当前页和每页显示条数
		Page page = FrameworkHelper.createPersonalPage(userId, currentPage);
		List<CmsModel> dataList = ModelMethods.findModelList(request, page);
		// 刷新列表和翻页
//		CommandGroup cg = ModelView.updateView(request, loc, viewFactory, dataList, page, null, 0, 0);
//		outPutXML(response, cg);
		return null;
	}
	
	public ModelAndView compareVersion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Locale loc = getLocale(request);
		String arId=Utils.getParameter(request,"arId");
		String[] checkItems = request.getParameterValues("selectItem");
		String contents = new String("请选择两个正确的版本进行比较！");
		if ((checkItems != null) && (checkItems.length == 2)) {
			if(Utils.notEmpty(checkItems[0]) && Utils.notEmpty(checkItems[1])){
			String ver1 = HtmlUtil.filterHtmlTags(ServiceLocator.getArticleHisVersionService().getArticleHisVersionById(arId,checkItems[1]).getVerContent());//lindent todo
			String ver2 = HtmlUtil.filterHtmlTags(ServiceLocator.getArticleHisVersionService().getArticleHisVersionById(arId,checkItems[0]).getVerContent());//lindent todo
//			String ver1 = "启动安装，修改默认安装目录，例如改为D:\\Program Files\\PostgreSQL\\8.3，在安装为服务时不选，即只安装程序，不初始化数据库，也不安装为服务。";
//			String ver2 = "启动安装后，修改安装目录，例如改为tgreSQL\\8.5，在安装为服务时不选，即只你好吗安装程序，不初始化关系型数据库，也不注册为服务。";
				Diff_match_patch dmp = new Diff_match_patch();
			contents = dmp.getHtmlDiffString(ver1,ver2);
			}
		}
		
		ViewFactory viewFactory = ViewFactory.getViewFactory(FrameworkHelper
				.getSkin(request), loc);
		CommandGroup cg = ArticleHisVersionView.getCompareVersionUpdateCommand(viewFactory,contents.replace("&para;", ""));//replace("&para;", "")过滤diff中产生出来的换行符

		outPutXML(response, cg);
		return null;
	}
	
	public ModelAndView getVerContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Locale loc = getLocale(request);
		String arId=Utils.getParameter(request,"arId");
		String hisId=Utils.getParameter(request,"hisId");
		
		String contents = new String("");
		if(Utils.notEmpty(arId) && Utils.notEmpty(hisId)){
			contents = ServiceLocator.getArticleHisVersionService().getArticleHisVersionById(arId,hisId).getVerContent();
		}
		ViewFactory viewFactory = ViewFactory.getViewFactory(FrameworkHelper.getSkin(request), loc);
		CommandGroup cg = ArticleHisVersionView.getCompareVersionUpdateCommand(viewFactory,contents);

		outPutXML(response, cg);
		return null;
	}
	
	public ModelAndView saveVersion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CmsArHistory arHis = new CmsArHistory();
		arHis.setCreateTime(new Date());
		arHis.setArTitle(StringUtils.strCut(Utils.getParameter(request,"arTitle"),490,""));
		arHis.setArId(Utils.getParameter(request,"arId"));
		arHis.setVerContent(StringUtils.unescape(Utils.getParameter(request,"arContent_es")));
		arHis.setVerStatus(Constants.AR_HISTORY_OK);
		arHis.setModifyReason(StringUtils.strCut(Utils.getParameter(request,"modifyReason"),490,""));
		arHis.setCreatorId((String) request.getSession().getAttribute(LOGIN_USER_KEY));
		AlertCommand ac =null;
		StringBuilder sb = new StringBuilder(200);
		try {
			ServiceLocator.getArticleHisVersionService().saveArticleHisVersion(arHis);
			ac = new AlertCommand("ac", "版本保存成功",
					"img/p/alert-info.gif", DialogPosition.southeast, 3);
		} catch (Exception e) {
			ac = new AlertCommand("ac", "版本保存失败！",
					"img/p/alert-crack.gif", DialogPosition.middle, 0);
		}
		CommandGroup cg = new CommandGroup("cg");
		cg.setPath("/search_dlg");
		cg.add(ac);
		cg.add(ac);
		sb.append("{hisId:'").append(arHis.getHisId()).append("',")
		  .append("verId:'").append(arHis.getVerId()).append("',")
		  .append("time:'").append(DateUtils.toDateTimeString(arHis.getCreateTime()))
		  .append("',").append("arId:'").append(arHis.getArId()).append("'}");
		JSCommand js = new JSCommand(null,sb.insert(0, "VM('/').f('arContent').edt.saveOrUpdateHistory(").append(")").toString());
		cg.add(js);
		cg.add(JSCmdLib.dialogClose(null));
		outPutXML(response, cg);
		return null;
	}

	
	public ModelAndView saveSpecialVersion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StringBuilder sb = new StringBuilder(100);
		CmsArHistory arHis = new CmsArHistory();
		arHis.setCreateTime(new Date());
		arHis.setArTitle(StringUtils.strCut(Utils.getParameter(request,"arTitle"),490,""));
		arHis.setArId(Utils.getParameter(request,"arId"));
		String arContent = Utils.getParameter(request,"arContent");
		arContent = StringUtils.unescape(arContent);
		arHis.setVerContent(arContent);
		arHis.setVerStatus(Constants.AR_HISTORY_OK);
		arHis.setModifyReason(Utils.getParameter(request,"modifyReason"));
		arHis.setCreatorId((String) request.getSession().getAttribute(LOGIN_USER_KEY));
		ServiceLocator.getArticleHisVersionService().saveSpecialArticleHisVersion(arHis);
		AlertCommand ac = new AlertCommand("ac", "自动保存版本成功",
				"img/p/alert-info.gif", DialogPosition.southeast, 3);
		CommandGroup cg = new CommandGroup("");
		//cg.add(ac);
		sb.append("{hisId:'").append(arHis.getHisId()).append("',")
		  .append("verId:'").append(arHis.getVerId()).append("',")
		  .append("time:'").append(DateUtils.toDateTimeString(arHis.getCreateTime()))
		  .append("',").append("arId:'").append(arHis.getArId()).append("'}");
		JSCommand js = new JSCommand(null,sb.insert(0, "VM(this).f('arContent').edt.saveOrUpdateHistory(").append(")").toString());
		cg.add(js);
		outPutXML(response, cg);
		return null;
	}

	
	/**
	 * 初始化历史版本列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView initHistoryVersion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String arId = Utils.getParameter(request, "arId");
		List<CmsArHistory> dataList = ServiceLocator.getArticleHisVersionService()
										.getArticleHisVersions(arId);
		StringBuilder sb = new StringBuilder(200);
		sb.append("[");
		if(Utils.notEmpty(dataList)) {
			for(CmsArHistory his:dataList) {
				sb.append("{hisId:'").append(his.getHisId()).append("',")
				  .append("verId:'").append(his.getVerId()).append("',")
				  .append("time:'").append(DateUtils.toDateTimeString(his.getCreateTime()))
				  .append("',arId:'").append(arId).append("'},");
			}
			sb = sb.deleteCharAt(sb.length()-1);
		}
		
		sb.append("]");
		JSCommand js = new JSCommand(null,sb.insert(0, "VM('/').f('arContent').edt.addAllHistory(").append(")").toString());
		outPutXML(response, js);
		return null;
	}
	
	public ModelAndView recDialog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Locale loc = getLocale(request);
		ViewFactory viewFactory = ViewFactory.getViewFactory(Framework.getSkin(request));
		BaseView view = null;
		view = ArticleHisVersionView.buildRecDialog(loc, viewFactory);
		outPutXML(response, view);
		return null;
	}
	
	
	public ModelAndView recSpecialVersion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Locale loc = getLocale(request);
		ViewFactory viewFactory = ViewFactory.getViewFactory(Framework.getSkin(request));
		String arId = Utils.getParameter(request,"arid");
		String verId = "0.9";
		String hisId = ServiceLocator.getArticleHisVersionService().hasSpecialArticleHisVersion(arId, verId);
		CmsArHistory cah = null;
		CommandGroup cg = new CommandGroup("");
		if(hisId!=null){
			cah = ServiceLocator.getArticleHisVersionService().getArticleHisVersionById(hisId);
			cg.setPath("/");
			UpdateCommand uc = new UpdateCommand("");
			FormPanel editorPanel = viewFactory.getDefaultFormPanel();
			editorPanel.setId("f_rjEdit");
			editorPanel.setScroll(Scroll.hidden);
			editorPanel.setCellspacing(0);
			editorPanel.setCellpadding(0);
			RJCMSEditor rjEdt = new RJCMSEditor("arContent", "正&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;文",
					Utils.notEmpty(cah.getVerContent())?cah.getVerContent():"", Integer.MAX_VALUE);
			rjEdt.setRows(40);
			rjEdt.setFullLine(true);
			editorPanel.add(rjEdt);
			uc.setContent(editorPanel);
			AlertCommand ac = new AlertCommand("alertcmd","恢复数据成功!","img/p/alert-info.gif", DialogPosition.southeast, 3); 
			cg.add(ac);
			cg.add(uc);
		}else{
			AlertCommand ac = new AlertCommand("alertcmd","恢复失败，数据不存在，请开启自动保存功能!","img/p/alert-crack.gif", DialogPosition.middle, 0);
			cg.add(ac);
		}
		
		outPutXML(response, cg);
		return null;
	}
	
	public ModelAndView modifyReasonDialog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Locale loc = getLocale(request);
		ViewFactory viewFactory = ViewFactory.getViewFactory(Framework.getSkin(request));
		String arTitle = StringUtils.strCut(Utils.getParameter(request,"arTitle"),490,"");
		String arId = Utils.getParameter(request,"arId");
		String arContent_es = Utils.getParameter(request,"arContent_es");
		BaseView view = null;
		view = ArticleHisVersionView.buildMRDialog(loc, viewFactory,arTitle,arId,arContent_es);
		outPutXML(response, view);
		return null;
	}

}
