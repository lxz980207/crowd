package com.lxz.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.lxz.crodw.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public interface RoleService {

    PageInfo<Role> getPageInfo(Integer pageNum,Integer pageSize,String keyword);
    void delete (Integer id);

    void saveRole(Role role);

    void updateRole(Role role);
    void removeRole(List<Integer> roleList);

    List<Role> getAssignedRole(Integer adminId);

    public List<Role> getUnAssignedRole(Integer adminId);
}
