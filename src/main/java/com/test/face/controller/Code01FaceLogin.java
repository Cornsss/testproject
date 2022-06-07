package com.test.face.controller;

import com.baidu.aip.face.AipFace;
import com.test.base.ViewResponse;
import com.test.face.entity.User;
import com.test.utils.ImageUtil;
import com.test.utils.StringUtils;
import org.json.JSONObject;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
public class Code01FaceLogin {


    private static final String APP_ID = "26346392";

    private static final String API_KEY = "GTZNqeWNdOLO6EvSuBqGM8tb";

    private static final String SECRET_KEY = "7xADRqd6HpVvUP2fCU8qReCiWURi8hNG";

    @PostMapping(value = "/register")
    @ResponseBody
    public ViewResponse register(@RequestParam String faceBase, @RequestParam String userName) {
        try {
            if (StringUtils.isNotBlank(faceBase) && StringUtils.isNotBlank(userName)) {
                //文件上传的地址
                String upPath = ResourceUtils.getURL("classpath:").getPath() + "static/photo";
                //用于查看路径是否正确
                System.out.println(upPath);
                // 图片名称
                String fileName = userName + System.currentTimeMillis() + ".png";
                System.out.println(upPath + "//" + fileName);
//                File file = new File(upPath + "\\" + fileName);
                //初始化百度云的AipFace
                AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

                //往自己demo数据库里插入一条用户数据
                User user = new User();
                user.setUserName(userName);
                user.setFacePath(upPath + "//" + fileName);
//                User exitUser = userService.selectUserByName(user);
//                if(exitUser != null) {
//                    return "2";
//                }
//                userService.addUsers(user);


                // 往自己demo服务器里面上传摄像头捕获的图片 base64转图片
                ImageUtil.generateImage(faceBase, fileName);
                JSONObject groupList = client.getGroupList(null);
                System.out.println(groupList);
                //向百度云人脸库插入一张人脸
                JSONObject base64 = client.addUser(faceBase, "BASE64", "1", userName, null);
                System.out.println(base64);
//                facesetAddUser(client, faceBase, userName);

                return ViewResponse.success();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ViewResponse.success();
    }

}
