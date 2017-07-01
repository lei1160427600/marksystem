package cn.edu.henau.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.henau.entity.Checkin;

public interface CheckinDao {

	Checkin queryById(long checkinId);
	 
	 int insertCheckin(List<Checkin> checkin);
	 
	 int queryRollCallCount();
	 
	 void updateCheckin(List<Checkin> checkin);
	 
	 List<Checkin> getCurrentPage(Checkin checkin);
	    
	 List<Checkin> queryAll(@Param("offset") int offset,@Param("limit") int limit);
}
