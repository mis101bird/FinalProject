function Admin( s ){
console.log('in Admin');
var xmlhttp;
var PostURL="adminaccount/"+s;
if (window.XMLHttpRequest)
  {
  xmlhttp=new XMLHttpRequest();
  }
else
  {
  xmlhttp=new ActiveXObject('Microsoft.XMLHTTP');
  }
xmlhttp.open('POST', PostURL, true );
xmlhttp.onreadystatechange = function() {
            if ( xmlhttp.readyState != 4) return;
            if ( xmlhttp.status == 200 || xmlhttp.status == 400) {
			console.log('in Admin:got Ajax response:'+s);
            document.getElementById('ao'+s).innerHTML="<a href='javascript:deleteAccount(s);' class='btn btn-primary btn-warning'>Delete</a><span class='glyphicon glyphicon-star'></span>";
            }
        };
xmlhttp.send();
}
  function deleteAccount( s ){
console.log('in deleteAccount');
var xmlhttp;
var PostURL='deleteuser/'+s;
if (window.XMLHttpRequest)
  {
  xmlhttp=new XMLHttpRequest();
  }
else
  {
  xmlhttp=new ActiveXObject('Microsoft.XMLHTTP');
  }
xmlhttp.open('POST', PostURL, true );
xmlhttp.onreadystatechange = function() {
            if ( xmlhttp.readyState != 4) return;
            if ( xmlhttp.status == 200 || xmlhttp.status == 400) {
			console.log('delete Account:got Ajax response:'+s);
            document.getElementById('a'+s).innerHTML='';
            }
        };
xmlhttp.send();
}
function deleteBook( s )
{
console.log('in deleteBook');
var xmlhttp;
var PostURL="deletebook/"+s;
if (window.XMLHttpRequest){
  xmlhttp=new XMLHttpRequest();
  }
else
  {
  xmlhttp=new ActiveXObject('Microsoft.XMLHTTP');
  }
xmlhttp.open('POST', PostURL, true );
xmlhttp.onreadystatechange = function() {
            if ( xmlhttp.readyState != 4) return;
            if ( xmlhttp.status == 200 || xmlhttp.status == 400) {
			console.log('delete Book:got Ajax response');
            document.getElementById('b'+s).innerHYML='';
            }
        };
xmlhttp.send();
}

