package com.yinggg.translator.service.impl;

import com.yinggg.translator.entity.TUser;
import com.yinggg.translator.mapper.TUserMapper;
import com.yinggg.translator.service.TUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;



/**
 * (TUser)表服务实现类
 *
 * @author makejava
 * @since 2024-11-13 13:33:40
 */
@Service("tUserService")
public class TUserServiceImpl implements TUserService {
    @Resource
    private TUserMapper tUserMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TUser queryById(Integer id) {
        return this.tUserMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param tUser 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
//    @Override
//    public Page<TUser> queryByPage(TUser tUser, PageRequest pageRequest) {
//        long total = this.tUserDao.count(tUser);
//        return new PageImpl<>(this.tUserDao.queryAllByLimit(tUser, pageRequest), pageRequest, total);
//    }

    /**
     * 新增数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    @Override
    public TUser insert(TUser tUser) {
        this.tUserMapper.insert(tUser);
        return tUser;
    }

    /**
     * 修改数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    @Override
    public TUser update(TUser tUser) {
        this.tUserMapper.update(tUser);
        return this.queryById(tUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tUserMapper.deleteById(id) > 0;
    }

    @Override
    public TUser login(TUser tuser) {

        return tUserMapper.login(tuser);
    }

    @Override
    public boolean register(TUser user) {
        List<TUser> result  = tUserMapper.queryByName(user.getUsername());
        if (!result.isEmpty()) {
            // 如果查到了记录，且数量大于0（这里假设查询返回数量代表匹配记录数量）
            return false;
        }
            // 没查到相等记录或者 result 为 null 的情况，继续后续插入逻辑等
        tUserMapper.insert(user);
        return true;
    }
}
