package com.vdong.controller;

import com.vdong.commons.bean.Attach;
import com.vdong.commons.bean.Msg;
import com.vdong.services.SystemCodeService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * 文件上传处理
 *
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/upload")
public class UploadController extends BaseController {


    @Autowired
    private SystemCodeService systemCodeService;

    @ResponseBody
    @RequestMapping(value = "/attachment")
    // ,produces="text/json;charset=UTF-8"
    public Msg uploadScenePicture(MultipartFile file, String uploadpath) throws Exception {
        Msg msg = new Msg();
        String realpathdir = request.getSession().getServletContext()
                .getRealPath("/");
        String path = "upload/roomPic/";
        if (uploadpath != null && !uploadpath.equals("")) {
            path += "/" + uploadpath;
        }
        realpathdir = realpathdir + path;
        // 文件后缀名
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        // 文件名
        String filename = UUID.randomUUID().toString().replace("-", "") + "."
                + ext;
        File savefile = new File(new File(realpathdir), filename);
        if (!savefile.getParentFile().exists()) {
            savefile.getParentFile().mkdirs();
        }
        file.transferTo(savefile);
        //将数据存到附件表中并返回ID		
        Attach attach = new Attach();
        attach.setFileName(filename);
        attach.setOriginName(file.getOriginalFilename());
        attach.setUrl(path + filename);
        try {
            int count = systemCodeService.saveAttach(attach);
            attach.setId(count);
            msg.setData(attach);
            msg.setResource(true);
        } catch (Exception e) {
            e.printStackTrace();
            msg.setResource(false);
        }
        return msg;
    }

}
