package com.itheima.dao;

import com.itheima.domain.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IUserDao {

    /**
     * 根据用户名查询
     */
    @Select("select * from sys_user where username=#{username}")
    @Results({
            //封装用户的id
            @Result(property = "id", column = "id"),
            //用户的其他信息：username、password等，因为列与字段一致所以可省略。仅限于注解开发。

            //封装角色信息:roles
            @Result(property = "roles",column = "id",javaType = List.class,
                    many = @Many(select = "com.itheima.dao.IRoleDao.findRoleByUserId",fetchType = FetchType.LAZY))
    })
    List<SysUser> findByUsername(String username);

    /**
     * 查询全部用户
     *
     * @return
     */
    @Select("select * from sys_user")
    List<SysUser> findAll();

    /**
     * 添加用户
     */
    @Insert("insert into sys_user values(null,#{username},#{email},#{password},#{phoneNum},#{status})")
    void save(SysUser sysUser);

    /**
     * 根据用户id查询（用户、角色、权限）
     *
     * @param id
     * @return
     */
    @Select("select * from sys_user where id=#{id}")
    @Results({
            //封装用户的id
            @Result(property = "id", column = "id"),
            //用户的其他信息：username、password等，因为列与字段一致所以可省略。仅限于注解开发。

            //封装角色信息:roles
            @Result(property = "roles",column = "id",javaType = List.class,
                    many = @Many(select = "com.itheima.dao.IRoleDao.findRoleByUserId",fetchType = FetchType.LAZY))
    })
    SysUser findById(int id);

    /**
     * 根据用户id删除用户角色中间表数据
     * @param userId
     */
    @Delete("delete from sys_user_role where userid=#{userId}")
    void deleteUserRole(int userId);

    /**
     * 给用户添加角色
     * @Param
     * 1.建立形参与占位符参数的映射关系，
     * 2.如果方法有多个参数时候使用。
     */
    @Insert("insert into sys_user_role(userid,roleid)values(#{userId},#{roleId})")
    void saveUserRole(@Param("userId") int userId, @Param("roleId") int roleId);
}















