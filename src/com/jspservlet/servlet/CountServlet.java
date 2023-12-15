package com.jspservlet.servlet;

import com.jspservlet.util.dbConnectUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/CountServlet")
public class CountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        // 生成 CSV 内容
        StringBuilder csvContent = new StringBuilder();
        Connection conn = dbConnectUtil.connect();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT author_name,category_name,COUNT(DISTINCT ISBN) FROM book GROUP BY author_name,category_name;");
            // 构建 CSV 表头（种类名）
            ResultSet resultSet =  ps.executeQuery();
            List<String> categoryNames = getCategoryNamesFromResultSet(resultSet);
            csvContent.append(",").append(String.join(",", categoryNames)).append("\n");

// 构建 CSV 内容
            Map<String, Map<String, Integer>> authorCategoryBookCount = new HashMap<>();

            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String author = resultSet.getString("author_name");
                String category = resultSet.getString("category_name");
                int bookCount = resultSet.getInt(3);

                authorCategoryBookCount.computeIfAbsent(author, k -> new HashMap<>()).put(category, bookCount);
            }

// 迭代作者和种类，构建 CSV 行
            for (String author : authorCategoryBookCount.keySet()) {
                Map<String, Integer> categoryBookCount = authorCategoryBookCount.get(author);
                csvContent.append(author).append(",");
                for (String categoryName : categoryNames) {
                    int count = categoryBookCount.getOrDefault(categoryName, 0);
                    csvContent.append(count).append(",");
                }
                csvContent.append("\n");
            }

// 设置响应内容类型
            response.setContentType("text/csv;charset=gbk");
            response.setHeader("Content-Disposition", "attachment; filename=\"book_data.csv\"");

// 将 CSV 内容写入响应
            try (PrintWriter writer = response.getWriter()) {
                writer.write(csvContent.toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);

    }

    private List<String> getCategoryNamesFromResultSet(ResultSet resultSet) throws SQLException {
        Set<String> categoryNames = new HashSet<>();
        while (resultSet.next()) {
            categoryNames.add(resultSet.getString("category_name"));
        }
        return new ArrayList<>(categoryNames);
    }
}
