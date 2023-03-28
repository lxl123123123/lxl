let K_xiala = document.getElementsByClassName('K_chioce')[0].getElementsByClassName('K_xiala')[0];
let K_xialafu = document.getElementsByClassName('K_chioce')[0];
let K_ul = document.getElementsByClassName('K_chioce')[0].getElementsByTagName("ul")[0];
let K_li = K_ul.getElementsByTagName('li');
let data = document.querySelector('.data');
let K_input = document.getElementsByClassName('K_chioce')[0].getElementsByTagName('input')[0];
let K_sure = document.getElementsByClassName('K_sure')[0];
let k_entry = document.querySelector('.K_entry');
let flag, num = 0;

K_xiala.onclick = function K_onclick() {
  if (num % 2 == 0) {
    K_xiala.classList.remove('K_up');
    K_xiala.className += ' K_down';
    flag = 1;
    num = 1;
  } else {
    K_xiala.classList.remove('K_down');
    K_xiala.className += ' K_up';
    K_ul.className += ' K_ulUp';
    setTimeout(() => {
      K_sure.style.display = 'flex';
    }, 1500);
    flag = 0;
    num = 0;
  }
  if (flag == 1) {
    K_ul.classList.remove('K_ulUp');
    K_ul.className += ' K_ulxiala';
    K_ul.style.display = 'block';
    K_sure.style.display = 'none';
    K_input.style.display = 'none';
    for (let k = 0; k < K_li.length; k++) {
      K_li[k].onclick = function () {
        K_input.value = this.innerText;
        K_input.style.color = "#fff"
        K_xiala.classList.remove('K_down');
        K_xiala.className += ' K_up';
        K_input.style.display = 'block';
        K_ul.style.display = 'none';
        K_sure.style.display = 'flex';
        num = 0;
      }
    }
    flag = 0;
  } else {
    setTimeout(() => {
      K_ul.style.display = 'none';
      K_input.style.display = 'block';
    }, 1500);
    K_ul.classList.remove('K_ulxiala');
    flag = 1;
  }
}

var s_name = document.getElementById('name');
var s_xuehao = document.getElementById('xuehao');
var s_dianhua = document.getElementById('dianhua');
var K_re1 = /^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(16[5,6])|(17[0-8])|(18[0-9])|(19[1、5、8、9]))\d{8}$/;
var flag_deng =1;
K_sure.onclick = function () {
  if(s_name.value==""){
    s_name.value="请正确输入姓名！";
    s_name.style.color='red';
  }
  if((s_xuehao.value=="") || (s_xuehao.value.length!=8)){
    s_xuehao.value="请正确输入学号！";
    s_xuehao.style.color='red';
  }
  if((K_input.value=="")){
    K_input.value="请选择方向";
    K_input.style.color='red';
  }
  if(!((s_dianhua.value).match(K_re1)) || (s_dianhua.value.length!=11)){
    s_dianhua.value="请正确输入电话！";
    s_dianhua.style.color='red';
  }
  if((s_name.value!="") && (s_xuehao.value.length==8) && (K_input.value!="") && ((s_dianhua.value).match(K_re1)) && (s_dianhua.value.length==11) && 
  (K_input.value!="请选择方向") && (s_dianhua.value!="请正确输入电话！") &&  (s_xuehao.value!="请正确输入学号！") && (s_name.value!="请正确输入姓名！")){
    if(flag_deng == 1){
      alert("请确保号码与前面一致！");
      flag_deng=0;
    }
    else{
    k_entry.innerHTML='loading...';
    k_entry.style.color='rgba(255, 0, 0, 0.536)';
    var jilu=null;
    if( K_input.value == '前端'){
      jilu=1;
    }
    if( K_input.value == '后台'){
      jilu=2;
    }
    if( K_input.value == '安全'){
      jilu=3;
    }
    if( K_input.value == '设计'){
      jilu=4;
    }

    let useritem ={
      username : s_name.value,
      userNumber : s_xuehao.value,
      phone : s_dianhua.value,
      direction: jilu
    }

    const user_xhr = new XMLHttpRequest();

    user_xhr.open('POST', "http://124.221.253.24:9081/User/save", true);

    user_xhr.setRequestHeader('Content-Type','application/json;charset=UTF-8');

    user_xhr.send(JSON.stringify(useritem));
    console.log(JSON.stringify(useritem));
    
    user_xhr.onreadystatechange = function () {
      if (user_xhr.readyState == 4 && user_xhr.status >= 200 && user_xhr.status < 300) {
        
        let str = JSON.parse(user_xhr.responseText);

        window.location.href = "../jinruye/xinxishangchuan.html";
       }
    }
  }
  }
}

s_name.onfocus = function() {
  s_name.style.color='#fff';
  s_name.value="";
}
s_xuehao.onfocus = function() {
  s_xuehao.style.color='#fff';
  s_xuehao.value="";
}
s_dianhua.onfocus = function() {
  s_dianhua.style.color='#fff';
  s_dianhua.value="";
}
