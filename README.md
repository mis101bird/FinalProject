# FinalProject

Dear all

Here is final project for CS3715 Final Project.

##Maria part:
login.html: check if the user is admin or a student, check for incorrect password/ user id, it takes you to logout page when you click login.

signup.html: sent sign up sucessfully mail,  matches the 2 password fields, unique username.

logput.: if you press the logout button, takes you to login page

##Thaer part:
Book Genre page: classify books in there, have different genres. 

Book detail page: book cover, youtube, Description, link to reservation page, adding the books into the database.

Search Engine page : post find a text named "search" , seach in database, show info like Genre page. 

##Hsuan-Ju part:
2-index.html: have before-index and after-index, iframe for login-logout, place for sign up, after-index will show student's detail.

forget password page: sent mail to student about password.
##Bootstrap Framework:
Bootstrap just a framework writen by css and javascript. We can see the bootstraps document and use it's class in our own webpage:

<a href="http://www.w3schools.com/bootstrap/">W3C bootstrap tutorial</a>


<a href="http://bootstrapdocs.com/v3.2.0/docs/components/#top">Bootstrap official tutorial</a>
##Database:

*bold one is foreign key or primary key.

####account table:
<table>
<tr>
<th>UID</th><td>name</td><td>password</td><td>email</td>
</tr>
<tr>
<td>User id</td><td></td><td></td>
</tr>
</table>
####borrow table:
<table>
<tr>
<th>UID</th><th>BID</th><td>deadline</td>
</tr>
<tr>
<td>User id</td><td>book id</td><td>borrow deadline</td>
</tr>
</table>
####reservation table:
<table>
<tr>
<th>UID</th><th>BID</th>
</tr>
<tr>
<td>User id</td><td>book id</td>
</tr>
</table>
####book table:
<table>
<tr>
<th>BID</th><td>name</td><td>author</td><td>status</td><td>cover</td><td>description</td><td>category</td>
</tr>
<tr>
<td>Book Id</td><td>book name</td><td>Book's Author</td><td>Borrowd/available</td>Cover of the book<td>XXX.png</td><td>about the book</td><td>Science/Food/Literature/etc..</td>
</tr>
</table>
Notes: Admin page which deletes and modify user's account.
<a href="http://www.tutorialspoint.com/sqlite/sqlite_java.htm">SQLite tutorial</a>
