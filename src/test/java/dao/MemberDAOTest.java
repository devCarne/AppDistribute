package dao;

import dto.MemberDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberDAOTest {

    MemberDAO dao = new MemberDAO();

    @Test
    void getConnection() {
        assertNotNull(dao.getConnection());
    }

    @Test
    void regist() {
        assertTrue(dao.regist(new MemberDTO(1, "홍길동", "010-1111-1111")));
    }

    @Test
    void getList() {
        assertNotNull(dao.getList());
    }
}