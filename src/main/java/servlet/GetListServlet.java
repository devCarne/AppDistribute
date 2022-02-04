package servlet;

import dao.MemberDAO;
import dto.MemberDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/getList.do")
public class GetListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        MemberDAO dao = new MemberDAO();
        ArrayList<MemberDTO> memberList = dao.getList();

        JSONArray jsonArray = new JSONArray();
        for (MemberDTO member : memberList) {
            JSONObject tempObject = new JSONObject();
            tempObject.put("num", member.getNum());
            tempObject.put("name", member.getName());
            tempObject.put("tel", member.getTel());
            jsonArray.add(tempObject);
        }

        out.print(jsonArray);
    }
}
