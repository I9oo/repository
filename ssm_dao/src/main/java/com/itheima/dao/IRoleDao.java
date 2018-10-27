package com.itheima.dao;
import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IRoleDao {

    /**
     * 查询全部
     * @return
     */
    @Select("select * from sys_role")
    List<Role> findAll();

    /**
     * 添加
     */
    @Insert("insert into sys_role values(seq_role.nextval,#{roleName},#{roleDesc})")
    void save(Role role);

    /**
     * 根据用户id，查询用户角色
     */
    @Select("select r.* from sys_role r inner join sys_user_role ur on r.id=ur.roleid where ur.userid=#{userId}")
    @Results({
            // 封装角色id
            @Result(property = "id",column = "id"),
            //封装权限:permissions
            @Result(property = "permissions",column = "id",
            many = @Many(select = "com.itheima.dao.IPermissionDao.findPermissionByRoleId",fetchType = FetchType.LAZY))
    })
    List<Role> findRoleByUserId(int userId);
}
