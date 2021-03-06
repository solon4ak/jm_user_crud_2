<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View user</title>
    </head>
    <body>
        
        <h2>View user</h2>
        <p>
            <a href="<c:url value="/list" />">List all</a>
        </p>
        <p>
            <i>
                Created: <fmt:formatDate type="both" dateStyle="medium" value="${user.dateCreated}"/>
            </i>
        </p>
        <p>
            <i>
                Last updated: <fmt:formatDate type="both" dateStyle="medium" value="${user.lastUpdate}"/>
            </i>
        </p>

        <table border="0" width="300" cellpadding="5">
            <tbody>
                <tr>
                    <td>Nickname</td>
                    <td>${user.nickName}</td>
                </tr>
                <tr>
                    <td>First name</td>
                    <td>${user.firstName}</td>
                </tr>
                <tr>
                    <td>Last name</td>
                    <td>${user.lastName}</td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td>${user.email}</td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td>${user.address}</td>
                </tr>
                <tr>
                    <td>Phone number</td>
                    <td>${user.phoneNumber}</td>
                </tr>
                <tr>
                    <td>Birth date</td>
                    <td>
                        <fmt:formatDate value="${user.birthDate}" type="date" dateStyle="medium"/>
                    </td>
                </tr>
                <tr>
                    <td>User role</td>
                    <td>
                        ${user.role}
                    </td>
                </tr>
            </tbody>
        </table>
        <p>
            <a href="<c:url value="/edit/${user.id}" />">
                Edit
            </a> /
            <a href="<c:url value="/delete/${user.id}"/>">
                Delete
            </a>
        </p>

    </form>
</body>
</html>