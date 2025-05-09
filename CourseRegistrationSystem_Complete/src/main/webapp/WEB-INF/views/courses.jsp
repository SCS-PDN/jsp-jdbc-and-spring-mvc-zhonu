<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Available Courses</h2>
<table border="1">
  <tr><th>Name</th><th>Instructor</th><th>Credits</th><th>Action</th></tr>
  <c:forEach var="course" items="${courses}">
    <tr>
      <td>${course.name}</td>
      <td>${course.instructor}</td>
      <td>${course.credits}</td>
      <td>
        <form action="register/${course.courseId}" method="post">
          <input type="submit" value="Register" />
        </form>
      </td>
    </tr>
  </c:forEach>
</table>
