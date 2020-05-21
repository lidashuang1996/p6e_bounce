package com.p6e.bounce.controller.geetest;

import com.p6e.bounce.cache.IP6eCacheGeetest;
import com.p6e.bounce.controller.support.P6eBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

@RestController
@RequestMapping("/geetest")
public class P6eGeetestController extends P6eBaseController {

    @Autowired
    private IP6eCacheGeetest p6eCacheGeetest;

    @GetMapping("/first")
    protected void first(HttpServletRequest request, HttpServletResponse response) {
        try {
            String client = getClient(request);
            if (!client.isEmpty()) {
                GeetestLib gtLib = new GeetestLib(GeetestConfig.geetestId, GeetestConfig.geetestKey);
                String digestmod = "md5"; //必传参数，此版本sdk可支持md5、sha256、hmac-sha256，md5之外的算法需特殊配置的账号，联系极验客服
                // 进行一次验证，得到结果
                GeetestLibResult result = gtLib.register(structureParam(digestmod, client), digestmod);
                p6eCacheGeetest.set(client, String.valueOf(result.getStatus()));
                PrintWriter out = response.getWriter();
                // 注意，不要更改返回的结构和值类型
                out.println(result.getData());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/second")
    protected void second(HttpServletRequest request, HttpServletResponse response) {
        try {
            String client = getClient(request);
            if (!client.isEmpty()) {
                GeetestLib gtLib = new GeetestLib(GeetestConfig.geetestId, GeetestConfig.geetestKey);

                String challenge = request.getParameter(GeetestConfig.GEETEST_CHALLENGE);
                String validate = request.getParameter(GeetestConfig.GEETEST_VALIDATE);
                String seccode = request.getParameter(GeetestConfig.GEETEST_SECCODE);

                PrintWriter out = response.getWriter();

                int gt_server_status_code;
                try {
                    gt_server_status_code = Integer.valueOf(p6eCacheGeetest.get(client));
                } catch (Exception e) {
                    gtLib.gtlog(
                            "SecondValidateServlet.doPost()：二次验证validate：session取key发生异常，"
                                    + e.toString());
                    out.println("{\"status\":\"fail\"}");
                    return;
                }
                String digestmod = "md5"; //必传参数，此版本sdk可支持md5、sha256、hmac-sha256，md5之外的算法需特殊配置的账号，联系极验客服
                GeetestLibResult result;
                // gt_server_status_code 为1代表一次验证register正常，走正常二次验证模式；为0代表一次验证异常，走failback模式
                if (gt_server_status_code == 1) {
                    //gt-server正常，向极验服务器发起二次验证
                    result = gtLib.successValidate(challenge, validate, seccode, structureParam(digestmod, client), digestmod);
                } else {
                    // gt-server非正常情况，进行failback模式本地验证
                    result = gtLib.failValidate(challenge, validate, seccode);
                }

                // 注意，不要更改返回的结构和值类型
                out.println(result.getData());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, String> structureParam(String digestmod, String client) {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("digestmod", digestmod);
        //自定义参数,可选择添加
        paramMap.put("user_id", client); //网站用户id
        paramMap.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        paramMap.put("ip_address", obtainIp()); //传输用户请求验证时所携带的IP
        return paramMap;
    }

    private String getClient(HttpServletRequest request) {
        String client = "";
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            if ("client".equals(name)) {
                client = request.getHeader(name);
                break;
            }
        }
        return client;
    }
}
