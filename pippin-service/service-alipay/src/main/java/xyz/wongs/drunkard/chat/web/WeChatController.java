package xyz.wongs.drunkard.chat.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import xyz.wongs.drunkard.common.utils.StringUtils;
import xyz.wongs.drunkard.common.utils.bean.SpringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RequestMapping("im")
@RestController
public class WeChatController {

    /**
     * 登陆界面
     */
    @GetMapping("/")
    public ModelAndView login() {
        return new ModelAndView("/login");
    }

    /**
     * 聊天界面
     */
    @GetMapping("/index")
    public ModelAndView index(String username) throws UnknownHostException {
        if (StringUtils.isEmpty(username)) {
            username = "匿名用户";
        }
        HttpServletRequest request = SpringUtils.getHttpServletRequest();

        ModelAndView mav = new ModelAndView("/chat");
        mav.addObject("username", username);
        mav.addObject("webSocketUrl", "ws://"+ InetAddress.getLocalHost().getHostAddress()+":"+request.getServerPort()+request.getContextPath()+"/chat");
        return mav;
    }

}
