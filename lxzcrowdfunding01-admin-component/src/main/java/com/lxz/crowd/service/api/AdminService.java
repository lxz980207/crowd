package com.lxz.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.lxz.crodw.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminService {
   void saveAdmin(Admin admin);

    List<Admin> findAll();

    Admin getAdminByloginAcct(String loginAcct, String password);

    PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

    void remove(Integer adminId);

    Admin pickOne(Integer adminId);

    void saveAdminRoleRelationship(Integer adminId,List<Integer> roleIdList);
}
