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
    private static final String BASE_URL = "http://renrenshang.tongyi100.cn/index.php/Api/";
    private static MyRequest myRequest;

    public static MyRequest getInstance() {
        if (myRequest == null) {
            myRequest = new MyRequest();
        }
        return myRequest;
    }
    //注册
   public Request  regist(String mobile,String inviteCode,String code,String pwd){
       FormBody formBody=new FormBody.Builder().add("mobile",mobile).add("invite_code",inviteCode).add("code",code).add("pwd",pwd).build();
       Request  request=new     Request.Builder().url(BASE_URL+"register").post(formBody).build();
       return request;
   }
    //动态点赞
    public  Request clickGood(String uid,String pid){
           Request request=new Request.Builder().url(BASE_URL+"post_click?uid="+uid+"&p_id="+pid).build();
        return request;
    }
    //动态评论
    public Request commnet(String uid,String pid){
        FormBody formBody=new FormBody.Builder().add("uid",uid).add("pid",pid).build();
        Request request=new Request.Builder().url(BASE_URL+"post_comment").build();
        return request;
    }
    //获取朋友列表 传入uid
    public Request getFrdList(String uid) {
        Request request = new Request.Builder().url(BASE_URL + "my_friends?uid=" + uid).build();
        return request;
    }
    //个人信息修改
