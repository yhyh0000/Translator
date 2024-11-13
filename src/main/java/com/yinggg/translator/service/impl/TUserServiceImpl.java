package com.yinggg.translator.service.impl;

import com.yinggg.translator.entity.TUser;
import com.yinggg.translator.mapper.TUserDao;
import com.yinggg.translator.service.TUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    private TUserDao tUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TUser queryById(Integer id) {
        return this.tUserDao.queryById(id);
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
        this.tUserDao.insert(tUser);
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
        this.tUserDao.update(tUser);
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
        return this.tUserDao.deleteById(id) > 0;
    }

    @Override
    public TUser login(TUser tuser) {
        return tUserDao.login(tuser);
    }
}
