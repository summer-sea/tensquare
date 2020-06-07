package com.tenquare.friend.controller;

import com.tenquare.friend.client.UserClient;
import com.tenquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: tensquare_parent
 * @author: 大禹
 * @create: 2020-06-03 18:43
 **/

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserClient userClient;



    /**
     * 添加好友
     * @param friendid 对方用户ID
     * @param type 1：喜欢 0：不喜欢
     * @return
     */



    @RequestMapping(value="/like/{friendid}/{type}",method= RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendid , @PathVariable String type){
        //验证是否登录 并且 拿到当前用户id
        Claims claims= (Claims) request.getAttribute("claims_user");
        if(claims == null ){
            //说明当前用户没有user 权限
            return new Result(false, StatusCode.LOGINERROR,"权限不足");
        }
        //得到当前用户角色
        String userid = claims.getId();
        if(type !=null) {
            //如果是喜欢
            if (type.equals("1")) {
                //添加好友
                int flag =friendService.addFriend(userid, friendid);
                if (flag==0) {
                    return new Result(false, StatusCode.REPERROR, "不能重复添加好友,已经添加此好友");
                }
                if(flag ==1){
                    userClient.updatefanscountandfollowcount(userid,friendid,1);
                    return new Result(false, StatusCode.REPERROR, "添加成功");
                }
            } else if (type.equals("2")) {
                //不喜欢 添加非好友
                int flag= friendService.addNoFriend(userid, friendid);
                if (flag==0) {
                    return new Result(false, StatusCode.REPERROR, "不能重复添加非好友,已经添加此好友");
                }
                if(flag ==1){
                    return new Result(false, StatusCode.REPERROR, "添加成功");
                }

            }
            return new Result(false, StatusCode.ERROR, "参数异常");

        }else{
            return new Result(false, StatusCode.ERROR, "参数异常");
        }

    }

    /**
     * 删除好友
     */

    @RequestMapping(value="/{friendid}",method= RequestMethod.DELETE)
    public Result deleteFriend(@PathVariable String friendid){

        //验证是否登录 并且 拿到当前用户id
        Claims claims= (Claims) request.getAttribute("claims_user");
        if(claims == null ){
            //说明当前用户没有user 权限
            return new Result(false, StatusCode.LOGINERROR,"权限不足");
        }
        //得到当前用户角色
        String userid = claims.getId();

        friendService.deleteFriend(userid,friendid);

        userClient.updatefanscountandfollowcount(userid,friendid,-1);
        return new Result(true,StatusCode.OK,"删除成功");

    }

}
