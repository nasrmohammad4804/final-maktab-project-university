<!DOCTYPE html>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../../resource/css/showAllUser.css">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
</head>


<div class="header">

    <form action="">
        <input type="text" placeholder="firstName" id="firstName">
        <input type="text" placeholder="lastName" id="lastName">
        <input list="userType" name="role" placeholder="role" id="role">
        <datalist id="userType" >
            <option value="student">student</option>
            <option value="master">master</option>
        </datalist>
        <input type="submit" value="searching" id="submit-searching">

    </form>
</div>
<p id="not-found-message">
    dont find any user</p>
<body>
<div class="container my-2" id="parent-container">
    <h2 style="color: #ff8880;margin-top: 15px">sorting</h2>

    <div class="my-container">
        <div class="sorting"> <a href="/user-all/${currentPage}?sortField=first_name&sortDir=${reverseSortDir}">first name</a></div>
        <div class="sorting"> <a href="/user-all/${currentPage}?sortField=last_name&sortDir=${reverseSortDir}">last name</a></div>
        <div class="sorting"> <a href="/user-all/${currentPage}?sortField=user_name&sortDir=${reverseSortDir}">user name</a></div>
    </div>
    <table border="1px" class="able table-striped table-responsive-md">
        <tr>
            <th>firstName</th>
            <th>lastName</th>
            <th>userName</th>
            <th>state</th>
        </tr>
        <c:forEach var="user" items="${allUser}">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.userName}</td>

                <c:choose>
                    <c:when test="${user.registerState eq 'CONFIRM'}">
                        <td > <a style="border-radius: 3px" href="/change-profile?id=${user.id}" id="green">confirm</a></td>
                    </c:when>

                    <c:when test="${user.registerState eq 'WAITING'}">
                        <td > <a style="border-radius: 3px" href="/confirm-user/${user.id}" id="orange">waiting</a> </td>
                    </c:when>
                </c:choose>
            </tr>
        </c:forEach>
    </table>

</div>

<div >
    <c:choose>
        <c:when test="${totalPages > 1}">
            <div class="row col-sm-10" id="number-pagination" >
                <div class="col-sm-2" style="color: #ff8880;font-weight: bold" >
                    Total Rows : ${totalItems}
                </div>

                <div class="col-sm-1" style="display: flex;flex-direction: row">

                    <c:forEach var="item" begin="1" end="${totalPages}" >
                        <c:choose>
                            <c:when test="${currentPage != item}">
                                <span> <a style="background: greenyellow;padding: 4px;border-radius: 3px" href="/user-all/${item}?sortField=${sortField}&sortDir=${sortDir}">${item}</a>  &nbsp; &nbsp; </span>
                            </c:when>
                            <c:when test="${currentPage ==item}">
                                <span>  <span style="background: darkgrey;padding: 4px;border-radius: 3px">${item}</span> &nbsp; &nbsp; </span>

                            </c:when>
                        </c:choose>
                    </c:forEach>

                </div>

                <div class="col-sm-1" >
                    <c:choose>
                        <c:when test="${currentPage < totalPages}">
                            <a style="background: greenyellow;padding: 4px;border-radius: 3px" href="/user-all/${currentPage +1}?sortField=${sortField}&sortDir=${sortDir}">Next</a>
                        </c:when>
                        <c:otherwise>
                            <span style="background: darkgrey;padding: 4px;border-radius: 3px">Next</span>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="col-sm-1" >
                    <c:choose>
                        <c:when test="${currentPage > 1 && currentPage <= totalPages}">
                            <a style="background: greenyellow;padding: 4px;border-radius: 3px" href="/user-all/${currentPage -1}?sortField=${sortField}&sortDir=${sortDir}">Last</a>
                        </c:when>
                        <c:otherwise>
                            <span style="background: darkgrey;padding: 4px;border-radius: 3px">Last</span>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </c:when>
    </c:choose>
</div>

<script src="../../../resource/js/jquery-3.6.0.min.js"></script>
<script src="../../../resource/js/showAllUser.js"></script>

</body>
</html>