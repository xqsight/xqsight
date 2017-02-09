<%@ include file="header.jsp" %>

<p>
<% if (request.getAttribute("edu.yale.its.tp.cas.badUsernameOrPassword") 
       != null) { %>
<font color="red">Sorry, you entered an invalid NetID or password. <br />
  Please try again. 
</font>
<% } else if (request.getAttribute("edu.yale.its.tp.cas.service") == null) { %>
  You may establish Yale authentication now in order to access protected
  services later.
<% } else { %>
  You have requested access to a site that requires Yale
  authentication. 
<% } %>
</p>
</font>

<font face="Arial,Helvetica">
<p>
Enter your Yale NetID and password below; then click on the <b>Login</b>
button to continue.
</p>
</font>


        <table bgcolor="#FFFFAA" align="center"><tr><td>

        <table border="0" cellpadding="0" cellspacing="5">


        <tr>
        <td><font face="Arial,Helvetica"><b>NetID:</b></td>
        <td>
        <form method="post" name="login_form">
        <input type="text" name="username" maxlength="8"></td>
        </tr>

        <tr>
        <td><font face="Arial,Helvetica"><b>Password:</b></td>
        <td><input type="password" name="password"></td>
        </tr>

        <tr>
        <td colspan="2" align="left">
	<input type="checkbox" name="warn" value="true" />
        <small>
	    <small>Warn me before logging me in to other sites.</small>
        </small>
	</tr>

        <tr>
        <td colspan="2" align="right">
        <input type="submit" value="Login">
        </form>
        </td>
        </tr>

        </td></tr></table>

        </td></tr></table>

</td></tr>

<tr><td colspan="2">
<center>
<font color="red" face="Arial,Helvetica">
<i><b>For security reasons, quit your web browser when you are done
accessing services that require authentication!</b></i>
</font>
</center>
</td></tr>


<tr><td colspan="2">
<p>
<font face="Arial,Helvetica" size="1">
Be wary of any program or web page that asks you for your NetID and
password. Secure Yale web pages that ask you for your NetID and password
will generally have URLs that begin with "https://www.yale.edu" or
"https://secure.its.yale.edu". In addition, your browser should visually
indicate that you are accessing a secure page.
</p>
<p>
<a target="new" href="http://www.yale.edu/its/feedback/">Information Technology Services</a>
</p>

</td></tr>

</table>
</table>
</table>
