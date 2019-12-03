# mobileCard API doc

# Search available employees

Description

*return all available employess as html with posiblity to genertate VCard*

API call

*http://localhost:8080/mobileCard/generateVCard?searchPerson=nazwisko*

Example response

<a href="/user/profile.php?id=180" title="Tomasz Jawroski"></a>
 <h3><a href="/user/profile.php?id=180" title="Tomasz Jawroski">Tomasz Jawroski</a></h3>
 
 <div class="extra-info">
  <ul>
   <li> <span class="item-title">Afiliacja do:</span> <span class="item-content">RSO</span> </li>
  </ul>
 </div>
 <div class="sendmail-link btn btn-default">
  <img class="smallicon" src="https://adm.edu.p.lodz.pl/theme/image.php/adaptable/core/1575265848/i/email" alt="">
  <a href="/user/usermailform.php?user=180">Wyślij wiadomość</a>
 </div>
 <a class="fullprofile-link btn btn-default" href="https://adm.edu.p.lodz.pl/user/view.php?id=180">Profil publiczny</a><br>
 <a class="fullprofile-link btn btn-default" href="https://adm.edu.p.lodz.pl/user/fullprofile.php?id=180">Pełny profil</a> <br>
</div><a href=/api/html/users/nowak/vcard/1>
<button>Generate Vcard</button></a><div class="user-info">

# return VCard for employ

Description

*return VCard for emploe*

API call

*http://localhost:8080/mobileCard/users/Jaworski/generate/1*

Example response

BEGIN:VCARD <br >
    VERSION:4.0 <br >
    PRODID:ez-vcard 0.10.5 <br >
    FN:Przemysław Nowak <br >
    ORG:I72 - Instytut Informatyki <br >
    END:VCARD <br >


