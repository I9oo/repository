package com.itheima.dao;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    /**
     * 查询全部
     * @return
     */
    @Select("select * from sys_permission")
    List<Permission> findAll();

    /**
     * 添加
     */
    @Insert("insert into sys_permission values(seq_permission.nextval,#{permissionName},#{url},#{pid})")
    void save(Permission permission);

    /**
     * 根据角色id，查询角色的权限
     */
    @Select("select p.* from sys_permission p " +
            "inner join sys_role_permission rp on p.id=rp.permissionid where rp.roleid=#{roleId}")
    List<Permission> findPermissionByRoleId(int roleId);
}
