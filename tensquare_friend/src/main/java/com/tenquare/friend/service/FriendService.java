package com.tenquare.friend.service;

import com.tenquare.friend.dao.FriendDao;
import com.tenquare.friend.dao.NoFriendDao;
import com.tenquare.friend.pojo.Friend;
import com.tenquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: tensquare_parent
 * @author: 大禹
 * @create: 2020-06-03 18:47
 **/

@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    public int addFriend(String userid, String friendid) {
        //判断user到friend是否有数据，有就是重复添加好友
        Friend friend = friendDao.findByUseridAndFriendid(userid, friendid);
        if (friend !=null){
            return  0;
        }
        //直接添加好友 ，让好友表中user到friend方向的type 为0
        friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);
        //判断从 friend到 user 是否有数据，如果有，把双方的状态都改为 1
        if (friendDao.findByUseridAndFriendid(friendid,userid) != null){
            //把双方的islike 都改成1
            friendDao.updateIslike("1",userid,friendid);
            friendDao.updateIslike("1",friendid,userid);
        }

        return 1;
    }

    public int addNoFriend(String userid, String friendid) {
        //先判断是否已经是好友
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userid, friendid);
        if(noFriend!=null){
            noFriend = new NoFriend();
            noFriend.setUserid(userid);
            noFriend.setFriendid(friendid);
            noFriendDao.save(noFriend);
            return 1;
        }
        return 0;
    }

    public void deleteFriend(String userid, String friendid) {
        //删除好友表中 userid 到 friendid 这条 数据
        friendDao.deletefriend(userid,friendid);
        //更新friend 到userid 的islike 为0
        friendDao.updateIslike("0",friendid,userid);
        //非 好友表中添加数据
        NoFriend noFriend =new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
    }
}
