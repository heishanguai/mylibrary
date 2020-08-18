package com.springlearning.mylibrary.dao;

import com.springlearning.mylibrary.model.Ticket;
import jdk.nashorn.internal.objects.annotations.Where;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TicketDAO {

    String table_name = "ticket";
    String insert_field = "user_id, ticket, expired_at";
    String select_field = "id, " + insert_field;

    @Insert({"insert into", table_name, "(", insert_field, ") values (#{userId}, #{ticket}, #{expiredAt})"})
    int addTicket(Ticket ticket);

    @Select({"select", select_field, "from", table_name, "where user_id=#{uid}"})
    Ticket selecByUserId(int uid);

    @Select({"select", select_field, "from", table_name, "where ticket=#{t}"})
    Ticket selectByTicket(String t);

    @Delete({"delete from", table_name, "where user_id=#{uid}"})
    void deleteByUserId(int uid);

    @Delete({"delete from", table_name, "where ticket=#{t}"})
    void deleteByTicket(String t);
}
