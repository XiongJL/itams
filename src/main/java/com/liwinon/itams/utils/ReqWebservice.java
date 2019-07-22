package com.liwinon.itams.utils;

import com.liwinon.itams.dao.primaryRepo.AssetsDao;
import com.liwinon.itams.entity.primay.Assets;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 请求调用webservice
 */
@Component
public class ReqWebservice {
    @Autowired
    AssetsDao assetsDao;
    private static String url="http://172.30.7.56/sys/webservice/kmReviewWebserviceService?wsdl";
    public String postData(String userid,String DeviceID) throws IOException {
        String responseData = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://172.30.7.56/sys/webservice/kmReviewWebserviceService?wsdl");
        httpPost.setHeader("Content-Type","text/xml");
        String xml = getXml(userid,DeviceID);
        if (xml==null){
            return "设备编号不存在!";
        } else if (xml.indexOf("流程")!=-1) {
            return "该资产已在流程审批中!";
        }
        System.out.println(xml);
        StringEntity entityParams = new StringEntity(xml,Consts.UTF_8);
        httpPost.setEntity(entityParams);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseData = EntityUtils.toString(response.getEntity(),"utf-8");
                System.out.println(responseData);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            response.close();
            httpClient.close();
        }
        return null;
    }

    /**
     * 获取准备发送的xml
     * @param userid 工号
     * @return
     */
    public String getXml(String userid,String DeviceID){
        userid = "100501002";  //测试用
        Assets a =  assetsDao.findByDeviceID(DeviceID);
        if (a==null){
            return null;
        }else if (a.getAState()=="流程中"){
            return "该资产已在流程审批中!";
        }
        String param = "";
        param = "fd_asset_detail.fd_assets_types:'"+a.getAssetsType()+"'," +
                "fd_asset_detail.fd_assets_class:'"+a.getAssetsCategory()+"'," +
                "fd_asset_detail.fd_asset_number:'"+a.getAssetsID()+"'," +
                "fd_asset_detail.fd_equipment_number:'"+a.getDeviceID()+"'," +
                "fd_asset_detail.fd_device_name:'"+a.getAssetsName()+"'," +
                "fd_asset_detail.fd_unit_type:'"+a.getModels()+"'," +
             //   "fd_disposal_type:'"+a.getAssetsName()+"'," +  //处置类型
                "fd_asset_detail.fd_disposal_position:'"+a.getLocation()+"'," +
                "fd_asset_detail.fd_asset_states:'"+a.getAState()+"'" ;
        String xml="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.review.km.kmss.landray.com/\" xmlns:mro=\"http://mro.webservice.review.km.kmss.landray.com/\">  \n" +
                "  <soapenv:Header> \n" +
                "    <tns:RequestSOAPHeader xmlns:tns=\"http://sys.webservice.client\">  \n" +
                "      <tns:user>erptest</tns:user>  \n" +
                "      <tns:password>2f92bb5f59eb370d484914dbaa6ec214</tns:password> \n" +
                "    </tns:RequestSOAPHeader></soapenv:Header>\n" +
                "   <soapenv:Body> \n" +
                "    <web:addReview>\n" +
                "         <!--Optional:-->\n" +
                "         <arg0> \n" +
                "        <!--Zero or more repetitions:-->\n" +
                "\t\t\t   <attachmentForms> \n" +
                "               <fdKey></fdKey>\n" +
                "               <fdFileName></fdFileName>\n" +
                "               <fdAttachment>\n" +
                "         </fdAttachment>\n" +
                "            </attachmentForms>\n" +
                "\t\t\t<attachmentForms> \n" +
                "\t\t\t   <fdKey></fdKey>\n" +
                "               <fdFileName></fdFileName>\n" +
                "               <fdAttachment>{}</fdAttachment>\n" +
                "\t\t\t </attachmentForms> \n" +
                "\t\t\t <attachmentForms> \n" +
                "\t\t\t   <fdKey></fdKey>\n" +
                "               <fdFileName></fdFileName>\n" +
                "               <fdAttachment>{}</fdAttachment>\n" +
                "\t\t\t </attachmentForms> \n" +
                "\t\t\t <attachmentForms> \n" +
                "\t\t\t   <fdKey></fdKey>\n" +
                "               <fdFileName></fdFileName>\n" +
                "               <fdAttachment>{}</fdAttachment>\n" +
                "\t\t\t </attachmentForms> \n" +
                "\t\t\t <attachmentForms> \n" +
                "\t\t\t   <fdKey></fdKey>\n" +
                "               <fdFileName></fdFileName>\n" +
                "               <fdAttachment>{}</fdAttachment>\n" +
                "\t\t\t </attachmentForms> \n" +
                "            <!--Optional:-->\n" +
                "            <docContent/>\n" +
                "            <!--Optional:-->\n" +
                "            <docCreator>{PersonNo:\" "+userid+"\"}</docCreator>\n" +
                "            <!--Optional:-->\n" +
                "            <docProperty/>\n" +
                "            <!--Optional:-->\n" +
                "            <docStatus>20</docStatus>\n" +
                "            <!--Optional:-->\n" +
                "            <docSubject>sptest </docSubject>\n" +
                "            <!--Optional:-->\n" +
                "            <fdKeyword/>\n" +
                "            <!--Optional:-->\n" +
                "            <fdTemplateId>16a48ca698ba4101afb9daf4cdeab0a6</fdTemplateId>  \n" +
                "            <!--Optional:-->\n" +
                "            <flowParam>{}</flowParam>\n" +
                "            <!--Optional:-->\n" +
                "            <formValues>\n" +
                "{  "+param+"}" +
                "</formValues>\n" +
                "            <!--Optional:-->\n" +
                "            <sysResource>?</sysResource>\n" +
                "         </arg0>\n" +
                "      </web:addReview>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>\n" +
                "\n";

        return xml;
    }


}
