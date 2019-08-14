package com.liwinon.itams.service;

import com.liwinon.itams.dao.primaryRepo.AssetsDao;
import com.liwinon.itams.dao.primaryRepo.UserInfoDao;
import com.liwinon.itams.entity.primay.Assets;
import com.liwinon.itams.entity.primay.UserInfo;
import com.liwinon.itams.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class hardwareServiceImpl implements hardwareService {
    @Autowired
    UserInfoDao userDao;
    @Autowired
    AssetsDao assetsDao;
    /**
     * 保存或者更新资产
     *  在更新时,前端限制了DeviceId不能更改,直接设置即可.
     *  assetsid 是由ERP 系统生成, 故不作限制
     * @param request
     * @return
     */
    @Transactional
    public String saveHardware(HttpServletRequest request) {
        //Hardware hd = new Hardware();
        Assets as = new Assets();
        System.out.println("开始保存");
        String DeviceID = request.getParameter("DeviceID");
        boolean update = false;
        if (DeviceID != null) {
            if (assetsDao.findByDeviceID(DeviceID) != null) { //是更新操作
                update = true;
                as = assetsDao.findByDeviceID(DeviceID);
            }
            if (DeviceID == "")
                return "deviceIDEmpty";
        }
        as.setDeviceID(DeviceID);
        String assetsid =  request.getParameter("AssetsID");
        if (!StringUtils.isEmpty(assetsid))
            as.setAssetsID(assetsid);
        String type = request.getParameter("AssetsType");
        if (type != null)
            as.setAssetsType(type);
        String AssetsCategory = request.getParameter("AssetsCategory");
        if (AssetsCategory != null)
            as.setAssetsCategory(AssetsCategory);
        String FactoryID = request.getParameter("FactoryID");
        if (FactoryID != null)
            as.setFactoryID(FactoryID);
        String AssetsName = request.getParameter("AssetsName");
        if (AssetsName != null)
            as.setAssetsName(AssetsName);
        String Models = request.getParameter("Models");
        if (Models != null)
            as.setModels(Models);
        String Supplier = request.getParameter("Supplier");
        if (Supplier != null)
            as.setSupplier(Supplier);
        String FaArea = request.getParameter("FaArea");
        if (FaArea != null)
            as.setFaArea(FaArea);
        String Area = request.getParameter("Area");
        if (Area != null)
            as.setArea(Area);
        String Location = request.getParameter("Location");
        if (Location != null)
            as.setLocation(Location);
        String Department = request.getParameter("Department");
        if (Department != null)
            as.setDepartment(Department);
        String ProductionSetp = request.getParameter("ProductionSetp");
        if (ProductionSetp != null)
            as.setProductionSetp(ProductionSetp);
        String Personliable = request.getParameter("Personliable");
        if (Personliable != null)
            as.setPersonliable(Personliable);
        String PersonliableID = request.getParameter("PersonliableID");
        if (PersonliableID != null)
            as.setPersonliableID(PersonliableID);
        String PState = request.getParameter("PState");
        if (PState != null)
            as.setPState(PState);
        String AState = request.getParameter("AState");
        if (AState != null)
            as.setAState(AState);
        String RequireID = request.getParameter("RequireID");
        if (RequireID != null)
            as.setRequireID(RequireID);
        String AgreementID = request.getParameter("AgreementID");
        if (AgreementID != null)
            as.setAgreementID(AgreementID);
        String ContractID = request.getParameter("ContractID");
        if (ContractID != null)
            as.setContractID(ContractID);
        String date = request.getParameter("EntryDate");
        Date EntryDate = null ;
        if(date!= null &&date!=""){
            EntryDate = Date.valueOf(date);
            if (EntryDate != null){
                as.setEntryDate(EntryDate);
            }
        }
        String ReceiptID = request.getParameter("ReceiptID");
        if (ReceiptID != null)
            as.setReceiptID(ReceiptID);
        String CheckID = request.getParameter("CheckID");
        if (CheckID != null)
            as.setCheckID(CheckID);
        String OutID = request.getParameter("OutID");
        if (OutID != null)
            as.setOutID(OutID);
        String Remark = request.getParameter("Remark").trim();
        if (Remark != null)
            as.setRemark(Remark);
        assetsDao.save(as);
        /* 判断是否有使用人员*/
        UserInfo user = userDao.findByAssetsID(assetsid);
        if (user ==null){
            user = new UserInfo();
            user.setAssetsID(assetsid);
        }
        String userID = request.getParameter("UserID");
        if (userID != null && userID != "") {  //有使用者工号,就保存
            user.setUserID(userID);
            String userName = request.getParameter("UserName");
            if (userName != null)
                user.setUserName(userName);
            if (EntryDate != null)
                user.setGetTime(EntryDate);
            String UserDepartment = request.getParameter("UserDepartment");
            if (UserDepartment != null)
                user.setUserDepartment(UserDepartment);
            userDao.save(user);
//            //说明有人使用,更改资产状态
//            hd.setState(1);
            //重新保存State
//            hdDao.save(hd);
            System.out.println("保存用户成功");

        }
        return "ok";
    }

    /**
     * 解析Excel并保存数据
     *
     * @param columns
     * @param filePath
     */
    @Override
    @Transactional
    public String resolveExcel(String[] columns, String filePath, int type) {
        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        List<Map<String, String>> list = null;
        String cellData = null;
        wb = ExcelUtil.readExcel(filePath);
        if (wb != null) {
            //用来存放表中数据
            list = new ArrayList<Map<String, String>>();
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i < rownum; i++) {
                Map<String, String> map = new LinkedHashMap<String, String>();
                row = sheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < colnum; j++) {
                        cellData = (String) ExcelUtil.getCellFormatValue(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                } else {
                    break;
                }
                list.add(map);
            }
        }
        //遍历解析出来的list
        String key = null;
        String value = null;
        if (type == 0) {
            String assetsType = "";
            String assetsCategory = "";
            for (Map<String, String> map : list) {
                Assets as = new Assets();
                UserInfo userInfo = new UserInfo();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    //    System.out.println(entry.getKey() + ":" + entry.getValue() + ",");
                    key = entry.getKey().trim();
                    value = entry.getValue().trim();

                    if (columns[0].equals(key)) { //解析类型
                        assetsType = value;
                    }
                    if (columns[1].equals(key)) { //解析类别
                        assetsCategory = value;
                    }
                    if (columns[2].equals(key)) {  //判断资产编号非空
                        if (StringUtils.isEmpty(value)){
                            continue;
                        }
                        if (assetsDao.findByAssetsID(value) == null) { //无
                            as.setAssetsID(value);
                            as.setAssetsType(assetsType);
                            as.setAssetsCategory(assetsCategory);
                        } else {  //存在,更新
                            as = assetsDao.findByAssetsID(value);
                            as.setAssetsType(assetsType);
                            as.setAssetsCategory(assetsCategory);
                            userInfo = userDao.findByAssetsID(value);
                            if (userInfo==null)
                                userInfo = new UserInfo();
                        }
                    }

                    if (columns[3].equals(key)) { //解析设备编号
                        if (StringUtils.isEmpty(value)){
                            continue;
                        }
                        as.setDeviceID(value);
                    }
                    if (columns[4].equals(key))  //解析出厂编号
                        as.setFactoryID(value);
                    if (columns[5].equals(key))  //解析资产名称
                        as.setAssetsName(value);
                    if (columns[6].equals(key)) //解析规格型号
                        as.setModels(value);
                    if (columns[7].equals(key)) //供应商
                        as.setSupplier(value);
                    if (columns[8].equals(key)) //厂区
                        as.setFaArea(value);
                    if (columns[9].equals(key)) //区域
                        as.setArea(value);
                    if (columns[10].equals(key))  //详细位置
                        as.setLocation(value);
                    if (columns[11].equals(key))  //部门
                        as.setDepartment(value);
                    if (columns[12].equals(key)) //工序
                        as.setProductionSetp(value);
                    if (columns[13].equals(key)) //责任人
                        as.setPersonliable(value);
                    if (columns[14].equals(key))  //责任人ID
                        as.setPersonliableID(value);
                    if (columns[15].equals(key)) { //性能状态
                        as.setPState(value);
                    }
                    if (columns[16].equals(key)) {  //资产状态
                        as.setAState(value);
                    }
                    if (columns[17].equals(key))  //需求报告编号
                        as.setRequireID(value);
                    if (columns[18].equals(key)) //技术协议号
                        as.setAgreementID(value);
                    if (columns[19].equals(key)) //合同号
                        as.setContractID(value);
                    if (columns[20].equals(key)){ //入厂时间
                        if (value==null || value==""){
                            as.setEntryDate(Date.valueOf("0001-01-01"));
                        }else {
                            as.setEntryDate(Date.valueOf(value));
                        }
                     }
                    if (columns[21].equals(key)) //收货单号
                        as.setReceiptID(value);
                    if (columns[22].equals(key)) //验收单号
                        as.setCheckID(value);
                    if (columns[23].equals(key)) //报废/转售单号
                        as.setOutID(value);
                    if (columns[24].equals(key)) //备注
                        as.setRemark(value);
                    if (columns[25].equals(key)) //使用人
                        userInfo.setUserName(value);
                    if (columns[26].equals(key)) //使用人工号
                        userInfo.setUserID(value);
                    if (columns[27].equals(key)) //使用人联系方式
                        userInfo.setTelephone(value);
                }
             //   System.out.println(as);
                //保存
                if (as != null && as.getAssetsID() != null) {
                    assetsDao.save(as);
                    userInfo.setAssetsID(as.getAssetsID());
                    if (!StringUtils.isEmpty(userInfo.getAssetsID())&&!StringUtils.isEmpty(userInfo.getUserName())){
                        if (userDao.findByAssetsID(as.getAssetsID())==null){
                            userDao.save(userInfo);
                        }
                    }
                    System.out.println("保存资产成功");
                }
            }

            return "ok";
        }

        return "失败!";
    }

}
