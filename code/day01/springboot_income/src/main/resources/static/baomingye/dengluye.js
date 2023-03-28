var phonenumber = document.querySelector('.zhanghao').querySelector('input');
var yanzhengma = document.querySelector('.pwd').querySelector('input');
var huoqu = document.querySelector('.huoqu');
var con = document.querySelector('.con');
var login = document.querySelector('.login');
var con1 = document.querySelector('.con1');
let K_re = /^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(16[5,6])|(17[0-8])|(18[0-9])|(19[1、5、8、9]))\d{8}$/;
var time = 8;
var temp=null;
var background = document.querySelector('.background');
var dataye = document.querySelector('.data');
var baoming = document.querySelector('.baoming');
var dianjijinru = document.querySelector('.dianjijinru');
var bumenjieshao = document.querySelector('.bumenjieshao');


dianjijinru.onclick = function (){
    background.style.display='block';
    baoming.style.display='none';
}
bumenjieshao.onclick = function(){
    window.location.href="../bumenxuanze/bumenchakan.html";
}

// 获取验证码
huoqu.onclick = function() {
    huoqu.disabled = true;
    huoqu.children[3].innerHTML='9';
    huoqu.className="huoqu-1";
    huoqu.children[0].style.display='none';
    huoqu.children[1].className = 'wave';
    huoqu.children[2].className = 'wave a-2';
    huoqu.children[3].style.display='block';
    var timer = setInterval(function() {
        if(time > 0){
            huoqu.children[3].innerHTML = time ;
            time--;
        } else {
            clearInterval(timer);
            huoqu.disabled = false;
            time = 8;
            huoqu.className="huoqu";
            huoqu.children[0].style.display='block';
            huoqu.children[1].className = '';
            huoqu.children[2].className = '';
            huoqu.children[3].style.display='none';
        }
    },1000);

   if((phonenumber.value).match(K_re) && (phonenumber.value.length === 11)){
    const xhr = new XMLHttpRequest;

    xhr.open('POST','http://124.221.253.24:9081/User/getCode');

    xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');

    xhr.send('phone='+phonenumber.value);

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if(xhr.status>=200 &&  xhr.status < 300){
                    con.innerHTML="最新验证码："+(JSON.parse(xhr.responseText)).data;
                    con.style.display='block';
                    temp = xhr.response;
            }
            else{
                    con.innerHTML="发送有误！";
                    con.style.display='block';
            }
    }
   }
}
   else{
    con1.style.display='block';
   }
}

phonenumber.onfocus = function () {
    con1.style.display='none';
}

login.onclick =function() {
    if((phonenumber.value).match(K_re) && (phonenumber.value.length === 11) && (yanzhengma.value!='' ) && (yanzhengma.value!='验证码不正确!')){ 
        const xhr1 = new XMLHttpRequest;

        xhr1.open('POST','http://124.221.253.24:9081/User/login');
    
        xhr1.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
    
        xhr1.send('phone='+phonenumber.value+'&code='+ yanzhengma.value);
    
        xhr1.onreadystatechange = function(){
            if(xhr1.readyState === 4){
                if(xhr1.status>=200 &&  xhr1.status < 300){
                    if(((JSON.parse(xhr1.responseText)).flag) == true){
                        if((JSON.parse(xhr1.responseText)).data == 1){
                            dataye.style.display='block';
                            background.style.display='none';
                        }
                        else{
                            window.location.href="../jieguoye/jieguoye.html";
                        }
                    }else{
                        yanzhengma.style.color = 'red';
                        yanzhengma.value='验证码不正确!';
                        con.innerHTML="请重新获取验证码！"
                        con.style.display='block';
                    }
                }
                else{
                        con.innerHTML="发送有误！";
                        con.style.display='block';
                }
    }
   }
    }
   else if(!((phonenumber.value).match(K_re) && (phonenumber.value.length === 11))){
        con1.style.display='block';
    }
   else{
    yanzhengma.style.color = 'red';
    yanzhengma.value='验证码不正确!';
   } 
}

yanzhengma.onfocus = function(){
    yanzhengma.value='';
    yanzhengma.style.color = '#000';
}