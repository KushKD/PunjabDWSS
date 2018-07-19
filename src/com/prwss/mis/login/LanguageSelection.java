/**
 * 
 */
package com.prwss.mis.login;

import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * @author bhsingh
 *
 */
public class LanguageSelection extends DispatchAction{
	private final static ConcurrentHashMap<String, Locale> cache =
			new ConcurrentHashMap<String, Locale>(32);

	public ActionForward english(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
		throws Exception {

			request.getSession().setAttribute(
					Globals.LOCALE_KEY, Locale.ENGLISH);

			return mapping.findForward("success");
		}

		public ActionForward punjabi(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
		throws Exception {
		
			request.getSession().setAttribute(
					Globals.LOCALE_KEY,Locale.FRANCE);

			return mapping.findForward("success");
		}
		  private static Locale createSingleton(String key, String language, String country) {
				Locale locale = new Locale(language, country);
				cache.put(key, locale);
				return locale;
			    }

}
