<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>회원 관리</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script>
        window.onload = getList;

        function refresh() {
            location.reload();
        }

        function getList() {
            $.ajax({
                type: "post",
                async: false,
                url: "${contextPath}/getList.do",
                success: function (data) {
                    var jsonArray = data;
                    var memberList = JSON.parse(jsonArray);

                    for (var i = 0; i < memberList.length; i++) {
                        var tnode1 = document.createTextNode(memberList[i].num);
                        var tnode2 = document.createTextNode(memberList[i].name);
                        var tnode3 = document.createTextNode(memberList[i].tel);

                        var row = tbl.insertRow(-1);
                        var cell1 = row.insertCell(0);
                        var cell2 = row.insertCell(1);
                        var cell3 = row.insertCell(2);

                        cell1.appendChild(tnode1);
                        cell2.appendChild(tnode2);
                        cell3.appendChild(tnode3);
                    }
                },
            });
        }
        $(function () {
            $("#regist").click(function () {

                if ($("#memberNum").val().length === 0) {
                    alert("번호를 입력하세요");
                    $("#memberNum").focus();
                    return false;
                } else if ($("#name").val().length === 0) {
                    alert("이름를 입력하세요");
                    $("#name").focus();
                    return false;
                } else if ($("#phone").val().length === 0) {
                    alert("전화번호를 입력하세요");
                    $("#phone").focus();
                    return false;
                }

                var member = {
                    memberNum : $("#memberNum").val(),
                    name : $("#name").val(),
                    phone : $("#phone").val()
                }
                var jsonString = JSON.stringify(member);

                $.ajax({
                    type:"post",
                    async:false,
                    url:"${contextPath}/reg.do",
                    data:{member:jsonString},
                    success: function () {
                        $("#result").html("등록에 성공했습니다.");
                    },
                    error: function () {
                        alert("등록에 실패했습니다.");
                    },
                    complete: function () {

                    }
                })
            });
        });


    </script>
</head>
<body>
<button type="button" onclick="refresh()">목록보기</button>
<br><br>
<table id="tbl" border="1">
    <thead>
    <tr>
        <th>번호</th><th>이름</th><th>전화번호</th>
    </tr>
    </thead>
    <tbody>

    </tbody>
</table>

<table>
    <tr>
    <td>번호</td><td><input type="text" name="memberNum" id="memberNum"></td>
    <td><button type="button" id="regist">등록</button></td>
    </tr>
    <tr>
    <td>이름</td><td><input type="text" name="name" id="name"></td>
    </tr>
    <tr>
    <td>전화번호</td><td><input type="text" name="phone" id="phone"></td>
    </tr>
    <tr>
        <td><div id="result"></div></td>
    </tr>
</table>
</body>
</html>