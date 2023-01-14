package com.example.demo.src.search;

import com.example.demo.src.search.model.GetSearchAllRes;
import com.example.demo.src.search.model.GetSearchBoardRes;
import com.example.demo.src.search.model.GetSearchScholarshipRes;
import com.example.demo.src.search.model.GetSearchSupportRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SearchDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<GetSearchAllRes> searchAll(String query) {
        String searchQuery = "SELECT Scholarship_name as 'name', Scholarship_content as 'content', Scholarship_view as 'view' " +
                "FROM Scholarship " +
                "WHERE Scholarship_name like ? " +
                "or Scholarship_content like ? " +
                "UNION ALL " +
                "select Support_name, Support_content, Support_view " +
                "from Support " +
                "where Support_name like ? " +
                "or Support_content like ? " +
                "order by 'view' desc";
        String wrappedKeyword = "%" + query + "%";

        return this.jdbcTemplate.query(searchQuery,
                (rs, rowNum) -> new GetSearchAllRes(
                        rs.getString("name"),
                        rs.getString("content"),
                        rs.getInt("view")
                ),
                wrappedKeyword, wrappedKeyword, wrappedKeyword, wrappedKeyword
        );
    }

    public List<GetSearchBoardRes> searchBoard(String query) {
        String searchQuery = "select * " +
                "from Board " +
                "where post_name like ? " +
                "or post_content like ? " +
                "order by post_view desc";
        String wrappedKeyword = "%" + query + "%";

        return this.jdbcTemplate.query(searchQuery,
                (rs, rowNum) -> new GetSearchBoardRes(
                        rs.getLong("post_idx"),
                        rs.getString("post_name"),
                        rs.getString("post_content"),
                        rs.getString("post_image"),
                        rs.getInt("post_view"),
                        rs.getInt("post_recommend"),
                        rs.getInt("post_comment")
                ),
                wrappedKeyword, wrappedKeyword
        );
    }

    public List<GetSearchScholarshipRes> searchScholarship(String query) {
        String searchQuery = "select * " +
                "from Scholarship " +
                "where Scholarship_name like ? " +
                "or Scholarship_content like ? " +
                "order by Scholarship_view desc";
        String wrappedKeyword = "%" + query + "%";

        return this.jdbcTemplate.query(searchQuery,
                (rs, rowNum) -> new GetSearchScholarshipRes(
                        rs.getLong("scholarship_idx"),
                        rs.getString("scholarship_name"),
                        rs.getString("scholarship_institution"),
                        rs.getString("scholarship_content"),
                        rs.getString("scholarship_image"),
                        rs.getInt("scholarship_view"),
                        rs.getInt("scholarship_comment"),
                        rs.getString("scholarship_term")
                ),
                wrappedKeyword, wrappedKeyword
        );
    }

    public List<GetSearchSupportRes> searchSupport(String query) {
        String searchQuery = "select * " +
                "from Support " +
                "where Support_name like ? " +
                "or Support_content like ? " +
                "order by Support_view desc";
        String wrappedKeyword = "%" + query + "%";

        return this.jdbcTemplate.query(searchQuery,
                (rs, rowNum) -> new GetSearchSupportRes(
                        rs.getLong("support_idx"),
                        rs.getString("support_policy"),
                        rs.getString("support_name"),
                        rs.getString("support_institution"),
                        rs.getString("support_content"),
                        rs.getString("support_image"),
                        rs.getInt("support_view"),
                        rs.getInt("support_comment")
                ),
                wrappedKeyword, wrappedKeyword
        );
    }


}
