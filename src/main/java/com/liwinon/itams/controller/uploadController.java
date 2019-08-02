package com.liwinon.itams.controller;

import com.liwinon.itams.dao.primaryRepo.AssetsDao;
import com.liwinon.itams.dao.primaryRepo.UserInfoDao;
import com.liwinon.itams.service.hardwareService;
import com.liwinon.itams.service.searchService;
import com.liwinon.itams.utils.ExcelUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

@RestController
public class uploadController {
    @Autowired
    hardwareService hdService;
    @Autowired
    searchService search;
    @Autowired
    UserInfoDao userDao;
    @Autowired
    AssetsDao asDao;

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/itams/upload/excel")
    public String upload(@RequestParam("file") MultipartFile file) {
        Subject subject = SecurityUtils.getSubject();
        if(!subject.hasRole("ROLE_admin")){
            System.out.println("无权限上传!");
            return null;
        }
        System.out.println("开始上传文件");
        if (file.isEmpty()) {
            return "empty";
        }
        String fileName = file.getOriginalFilename();
        if(fileName.indexOf("\\")!=-1){  //浏览器设置为显示真实路径 ,则需要处理
            String[] strs = fileName.split("\\\\");
            fileName = strs[strs.length-1];
        }
        System.out.println("文件名:"+fileName);
        String filePath = "D:\\ITAMS\\file\\";
        File dir = new File(filePath);
        if(dir.exists()){
            dir.mkdir();
        }
        File Assets = new File(filePath + fileName);
        try {
            file.transferTo(Assets);
            //保存操作信息
            return filePath + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "false";
    }

    /**
     * 解析上传的excel文件
     * @param path
     * @param type 可以选择解析的文件类型,
     * @return
     */
    @GetMapping(value = "/itams/upload/resolvExcel")
    public String resolve(String path, int type) {
        System.out.println("开始解析");
        String[] columns = null;
        if (type == 1) {
            columns = new String[]{"资产ID", "使用人", "工号", "资产类别", "品牌", "型号", "CPU", "RAM", "HDD1", "HDD2", "有线MAC1",
                    "有线MAC2", "无线MAC", "厂别位置", "状态", "采购日期", "IT维护工程师", "入系统日期", "所属部门"};
        }
        if(type==0){  //上传的是AssetsExcel
            columns = new String[]{"资产类型", "资产类别", "资产编号", "设备编号", "出厂编号", "资产名称", "规格型号", "供应商",
                    "厂区", "区域", "详细位置", "部门", "工序", "责任人", "责任人工号", "性能状态", "资产状态",
                    "需求报告编号", "技术协议号", "合同号", "入厂日期", "收货单号", "验收单号", "报废/转售单号","备注",
                    "使用人", "使用人工号","联系电话"};
        }
        return hdService.resolveExcel(columns, path,type);
    }

    @GetMapping(value = "/itams/download/Example")
    public ResponseEntity<InputStreamResource> exampleDoc(String name) {
        String path = "";
        if (StringUtils.isNotBlank(name)) {
            if ("Assets".equals(name)) {
                path = "D:\\ITAMS\\file\\资产管理信息上传示例.xlsx";
            }
            if("IT".equals(name)){
                path = "D:\\ITAMS\\file\\IT资产信息上传示例.xlsx";
            }
            // 此处可以添加其他示例文档
            FileSystemResource file = new FileSystemResource(path);
            try {
                String fileName = new String(file.getFilename().getBytes(), "iso8859-1");
                if (file.exists()) {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Cache-Control", "no-cache,no-store,must-revalidate");
                    headers.add("Content-Disposition", "attachment;fileName=" + fileName);
                    headers.add("Pragma", "no-cache");
                    headers.add("Expires", "0");
                    return ResponseEntity.ok().headers(headers).contentLength(file.contentLength())
                            .contentType(MediaType.parseMediaType("application/octet-stream"))
                            .body(new InputStreamResource(file.getInputStream()));
                }
            } catch (Exception e) {
                System.out.println("下载异常!");
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 导出IT资产数据
     *
     * @return
     */
    @GetMapping(value = "/itams/download/ITData")
    public ResponseEntity<InputStreamResource> exportITExcel() {
        List<String[]> data = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmm");
        String filepath = "D:\\ITAMS\\file\\export\\IT资产文档" + sdf.format(new Date()) + ".xlsx";
        System.out.println(filepath);
        data.add(new String[]{"使用人", "工号", "部门", "资产编号", "笔记本型号", "计算机名", "杀毒软件",
                "管理员", "USB状态", "笔记本状态", "BIOS设置", "领取时间"}); //第一行
        List<String[]> values = userDao.export();
        System.out.println("个数:"+values.size());
        for (String[] strs : values) {
            data.add(new String[]{strs[0], strs[1], strs[2], strs[3], strs[4], null, null, null, null, null, null, strs[5]});
        }
        try {
            ExcelUtil.writeExcel(filepath, data);
        } catch (Exception e) {
            System.out.println("导出失败!");
            e.printStackTrace();
        }
        // 此处可以添加其他示例文档
        FileSystemResource file = new FileSystemResource(filepath);
        try {
            String fileName = new String(file.getFilename().getBytes(), "iso8859-1");
            if (file.exists()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache,no-store,must-revalidate");
                headers.add("Content-Disposition", "attachment;fileName=" + fileName);
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                return ResponseEntity.ok().headers(headers).contentLength(file.contentLength())
                        .contentType(MediaType.parseMediaType("application/octet-stream"))
                        .body(new InputStreamResource(file.getInputStream()));
            }
        } catch (Exception e) {
            System.out.println("下载异常!");
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping(value = "/itams/download/AssetsData")
    public ResponseEntity<InputStreamResource> exportAsExcel(String content,String type){
        System.out.println("开始导出搜索的资产");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmm");
        String filepath = "D:\\ITAMS\\file\\export\\资产文档" + sdf.format(new Date()) + ".xlsx";
        List<String[]> data = search.getdata(content,type);
        try {
            ExcelUtil.writeExcel(filepath, data);
        } catch (Exception e) {
            System.out.println("导出失败!");
            e.printStackTrace();
        }
        FileSystemResource file = new FileSystemResource(filepath);
        try {
            String fileName = new String(file.getFilename().getBytes(), "iso8859-1");
            if (file.exists()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache,no-store,must-revalidate");
                headers.add("Content-Disposition", "attachment;fileName=" + fileName);
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                return ResponseEntity.ok().headers(headers).contentLength(file.contentLength())
                        .contentType(MediaType.parseMediaType("application/octet-stream"))
                        .body(new InputStreamResource(file.getInputStream()));
            }
        } catch (Exception e) {
            System.out.println("下载异常!");
            e.printStackTrace();
        }

        return null;
    }
}
