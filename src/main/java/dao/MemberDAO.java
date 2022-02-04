package dao;

import dto.MemberDTO;

import java.sql.*;
import java.util.ArrayList;

public class MemberDAO {

    public Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            return DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "system", "1234");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean regist(MemberDTO member) {
        String sql = "INSERT INTO MEMBER VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, member.getNum());
            pstmt.setString(2, member.getName());
            pstmt.setString(3, member.getTel());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<MemberDTO> getList() {
        String sql = "SELECT * FROM MEMBER";
        try (Statement stmt = getConnection().createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            ArrayList<MemberDTO> members = new ArrayList<>();

            while (rs.next()) {
                MemberDTO member = new MemberDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                );
                members.add(member);
            }

            return members;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
