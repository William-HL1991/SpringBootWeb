package com.example.springbootweb.web.controller;

import com.example.springbootweb.biz.service.ImgService;
import com.example.springbootweb.biz.service.ImgUploadService;
import com.example.springbootweb.common.entity.Result;
import com.example.springbootweb.common.utils.JsonUtil;
import com.example.springbootweb.common.utils.StringUtil;
import com.example.springbootweb.dao.module.Img;
import com.example.springbootweb.web.entity.Upload;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.example.springbootweb.common.error.SpringBootWebErrors.IMG_IS_EXIST;
import static com.example.springbootweb.common.error.SpringBootWebErrors.IMG_IS_NOT_EXIST;

/**
 * @author william
 * @date 2020/4/21
 */
@Controller
@Slf4j
@Async
@CrossOrigin(allowCredentials = "true", maxAge = 3600)  // 解决跨域问题
public class ImageUploaderController {

    @Autowired
    private ImgUploadService imgUploadService;

    @Autowired
    private ImgService imgService;

    @Value("${tma.imagesPath}")
    private String imgUploadPath;

    @PostMapping(value = "imgUpload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public Result<Upload> imgUpload(@RequestParam(name = "img") MultipartFile imgFile,
                                    @RequestParam(name = "name") String name,
                                    @RequestParam(name = "caseID", required = false) Integer caseId,
                                    @RequestParam(name = "newcaseID", required = false) Integer newCaseId) {
        String fileUrl = "http://192.168.3.34:8080/image/";
        if (imgFile.isEmpty()) {
            return Result.wrapErrorResult(IMG_IS_NOT_EXIST);
        }
        Img img1 = imgService.findByName(name);
        if (img1 != null) {
            return Result.wrapErrorResult(IMG_IS_EXIST);
        }
        if (imgFile.getContentType().contains("image")) {
            // 获取文件名
            String fileName = imgFile.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("上传的后缀名为：" + suffixName);
            String newFileName = StringUtil.getUuid() + suffixName;
            File dest = new File(imgUploadPath);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                FileCopyUtils.copy(imgFile.getInputStream(), Files.newOutputStream(new File(
                        imgUploadPath + File.separator + newFileName).toPath()));
                Img img = new Img();
                img.setName(name);
                img.setCaseId((long) caseId);
                img.setNewcaseId((long) newCaseId);
                img.setUrl(fileUrl);
                imgUploadService.insert(img);
                if (imgUploadService.insert(img)) {
                    Upload upload = new Upload();
                    upload.setName(name);
                    upload.setUrl(fileUrl);
                    log.info("\n" + JsonUtil.bean2StringPretty(upload));
                    return Result.wrapSuccessfulResult("上传成功", upload);
                } else {
                    return Result.wrapErrorResult(500005, "存库数据失败");
                }

            } catch (IllegalStateException e) {
                log.error("save image IllegalStateException -> " + e.toString());
                e.printStackTrace();
                return Result.wrapErrorResult(500001, "内部错误");
            } catch (IOException e) {
                log.error("save image IOException -> " + e.toString());
                e.printStackTrace();
                return Result.wrapErrorResult(500002, "内部错误");
            } catch (Exception e) {
                log.error("save image Exception -> " + e.toString());
                e.printStackTrace();
                return Result.wrapErrorResult(500003, "内部错误");
            }
        } else {
            return Result.wrapErrorResult(IMG_IS_NOT_EXIST);
        }

    }
}
