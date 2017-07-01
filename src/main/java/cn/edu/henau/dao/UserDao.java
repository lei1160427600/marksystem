package cn.edu.henau.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.henau.entity.UserEntity;

public interface UserDao {

    UserEntity queryById(long userId);
    
    UserEntity queryByUserCode(String userCode);
    
    void updateStudent(UserEntity userEntity);
    
    long insertStudent(UserEntity userEntity);
    
    void updateStudentList(List<UserEntity> userEntity);
    
    void updateIdentityAndAuthority(List<UserEntity> userEntity);
    
    void insertStudentList(List<UserEntity> userEntity);
    
    void deleteUser(List<UserEntity> list);
    
    List<UserEntity> queryByProAndClass(@Param("profession")String profession,@Param("userClass")String userClass);
    List<UserEntity> loadTeacherList(String identity);

    List<UserEntity> queryAll(@Param("offset") int offset,@Param("limit") int limit);
}
