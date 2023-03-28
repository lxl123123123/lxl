text1 = document.getElementsByClassName('text1')[0];
text2 = document.getElementsByClassName('text2')[0];
text3 = document.getElementsByClassName('text3')[0];
pic1 = document.getElementsByClassName('pic1')[0];
pic2 = document.getElementsByClassName('pic2')[0];
pic3 = document.getElementsByClassName('pic3')[0];
pic4 = document.getElementsByClassName('pic4')[0];
ename = document.getElementsByClassName('ename')[0];
cname = document.getElementsByClassName('cname')[0];
direction = document.getElementsByClassName('direction')[0];
container = document.getElementsByClassName('container')[0];
setTimeout(() => {
    setTimeout(() => {
        pic1.style.cssText = "animation-name: picclear; animation-duration: 0.5s; animation-timing-function: ease-in-out; animation-fill-mode: forwards;";
    }, 0);
    setTimeout(() => {
        pic2.style.cssText = "animation-name: picclear; animation-duration: 0.5s; animation-timing-function: ease-in-out; animation-fill-mode: forwards;";
        text1.style.cssText = "animation-name: textblur; animation-duration: 0.5s; animation-timing-function: ease-in-out; animation-fill-mode: forwards;";
        text2.style.cssText = "animation-name: textclear; animation-duration: 0.5s; animation-timing-function: ease-in-out; animation-fill-mode: forwards;";
    }, 1500);
    setTimeout(() => {
        pic3.style.cssText = "animation-name: picclear; animation-duration: 0.5s; animation-timing-function: ease-in-out; animation-fill-mode: forwards;";
        text2.style.cssText = "animation-name: textblur; animation-duration: 0.5s; animation-timing-function: ease-in-out; animation-fill-mode: forwards;";
    }, 3000);
    setTimeout(() => {
        pic4.style.cssText = "animation-name: picclear; animation-duration: 0.5s; animation-timing-function: ease-in-out; animation-fill-mode: forwards;";
        text3.style.cssText = "animation-name: textclear; animation-duration: 0.5s; animation-timing-function: ease-in-out; animation-fill-mode: forwards;";
    }, 4500);
    setTimeout(() => {
        text3.style.cssText = "animation-name: textblur; animation-duration: 0.5s; animation-timing-function: ease-in-out; animation-fill-mode: forwards;";
        ename.style.cssText = "animation-name: textclear; animation-duration: 0.5s; animation-timing-function: ease-in-out; animation-fill-mode: forwards;";
        cname.style.cssText = "animation-name: textclear; animation-duration: 0.5s; animation-timing-function: ease-in-out; animation-fill-mode: forwards;";
        direction.style.cssText = "animation-name: textclear; animation-duration: 0.5s; animation-timing-function: ease-in-out; animation-fill-mode: forwards;";
    }, 6000);
    setTimeout(() => {
        container.style.cssText = "display: none";
    }, 7000);
}, 1000);