////    public Request modifyUserInfo(Map<String,String> map,File file){
////        MultipartBuilder builder= new MultipartBuilder().type(MultipartBuilder.FORM);
////        if (map != null) {
////            for (Map.Entry<String, String> entry:map.entrySet()){
////                  builder.addPart(Headers.of("Content-Disposition","form-data;name=\""
////                          + entry.getKey()+"\""),RequestBody.create(null,entry.getValue()));
////            }
////        }
////        if (file != null) {
////            RequestBody requestBody = RequestBody.create(MediaType.parse(file.getName()),file);
////            builder.addPart(Headers.of("Content-Disposition","form-data;name=\""+file.getName()+"\""),requestBody);
////        }
//////        RequestBody requestBody = builder.build();
//////        FormBody formBody=new FormBody.Builder()
////////                .add("uid",uid)
////////                .add("user_name",name)
////////                .add("like",like)
////////                .add("picture",photo)
////////                .add("sex",sex)
////////                .add("sign",sign)
//////                .build();
////        RequestBody build = builder.build();
////        Request.Builder builder1 = new Request.Builder().url(BASE_URL+"user_info_edit" ).post(build).build();
//        return builder1;
//    }


    public static Request getFileRequest(String url, File file, Map<String, String> maps) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (maps == null) {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"file\";filename=\"file.jpg\""), RequestBody.create(parse("image/png"), file)
            ).build();

        } else {
            for (String key : maps.keySet()) {
                builder.addFormDataPart(key, maps.get(key));
            }

            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"file\";filename=\"file.jpg\""), RequestBody.create(parse("image/png"), file)
            );

        }
        RequestBody body = builder.build();
        return new Request.Builder().url(url).post(body).build();

    }

    //获取个人信息
    public Request getMyinfo(String uid) {
        Request request = new Request.Builder().url(BASE_URL + "user_info?uid=" + uid).build();
        return request;
    }

    //第一次上传个人信息
    public Request addUserInfo(String uid, String photo, String userName, String like, String sex) {
        FormBody formBody = new FormBody.Builder().add("uid", uid).add("photo", photo)
                .add("user_name", userName).add("like", like)
                .add("sex", sex).build();
        Request request = new Request.Builder().url(BASE_URL + "add_userinfo").post(formBody).build();
        return request;
    }

    public Request publish(String uid, String content, String share, String picture, String count, String money) {
        File file = new File(picture);
        RequestBody fileBody = RequestBody.create(MEDIA_TYPE_MARKDOWN, file);
//        FormBody formBody=new FormBody.Builder()
//                .add("uid",uid)
//                .add("content",content)
//                .add("is_share",share)
//                .add("picture",file.getName())
//                .add("count",count)
//                .add("money_one",money)
//                .build();
        MultipartBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("picture", String.valueOf(fileBody))
                .addFormDataPart("uid", uid)
                .addFormDataPart("content", content)
                .addFormDataPart("is_share", share)
                .addFormDataPart("count", count)
                .addFormDataPart("money_one", money)
                .build();
        Request request = new Request.Builder().url(BASE_URL + "add_post").post(requestBody).build();
        return request;
    }
    //钱包
    public Request wallet(String uid){
        Request request=new Request.Builder().url(BASE_URL+"wallet?uid="+uid).build();
        return request;
    }
     //我的消息
    public  Request getMsg(){
        Request request=new Request.Builder().url(BASE_URL+"get_message").build();
        return   request;
    }
    //获取我的发布
    public Request getMySend(String uid) {
        Request request = new Request.Builder().url(BASE_URL + "my_send?uid=" + uid).build();
        return request;
    }//获取朋友圈

    public Request getFrd(String uid) {
        Request request = new Request.Builder().url(BASE_URL + "dynamic_list?uid=" + uid).build();
        return request;
    }
    // 我的推广
    public  Request mySpread(String uid){
        Request request=new Request.Builder().url(BASE_URL+"my_exten?uid="+uid).build();
        return  request;
    }
    //添加我的上级
    public Request addUpLevel(String uid,String code){
             Request request=new  Request.Builder().url("add_father?uid="+uid+"&code="+code).build();
        return  request;
    }



    //获取课程
    public Request getCourse() {
        Request request = new Request.Builder().url(BASE_URL + "course_list").build();
        return request;
    }

    //手机号查找好友
    public Request getFrdMobile(String mobile) {
        Request request = new Request.Builder().url(BASE_URL + "find_user?mobile=" + mobile).build();
        return request;
    }

    //手机号添加好友
    public Request addFrd(String uid, String fid) {
        Request request = new Request.Builder().url(BASE_URL + "mobile_add?uid=" + uid + "&fid=" + fid).build();
        return request;
    }

    //分享回调,用户uid 动态id
    public Request shareBack(String uid, String pid) {
        Request request = new Request.Builder().url(BASE_URL + "share_return?uid=" + uid + "pid=" + pid).build();
        return request;
    }

    //获取课程详情
    public Request getCourseDetail(String id) {
        Request request = new Request.Builder().url(BASE_URL + "course_detal?id=" + id).build();
        return request;
    }

    //购买课程 参数uid ，课程id
    public Request buyCourse(String uid, String cid) {
        Request request = new Request.Builder().url(BASE_URL + "buy_course?uid=" + uid + "&cid=" + cid).build();
        return request;
    }
     //我的订单
    public Request myOrder(String uid,String type) {
        Request request=new Request.Builder().url(BASE_URL+"my_order?uid="+uid+"&class="+type).build();
        return request;
    }
    //购买旅游
    public Request buyTravel(String uid,String tid){
        Request request =new Request.Builder().url(BASE_URL+"buy_travel?uid="+uid+"&tid="+tid).build();
        return request;
    }
    //修改支付 账户      type	提现方式  1为支付宝  2为微信  3为银行卡
    public Request cashAccount(String uid,String type,String name,String account,String bankCard ){
        FormBody formBody=new FormBody.Builder().
                add("uid",uid).add("type",type).
                add("user_name",name).
                add("account",account).
                add("bank",bankCard)
                .build();
        Request request=new Request.Builder().url(BASE_URL+"edit_account").post(formBody).build();
              return  request;
    }
    //旅游
    public Request travel(){
        Request request=new Request.Builder().url(BASE_URL+"travel").build();
        return  request;

    }
    //旅游详情
    public  Request travelInfo(String id){
        Request request=new Request.Builder().url(BASE_URL+"travel_info?id="+id).build();
        return request;
    }
    //意见反馈
    public  Request suggest(String uid,String content){
        FormBody formBody=new FormBody.Builder().add("uid",uid).add("content",content).build();
        Request request=new Request.Builder().url(BASE_URL+"opinion").post(formBody).build();
        return request;
    }
}
