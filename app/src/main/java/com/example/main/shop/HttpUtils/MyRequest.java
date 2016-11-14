package com.example.main.shop.HttpUtils;

import java.io.File;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

import static okhttp3.MediaType.parse;


/**
 * Created by Administrator on 2016/11/8.
 */

public class MyRequest {
    public static final MediaType MEDIA_TYPE_MARKDOWN = parse("image/jpg");
    private static final String BASE_URL="http://renrenshang.tongyi100.cn/index.php/Api/";
    private  static MyRequest myRequest;

    public static MyRequest getInstance() {
        if (myRequest == null) {
            myRequest=new MyRequest();
        }
        return myRequest;
    }
    //获取朋友列表 传入uid
    public Request getFrdList(String uid) {
        Request request=new Request.Builder().url(BASE_URL+"my_friends?uid="+uid).build();
        return request;
    }
    //个人信息修改
//    public Request modifyUserInfo(Map<String,String> map,File file){
//        MultipartBuilder builder= new MultipartBuilder().type(MultipartBuilder.FORM);
//        if (map != null) {
//            for (Map.Entry<String, String> entry:map.entrySet()){
//                  builder.addPart(Headers.of("Content-Disposition","form-data;name=\""
//                          + entry.getKey()+"\""),RequestBody.create(null,entry.getValue()));
//            }
//        }
//        if (file != null) {
//            RequestBody requestBody = RequestBody.create(MediaType.parse(file.getName()),file);
//            builder.addPart(Headers.of("Content-Disposition","form-data;name=\""+file.getName()+"\""),requestBody);
//        }
////        RequestBody requestBody = builder.build();
////        FormBody formBody=new FormBody.Builder()
//////                .add("uid",uid)
//////                .add("user_name",name)
//////                .add("like",like)
//////                .add("picture",photo)
//////                .add("sex",sex)
//////                .add("sign",sign)
////                .build();
//        RequestBody build = builder.build();
//        Request.Builder builder1 = new Request.Builder().url(BASE_URL+"user_info_edit" ).post(build).build();
//        return builder1;
//    }


    public static Request  getFileRequest(String url,File file,Map<String, String> maps){
        MultipartBody.Builder builder=  new MultipartBody.Builder().setType(MultipartBody.FORM);
        if(maps==null){
            builder.addPart( Headers.of("Content-Disposition", "form-data; name=\"file\";filename=\"file.jpg\""), RequestBody.create(parse("image/png"),file)
            ).build();

        }else{
            for (String key : maps.keySet()) {
                builder.addFormDataPart(key, maps.get(key));
            }

            builder.addPart( Headers.of("Content-Disposition", "form-data; name=\"file\";filename=\"file.jpg\""), RequestBody.create(parse("image/png"),file)
            );

        }
        RequestBody body = builder.build();
        return   new Request.Builder().url(url).post(body).build();

    }
    //获取个人信息
    public Request getMyinfo(String uid){
        Request request=new Request.Builder().url(BASE_URL+"user_info?uid="+uid).build();
        return request;
    }
    //第一次上传个人信息
    public Request addUserInfo(String uid,String photo,String userName,String like,String sex ) {
        FormBody formBody=new FormBody.Builder().add("uid",uid).add("photo",photo)
                .add("user_name",userName).add("like",like)
                .add("sex",sex).build();
        Request request=new Request.Builder().url(BASE_URL+"add_userinfo").post(formBody).build();
        return request;
    }
    public Request publish(String uid,String content,String share,String picture,String count,String money){
        File file=new File(picture);
        RequestBody fileBody = RequestBody.create(MEDIA_TYPE_MARKDOWN,file);
//        FormBody formBody=new FormBody.Builder()
//                .add("uid",uid)
//                .add("content",content)
//                .add("is_share",share)
//                .add("picture",file.getName())
//                .add("count",count)
//                .add("money_one",money)
//                .build();
        MultipartBody requestBody =new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("picture",String.valueOf(fileBody))
                .addFormDataPart("uid",uid)
                .addFormDataPart("content",content)
                .addFormDataPart("is_share",share)
                .addFormDataPart("count",count)
                .addFormDataPart("money_one",money)
                .build();
        Request request=new Request.Builder().url(BASE_URL+"add_post").post(requestBody).build();
        return request;
    }
    //获取我的发布
    public Request getMySend(String uid){
        Request request =new Request.Builder().url(BASE_URL+"my_send?uid="+uid).build();
        return request;
    }//获取朋友列表
    public Request getFrd(String uid){
        Request request =new Request.Builder().url(BASE_URL+"dynamic_list?uid="+uid).build();
        return request;
    }
    public Request getCourse(){
        Request request = new Request.Builder().url(BASE_URL + "course_list").build();
return request;
    }

}
