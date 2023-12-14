package com.jspservlet.dao;

import com.jspservlet.entity.Author;
import com.jspservlet.entity.Book;
import com.jspservlet.entity.Category;
import com.jspservlet.entity.PublishHouse;
import com.jspservlet.util.dbConnectUtil;
import com.sun.xml.internal.ws.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookControl {

    public BookControl() {
    }

    public List<Book> selectAll() throws SQLException {
        List<Book> bookList = new ArrayList<>();
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select * from book order by price;");
            rs = ps.executeQuery();
            while (rs.next()) {
                PublishHouse publishHouse = new PublishHouse();
                publishHouse.setName(rs.getString(7));
                Author author = new Author();
                author.updateName(rs.getString(8));
                Category category = new Category();
                category.setCategoryName(rs.getString(9));
                Book book = new com.jspservlet.entity.Book(rs.getString(2),
                        rs.getString(1),
                        publishHouse,
                        author,
                        rs.getString(3),
                        rs.getString(4).substring(0,4), // TODO
                        category,
                        rs.getInt(5),
                        rs.getDouble(6),
                        rs.getDouble(10),
                        0);
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectUtil.disconnect(conn, ps, rs);
        }

        return bookList;
    }

    public String addString(boolean bool) {
        String sql = "";
        if (!bool) {
            sql += "where ";
        } else {
            sql += "and ";
        }
        return sql;
    }

    public List<Book> inquire(String title, Integer[] publishDate, String authorName,
                              String publishHouseName, String categoryName,
                              Double[] rate, Integer[] commentNumber,
                              Double[] price, String type, Integer rise) {
        List<Book> bookList = new ArrayList<>();
        Book book;
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String nullString = "";
        boolean full = false;
        try {
//            String sql = "select ISBN,book_name,book_description,YEAR(time),comment_num,pos_rate,press_name,author_name,category_name,price from book ";
            String sql = "select * from book ";

            if (!title.equals(nullString)) {
                sql += addString(full) + "book_name = '" + title +"' ";
                full = true;
            }
            if (!authorName.equals(nullString)) {
                sql += addString(full) + "author_name = '" + authorName + "' ";
                full = true;
            }
            if (!publishHouseName.equals(nullString)) {
                sql += addString(full) + "press_name = '" + publishHouseName + "' ";
                full = true;
            }
            if (!categoryName.equals(nullString)) {
                sql += addString(full) + "category_name = '" + categoryName + "' ";
            }
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PublishHouse publishHouse = new PublishHouse();
                publishHouse.setName(rs.getString(7));
                Author author = new Author();
                author.updateName(rs.getString(8));
                Category category = new Category();
                category.setCategoryName(rs.getString(9));
                book = new com.jspservlet.entity.Book(rs.getString(2),
                        rs.getString(1),
                        publishHouse,
                        author,
                        rs.getString(3),
                        rs.getString(4).substring(0,4), // TODO
                        category,
                        rs.getInt(5),
                        rs.getDouble(6),
                        rs.getDouble(10),
                        0);
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectUtil.disconnect(conn, ps, rs);
        }
        if (type.equals("pos_rate")) {
            bookList.sort((book1, book2)
                    -> (int) (100 * book1.getGoodRate() - 100 * book2.getGoodRate()));
        } else if (type.equals("price")) {
            bookList.sort((book1, book2)
                    -> (int) (100 * book1.getPrice() - 100 * book2.getPrice()));
        } else {
            bookList.sort(Comparator.comparingInt(book2 -> Integer.parseInt(book2.getPublishDate())));
        }

        List<Book> bookAnsListRate = new ArrayList<>();
        for (Book value : bookList) {
            if (value.getGoodRate() < rate[1]
                    && value.getGoodRate() >= rate[0]) {
                bookAnsListRate.add(value);
            }
        }

        List<Book> bookAnsListReview = new ArrayList<>();
        for (Book value : bookAnsListRate) {
            if (value.getReviewAmount() < commentNumber[1]
                    && value.getReviewAmount() >= commentNumber[0]) {
                bookAnsListReview.add(value);
            }
        }

        List<Book> bookAnsListPubDate = new ArrayList<>();
        for (Book value : bookAnsListReview) {
            if (Integer.parseInt(value.getPublishDate()) < publishDate[1]
                    && Integer.parseInt(value.getPublishDate()) >= publishDate[0]) {
                bookAnsListPubDate.add(value);
            }
        }

        List<Book> bookAnsListPrice = new ArrayList<>();
        for (Book value : bookAnsListPubDate) {
            if (value.getPrice() >= price[0]
                    && value.getPrice() < price[1]) {
                bookAnsListPrice.add(value);
            }
        }
        if (rise == 1) {
            Collections.reverse(bookAnsListPrice);
        }
        return bookAnsListPrice;
    }

    public Book selectByIsbn(String ISBN) {
        Book book = new Book();
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select * from book where ISBN = ?");
            ps.setString(1, ISBN);
            rs = ps.executeQuery();
            while (rs.next()) {
                PublishHouse publishHouse = new PublishHouse();
                publishHouse.setName(rs.getString(7));
                Author author = new Author();
                author.updateName(rs.getString(8));
                Category category = new Category();
                category.setCategoryName(rs.getString(9));
                book = new Book(rs.getString(2),
                        rs.getString(1),
                        publishHouse, author,
                        rs.getString(3),
                        rs.getString(4),
                        category,
                        rs.getInt(5),
                        rs.getDouble(6),
                        rs.getDouble(10),
                        0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectUtil.disconnect(conn, ps, rs);
        }
        return book;
    }

    public Author selectByAuthor(String name) {
        Book book;
        Author author = new Author();
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select author.*, book.ISBN,book.book_name " +
                    "from author,book where author.author_name=book.author_name and author.author_name= ? ");
            ps.setString(1, name);
            rs = ps.executeQuery();
            author.updateName(name);
            if (rs.next()) {
                author.updateNationality(rs.getString(2));
                author.updateBirthDay(rs.getString(3));
                author.updateDescription(rs.getString(4));
                author.updatePublishNumber(rs.getInt(5));
                List<Book> BookList = new ArrayList<>();
                book = new Book();
                book.setIsbn(rs.getString(6));
                book.setTitle(rs.getString(7));
                BookList.add(book);

                while (rs.next()){
                    book = new Book();
                    book.setIsbn(rs.getString(6));
                    book.setTitle(rs.getString(7));
                    BookList.add(book);
                };
                author.updatePublishBook(BookList);

            }





        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectUtil.disconnect(conn, ps, rs);
        }
        return author;
    }

    public PublishHouse selectByPublishHouse(String publishHouseName) {
        Book book;
        PublishHouse publishHouse = new PublishHouse();
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select press.*,book.ISBN,book.book_name " +
                    "from press,book where press.press_name=book.press_name and book.press_name=?");
            ps.setString(1, publishHouseName);
            rs = ps.executeQuery();
            if(rs.next()){
                publishHouse.setName(publishHouseName);
                publishHouse.setLocation(rs.getString(2));
                publishHouse.setTotalPublish(rs.getInt(3));
                List<Book> BookList = new ArrayList<>();
                book = new Book();
                book.setIsbn(rs.getString(4));
                book.setTitle(rs.getString(5));
                BookList.add(book);
                while (rs.next()) {
                    book = new Book();
                    book.setIsbn(rs.getString(4));
                    book.setTitle(rs.getString(5));
                    BookList.add(book);
                }
                publishHouse.setBookList(BookList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectUtil.disconnect(conn, ps, rs);
        }
        return publishHouse;
    }

    public Category selectByCategory(String categoryName) {
        Book book;
        Category category = new Category();
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select press.*,book.ISBN,book.book_name " +
                    "from press,book where press.press_name=book.press_name and press_name=?");
            ps.setString(1, categoryName);
            rs = ps.executeQuery();
            category.setCategoryName(categoryName);
            category.setTotalNumber(rs.getInt(2));
            List<Book> BookList = new ArrayList<>();
            while (rs.next()) {
                book = new Book();
                book.setIsbn(rs.getString(3));
                book.setTitle(rs.getString(4));
                BookList.add(book);
            }
            category.setBookList(BookList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectUtil.disconnect(conn, ps, rs);
        }
        return category;
    }

    public void addBook(Book book, Category category,
                        Author author, PublishHouse publishHouse) {
        Connection conn = dbConnectUtil.connect();
        PreparedStatement ps=null;
        try {
            ps = conn.prepareStatement("insert into book ISBN, book_name, " +
                    "book_description, time, comment_num, pos_rate, press_name, " +
                    "author_name, category_name, price values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getDescription());
            ps.setString(4, book.getPublishDate());
            ps.setInt(5, book.getReviewAmount());
            ps.setDouble(6, book.getGoodRate());
            ps.setString(7, publishHouse.getName());
            ps.setString(8, author.getName());
            ps.setString(9, category.getCategoryName());
            ps.setDouble(10, book.getPrice());
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnectUtil.disconnect(conn, ps, null);
        }
    }
}
