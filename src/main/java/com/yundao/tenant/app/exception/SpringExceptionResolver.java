package com.yundao.tenant.app.exception;

import com.yundao.core.code.Result;
import com.yundao.core.constant.CommonConstant;
import com.yundao.core.exception.BaseException;
import com.yundao.core.jsonp.Jsonp;
import com.yundao.core.jsonp.JsonpUtils;
import com.yundao.core.log.Log;
import com.yundao.core.log.LogFactory;
import com.yundao.core.resolver.AbstractExceptionResolver;
import com.yundao.core.utils.BooleanUtils;
import com.yundao.core.utils.JsonUtils;
import com.yundao.core.utils.ReflectUtils;
import com.yundao.core.utils.ResponseUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常解析类
 *
 * @author jan
 * @create 2017-06-30 PM5:36
 **/

@Configuration
public class SpringExceptionResolver extends AbstractExceptionResolver {

	private static Log log = LogFactory.getLog(SpringExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		try {
			Result<String> exceptionResult = this.getExceptionResult(request, ex);

			// 打印异常信息
			StringBuilder logBuilder = new StringBuilder();
			logBuilder.append("showMessage=").append(exceptionResult.getMessage());
			logBuilder.append(",stackMessage=").append(exceptionResult.getResult());
			log.error(logBuilder.toString());

			// 校验是否是json
			Result<Boolean> result = Result.newResult(exceptionResult.getCode(), exceptionResult.getMessage(), null);
			String json = JsonUtils.objectToJson(result);
			if (handler instanceof HandlerMethod) {
				HandlerMethod method = (HandlerMethod) handler;
				Class<ResponseBody> clazz = ResponseBody.class;
				ResponseBody ajax = method.getBeanType().getAnnotation(clazz);
				if (ajax != null || ReflectUtils.getAnnotationByMethod(method.getMethod(), clazz) != null) {
					// 校验是否是jsonp
					String callback = request.getParameter(CommonConstant.CALLBACK);
					if (!BooleanUtils.isBlank(callback)
							&& method.getBean().getClass().getAnnotation(Jsonp.class) != null) {
						json = JsonpUtils.toJsonp(callback, json);
					}
				}
			}
			// 设置异常信息
			ResponseUtils.printlnJson(response, json);
			return new ModelAndView();
		} catch (Exception e) {
			log.error("异常解析时异常", e);
			ResponseUtils.printlnJson(response, JsonUtils.objectToJson(Result.newFailureResult(1007)));
			return new ModelAndView();
		}
	}

}
