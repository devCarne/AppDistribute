package servlet;

import dao.MemberDAO;
import dto.MemberDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reg.do")
public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        System.out.println(req.getParameter("member"));
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(req.getParameter("member"));

            MemberDTO member = new MemberDTO(
                    Integer.parseInt((String) jsonObject.get("memberNum")),
                    (String) jsonObject.get("name"),
                    (String) jsonObject.get("phone")
            );

            MemberDAO dao = new MemberDAO();
            dao.regist(member);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
