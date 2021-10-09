package xyz.wongs.drunkard.framework.shiro.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import xyz.wongs.drunkard.framework.shiro.realm.UserRealm;

/**
 * 用户授权信息
 *
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2021/10/9 - 21:35
 * @since 1.0.0
 */
public class AuthorizationUtils {
    /**
     * 清理所有用户授权信息缓存
     */
    public static void clearAllCachedAuthorizationInfo() {
        getUserRealm().clearAllCachedAuthorizationInfo();
    }

    /**
     * 获取自定义Realm
     */
    public static UserRealm getUserRealm() {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        return (UserRealm) rsm.getRealms().iterator().next();
    }
}
