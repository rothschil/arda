package xyz.wongs.drunkard.framework.shiro.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import xyz.wongs.drunkard.common.constant.Constants;
import xyz.wongs.drunkard.base.response.enums.Status;
import xyz.wongs.drunkard.common.exception.DrunkardException;
import xyz.wongs.drunkard.common.utils.DateUtils;
import xyz.wongs.drunkard.common.utils.MessageUtils;
import xyz.wongs.drunkard.common.utils.ServletUtils;
import xyz.wongs.drunkard.common.enums.UserStatus;
import xyz.wongs.drunkard.common.utils.ShiroUtils;
import xyz.wongs.drunkard.framework.manager.AsyncManager;
import xyz.wongs.drunkard.framework.manager.factory.AsyncFactory;
import xyz.wongs.drunkard.war.constant.ShiroConstants;
import xyz.wongs.drunkard.war.constant.UserConstants;
import xyz.wongs.drunkard.war.core.domain.SysUser;
import xyz.wongs.drunkard.war.core.service.ISysUserService;

/**
 * 登录校验方法
 *
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2019/10/9 - 21:34
 * @since 1.0.0
 */
@Component
public class SysLoginService {

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private ISysUserService userService;

    /**
     * 登录
     */
    public SysUser login(String username, String password) {
        // 验证码校验
        if (ShiroConstants.CAPTCHA_ERROR.equals(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA))) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new DrunkardException(Status.VERIFICATION_CODE_INCORRECT);
        }
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
            throw new DrunkardException(Status.USER_NOT_LOGIN_ERROR);
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new DrunkardException(Status.USER_NOT_LOGIN_ERROR);
        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new DrunkardException(Status.USER_NOT_LOGIN_ERROR);
        }

        // 查询用户信息
        SysUser user = userService.selectUserByLoginName(username);

        if (user == null) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.not.exists")));
            throw new DrunkardException(Status.USER_NOT_LOGIN_ERROR);
        }

        if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.delete")));
            throw new DrunkardException(Status.USER_WAS_DEL);
        }

        if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.blocked", user.getRemark())));
            throw new DrunkardException(Status.USER_WAS_LOCK);
        }

        passwordService.validate(user, password);

        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        recordLoginInfo(user);
        return user;
    }

    /**
     * 记录登录信息
     */
    public void recordLoginInfo(SysUser user) {
        user.setLoginIp(ShiroUtils.getIp());
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserInfo(user);
    }
}
