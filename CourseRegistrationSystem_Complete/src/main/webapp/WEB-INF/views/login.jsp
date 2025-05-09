<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="login" method="post">
  Email: <input type="text" name="email" /><br/>
  Password: <input type="password" name="password" /><br/>
  <input type="submit" value="Login" />
</form>
<p style="color:red">${error}</p>
