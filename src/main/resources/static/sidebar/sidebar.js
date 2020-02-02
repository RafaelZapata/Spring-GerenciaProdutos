$("#menu-toggle").click(function(e) {
	e.preventDefault();
	$("#wrapper").toggleClass("toggled");
});

function imgChange(){
    Array.from(animais).forEach(function(animal) {
        var txt = animal.innerText;
        var img;
        if(txt == "Canino") img = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Creative-Tail-Animal-dog.svg/1024px-Creative-Tail-Animal-dog.svg.png";
        if(txt == "Felino") img = "http://www.iconsalot.com/asset/icons/pixel-perfect/animals/512/cat-icon.png";
        if(txt == "Equino") img = "https://gilletteveterinarian.com/wp-content/uploads/2016/03/Horse-Icon.png";
        if(txt == "Roedor") img = "http://www.iconsalot.com/asset/icons/freepik/pet-shop-17/128/014-hamster-icon.png";
        if(txt == "Ave") img = "http://www.iconsalot.com/asset/icons/freepik/pet-shop-17/512/022-bird-icon.png";
        if(txt == "Bovino") img = "https://gilletteveterinarian.com/wp-content/uploads/2016/03/Cow-Icon.png";
        animal.nextElementSibling.firstChild.src = img;
    });
    
}

function hiddenChange() {
  var elem = this.nextElementSibling;
  let img = this.firstChild;
  
  if(elem.hidden == false){
    elem.hidden = true;
    img.src= "https://cdn0.iconfinder.com/data/icons/user-interface-material-4-1/26/369-512.png";
  }else {
    elem.hidden = false;
    img.src= "https://cdn4.iconfinder.com/data/icons/web-mobile-flat1/200/Untitled-36-512.png";
    imgChange();
  }
}

animais = document.getElementsByClassName("listaAnimais");
classname = document.getElementsByClassName("mostrarAnimais");

Array.from(classname).forEach(function(element) {
    element.addEventListener("click", hiddenChange);
});

