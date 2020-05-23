package com.p6e.bounce.tool;

import com.p6e.bounce.model.dto.P6eProverbParamDto;
import com.p6e.bounce.model.dto.P6eProverbResultDto;
import com.p6e.bounce.service.P6eProverbService;
import com.p6e.bounce.utils.GsonUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 谚语 获取数据到数据库的工具类
 * 这里才用的是 jsonp 对数据进行爬取
 */
@Component
public class P6eProverbTool {

    private static final Logger logger = LoggerFactory.getLogger(P6eProverbTool.class);

    @Autowired
    private P6eProverbService p6eProverbService;

    public void execute() {
        try {
            //通过延迟2000毫秒然后再去请求可解决js异步加载获取不到数据的问题
            Document doc = Jsoup.connect("http://www.yuluju.com/lizhimingyan/11246.html").timeout(1000).get();
            // 通过class的名字得到（即XX）,一个数组对象Elements里面有我们想要的数据,至于这个div的值呢你打开浏览器按下F12就知道了;
            Elements elements = doc.getElementsByClass("content").select("div").select("div").select("span");
            for (Element element : elements) {
                // 打印出每一个节点的信息;你可以选择性的保留你想要的数据,一般都是获取个固定的索引;
                if (element != null) {
                    Elements es = element.getAllElements();
                    for (Element e : es) {
                        String text = e.text();
                        if (text.length() > 0 && text.charAt(0) >= 48 && text.charAt(0) <= 57) {
                            int start = 0, end = 0;
                            for (int i = 0; i < text.length(); i++) {
                                if (text.charAt(i) == '.') start = i;
                                if (text.charAt(i) == '—') end = i;
                            }
                            if (end - start > 2 && end + 1 < text.length()) {
                                P6eProverbParamDto p6eProverbParamDto = new P6eProverbParamDto();
                                p6eProverbParamDto.setAuthor(text.substring(end + 1));
                                p6eProverbParamDto.setContent(text.substring(start + 1, end - 1));
                                P6eProverbResultDto p6eProverbResultDto = p6eProverbService.create(p6eProverbParamDto);
                                if (p6eProverbResultDto.getError() == null) {
                                    logger.info("[ CREATE ] => SUCCESS ( " + GsonUtil.toJson(p6eProverbResultDto) + " ).");
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.gc(); // 回收创建的对象
        }
    }

}
