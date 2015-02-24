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

looking bootstrap about RWD!!

##Database:

*bold one is foreign key or primary key.

####account table:
<table>
<tr>
<th>UID</th><td>name</td><td>password</td>
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
<th>01</th><td>The Pale King</td><td>David Foster Wallace</td><td>Changable field!!</td><td><img src="http://1.bp.blogspot.com/-XsYyZHhWAng/TkmDh10R3fI/AAAAAAAAEGo/UgOJFfXR4UQ/s1600/006.jpg" alt="For some reason the image provided could not be loaded"></td><td>description</td><td>friction</td>
</tr>
<tr>
<td>book id</td><td>book name</td><td>book's author</td><td>borrow/free</td><td>img/XXX.png</td><td>about the book</td><td>Science/Food/Literature/etc..</td>
</tr>
</table>
Notes: Admin page which deletes and modify user's account.
