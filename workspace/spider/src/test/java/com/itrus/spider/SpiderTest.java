package com.wangxb.spider;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class SpiderTest {
    Logger logger = LoggerFactory.getLogger(SpiderTest.class);
    @Test
    public void test() throws IOException {
        logger.info("开始");
        WebClient webClient = getWebClient();
        updateCookie(webClient);
        String indexUrl = "http://192.168.100.120:92/contractweb/#/workBench/companyWorkBench";
        //indexUrl = "http://192.168.100.120:92/contractweb/#/contractManage/contractList";
        HtmlPage indexPage = getHtmlPage(webClient,indexUrl);

        wirtePage(indexPage,"index");


        List<HtmlListItem> listHtmlListItem = getLiItem(indexPage);
        HtmlPage childPage = null;
        int i = 0;
        do {
            if (listHtmlListItem.size()>i){
                HtmlListItem htmlListItem = listHtmlListItem.get(i);
                String childName = String.valueOf(i);
                childPage = ClickPage(webClient,htmlListItem);
                String title = childPage.getTitleText();
                if (StringUtils.isBlank(title) == false){
                    childName = title;
                }
                wirtePage(childPage, childName);
                i++;
                listHtmlListItem = getLiItem(childPage);
            }else{
                break;
            }
        }while (listHtmlListItem != null);


//        Document document = Jsoup.parse(pageXml);//获取html文档
//        List<Element> infoListEle = document.getElementById("feedCardContent").getElementsByAttributeValue("class", "feed-card-item");//获取元素节点等
//        infoListEle.forEach(element -> {
//            System.out.println(element.getElementsByTag("h2").first().getElementsByTag("a").text());
//            System.out.println(element.getElementsByTag("h2").first().getElementsByTag("a").attr("href"));
//        });
        logger.info("结束");
    }
    WebClient getWebClient(){
        WebClient webClient = new WebClient(BrowserVersion.CHROME);//新建一个模拟谷歌Chrome浏览器的浏览器客户端对象
        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX
        return webClient;
    }
    void updateCookie(WebClient webClient){
        CookieManager cookieManager = webClient.getCookieManager();
        Cookie ItrustToken_Cookie = new Cookie("192.168.100.120","itrustoken","eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhY2NvdW50IiwicGhvbmUiOiIxODMxMTQyMzMyMCIsInR5cGUiOiJhY2NvdW50IiwiZXhwIjoxNjI4NTkwNDYzLCJ1dWlkIjoiTDQ5OXEyN3ZwdGpxd3ciLCJpYXQiOjE2Mjg1ODk4NjMsImp0aSI6ImY1OGRiNjAwLTUxYWYtNDQyZS1hZjg5LTQzZTk3M2U2MTQxZCJ9.oxYXHGyVVHKeXbrwnjmZY_V7Npt2TnpmE7gFTAlxiiMYbfMZ8D8TaTr6fyDzcimOsPvHH8P5RjOuleYm9ZfXSB0u4iZGDVeUH3Xf1uQ0OoFoJd9MporDkO-11y3-D9befwUytVSXXdDfmyGDlkhWL1aP92Rf9jwozuyPhhJB5lM");
        Cookie CONSOLEID_Cookie = new Cookie("192.168.100.120","CONSOLEID","53aa6a8b1f13070f274a547c926e761e");
        Cookie JSESSIONID_Cookie = new Cookie("192.168.100.120","JSESSIONID","L499q27vptjqww");
        Cookie useruuid_Cookie = new Cookie("192.168.100.120","useruuid","L499q27vptjqww");

        cookieManager.addCookie(ItrustToken_Cookie);
        cookieManager.addCookie(CONSOLEID_Cookie);
        cookieManager.addCookie(JSESSIONID_Cookie);
        cookieManager.addCookie(useruuid_Cookie);
    }


    HtmlPage getHtmlPage(WebClient webClient,String url){
        HtmlPage page = null;
        try {
            page = webClient.getPage(url);//尝试加载上面图片例子给出的网页
            webClient.waitForBackgroundJavaScript(10000);//异步JS执行需要耗时,所以这里线程要阻塞30秒,等待异步JS执行结束
        } catch (Exception e) {
            logger.error("获取url失败:{}",url,e);
        }
        return page;
    }

    void writeFile(String fileName,String fileContent) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        fileOutputStream.write(fileContent.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    void wirtePage(HtmlPage htmlPage,String fileName) throws IOException {
        String pageHtml = htmlPage.asNormalizedText();
        String htmlFileName = "d:\\html\\"+ fileName + ".html";
        writeFile(htmlFileName,pageHtml);

        String pageXml = htmlPage.asXml();//直接将加载完成的页面转换成xml格式的字符串
        String xmlFileName = "d:\\html\\"+ fileName + ".xml";
        writeFile(xmlFileName,pageXml);
    }

    List<HtmlListItem> getLiItem(HtmlPage htmlPage) {
        if (htmlPage == null){
            return null;
        }
        List<HtmlListItem> listHtmlListItem = htmlPage.getByXPath("//li[@role='menuitem']");
        return listHtmlListItem;
    }

    HtmlPage ClickPage(WebClient webClient,HtmlListItem htmlListItem) throws IOException {
        HtmlPage childPage = htmlListItem.click();
        webClient.waitForBackgroundJavaScript(10000);//异步JS执行需要耗时,所以这里线程要阻塞30秒,等待异步JS执行结束
        return childPage;
    }

}
