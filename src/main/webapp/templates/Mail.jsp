
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Send Email</title>
</head>
<body>
<jsp:include page="includes/navbar.jsp"/>
<form action="${pageContext.request.contextPath}/Mail" method="post">
    <label for="recipient">Recipient Email:</label><br>
    <input type="email" id="recipient" name="recipient" required><br><br>

    <label for="subject">Subject:</label><br>
    <input type="text" id="subject" name="subject" required><br><br>

    <label for="message">Message:</label><br>
    <textarea id="message" name="message" rows="5" cols="30" required></textarea><br><br>

    <button type="submit">Send Email</button>
</form>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>
