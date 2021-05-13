package com.lxz.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxz.crodw.entity.Admin;
import com.lxz.crodw.entity.AdminExample;
import com.lxz.crowd.constant.CrowdConstant;
import com.lxz.crowd.exception.LoginFailedException;
import com.lxz.crowd.mapper.AdminMapper;
import com.lxz.crowd.service.api.AdminService;
import com.lxz.crowd.util.CrowdUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public void saveAdmin(Admin admin) {
        // 根据登录账号查询admin对象
        AdminExample adminExample =new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(admin.getLoginAcct());
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        // 判断admin对象是否为空
        if (admins.size()!=0){
            throw new LoginFailedException(CrowdConstant.MESSAGE_REGISTER_FAILED);
        }
        adminMapper.insert(admin);
     //   throw new RuntimeException();
    }

    public List<Admin> findAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    public Admin getAdminByloginAcct(String loginAcct, String password) {
        // 根据登录账号查询admin对象
        AdminExample adminExample =new AdminExample();
         AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        // 判断admin对象是否为空
        if (admins==null||admins.size()==0){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        if (admins.size()>1){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_SYSTEM_LOGIN_NOT_UNIQUE);
        }
        // 为空抛出异常
        Admin admin = admins.get(0);
        if (admin==null){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);

        }
        // 若不为空则去除数据库中admin对象
        String userPswd = admin.getUserPswd();
        // 将表单提交的明文密码加密
        String s = CrowdUtile.md5(password);
        // 判断密码比较
        if (!Objects.equals(s,userPswd)){
            // 不一致抛出异常
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);

        }

        // 一致返回admin对象
        return admin;
    }
    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        // 开启分页功能(pageHelp的非侵入性，不必修改原先查询)
        PageHelper.startPage(pageNum,pageSize);
        // 执行查询
        List<Admin> list = adminMapper.selectAdminByKeyword(keyword);
        // 封装PageInfo对象中
        return new PageInfo<>(list);
    }
    @Override
    public void remove(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public Admin pickOne(Integer adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList) {
        // 为了简化操作：先根据adminId 删除旧的数据，再根据roleIdList 保存全部新的数据
// 1.根据adminId 删除旧的关联关系数据
        adminMapper.deleteOLdRelationship(adminId);
// 2.根据roleIdList 和adminId 保存新的关联关系
        if(roleIdList != null && roleIdList.size() > 0) {
            adminMapper.insertNewRelationship(adminId, roleIdList);
        }
    }
}
