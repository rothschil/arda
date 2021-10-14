package xyz.wongs.drunkard.framework.shiro.web.filter.captcha;

import com.google.code.kaptcha.Constants;
import org.apache.shiro.web.filter.AccessControlFilter;
import io.github.rothschil.common.utils.StringUtils;
import io.github.rothschil.common.utils.ShiroUtils;
import io.github.rothschil.war.constant.ShiroConstants;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 验证码过滤器
 *
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2019/10/9 - 21:35
 * @since 1.0.0
 */
public class CaptchaValidateFilter extends AccessControlFilter {
    /**
     * 是否开启验证码
     */
    private boolean captchaEnabled = true;

    /**
     * 验证码类型
     */
    private String captchaType = "math";

    public void setCaptchaEnabled(boolean captchaEnabled) {
        this.captchaEnabled = captchaEnabled;
    }

    public void setCaptchaType(String captchaType) {
        this.captchaType = captchaType;
    }

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        request.setAttribute(ShiroConstants.CURRENT_ENABLED, captchaEnabled);
        request.setAttribute(ShiroConstants.CURRENT_TYPE, captchaType);
        return super.onPreHandle(request, response, mappedValue);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 验证码禁用 或不是表单提交 允许访问
        if (!captchaEnabled || !"post".equalsIgnoreCase(httpServletRequest.getMethod())) {
            return true;
        }
        return validateResponse(httpServletRequest, httpServletRequest.getParameter(ShiroConstants.CURRENT_VALIDATECODE));
    }

    public boolean validateResponse(HttpServletRequest request, String validateCode) {
        Object obj = ShiroUtils.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String code = String.valueOf(obj != null ? obj : "");
        // 验证码清除，防止多次使用。
        request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        return !StringUtils.isEmpty(validateCode) && validateCode.equalsIgnoreCase(code);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        request.setAttribute(ShiroConstants.CURRENT_CAPTCHA, ShiroConstants.CAPTCHA_ERROR);
        return true;
    }
}
