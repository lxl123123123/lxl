icon = document.getElementsByClassName('icon')[0];
ename2 = document.getElementsByClassName('ename2')[0];
pica = document.getElementsByClassName('pica')[0];
picb = document.getElementsByClassName('picb')[0];
picc = document.getElementsByClassName('picc')[0];
picd = document.getElementsByClassName('picd')[0];
zhuye = document.getElementsByClassName('zhuye')[0];
setTimeout(() => {
    zhuye.style.cssText = "display: block";
}, 7999);
setTimeout(() => {
    icon.style.cssText = "animation-name: picblur; animation-duration: 0.75s; animation-timing-function: ease-in-out; animation-fill-mode: forwards;";
    ename2.style.cssText = "animation-name: picblur; animation-duration: 0.75s; animation-timing-function: ease-in-out; animation-fill-mode: forwards;";
}, 9000);
setTimeout(() => {
    pica.style.cssText = "opacity: 0.2";
    picb.style.cssText = "opacity: 0.2";
    picc.style.cssText = "opacity: 0.2";
    picd.style.cssText = "opacity: 0.2";
}, 9500);
setTimeout(() => {
    pica.style.cssText = "animation-name: picclear; animation-duration: 0.75s; animation-timing-function: ease-in-out; animation-fill-mode: forwards;";
}, 10500);
setTimeout(() => {
    picb.style.cssText = "animation-name: picclear; animation-duration: 0.75s; animation-timing-function: ease-in-out; animation-fill-mode: forwards;";
}, 11500);
setTimeout(() => {
    picc.style.cssText = "animation-name: picclear; animation-duration: 0.75s; animation-timing-function: ease-in-out; animation-fill-mode: forwards;";
}, 12900);
setTimeout(() => {
    picd.style.cssText = "animation-name: picclear; animation-duration: 0.75s; animation-timing-function: ease-in-out; animation-fill-mode: forwards;";
    setTimeout(()=>{
        zhuye.style.display="none"
        M_con.style.display="block"
    },500)
}, 14250);