//结果完成页面
const jgstate = document.getElementsByClassName('jg-state');
const wd = document.getElementsByClassName('w-d');
const jgline = document.querySelector('.jg-line');
const weitongguo = document.querySelector('.weitongguo');

function w_statechange(res) 
{
    // if (res.state == '0') {    //未报名
    //     jgstate[0].classList.add('w-renovate');
    //     for (let i = 1; i <= 4; i++) {
    //         wd[i].style.display = 'none';
    //     }
    //     jgline.style.height = '0';
    // }

    if (res.state == '0') {  //已报名
        jgstate[0].classList.add('w-confirm');
        for (let i = 1; i <= 4; i++) {
            wd[i].style.display = 'none';
        }
        jgline.style.height = '0';
    }

    // if (res.state == '2') {  // 等待一面
    //     for (let i = 0; i < 1; i++) {
    //         jgstate[i].classList.add('w-confirm');
    //     }
    //     jgstate[1].classList.add('w-renovate');
    //     for (let i = 2; i <= 4; i++) {
    //         wd[i].style.display = 'none';
    //     }
    //     jgline.style.height = '18%';
    // }
 
    if (res.state == '1') {  //一面通过
        for (let i = 0; i < 2; i++) {
            jgstate[i].classList.add('w-confirm');
        }
        for (let i = 2; i <= 4; i++) {
            wd[i].style.display = 'none';
        }
        jgline.style.height = '18%';
    }

    // if (res.state == '4') {  //等待二面
    //     for (let i = 0; i < 2; i++) {
    //         jgstate[i].classList.add('w-confirm');
    //     }
    //     jgstate[2].classList.add('w-renovate');
    //     for (let i = 3; i <= 4; i++) {
    //         wd[i].style.display = 'none';
    //     }
    //     jgline.style.height = '37%';
    // }

    if (res.state == '2') {  //二面通过
        for (let i = 0; i < 3; i++) {
            jgstate[i].classList.add('w-confirm');
        }
        for (let i = 3; i <= 4; i++) {
            wd[i].style.display = 'none';
        }
        jgline.style.height = '37%';
    }

    // if (res.state == '6') {  //等待三面
    //     for (let i = 0; i < 3; i++) {
    //         jgstate[i].classList.add('w-confirm');
    //     }
    //     jgstate[3].classList.add('w-renovate');
    //     for (let i = 4; i <= 4; i++) {
    //         wd[i].style.display = 'none';
    //     }
    //     jgline.style.height = '59%';
    // }

    if (res.state == '3') {  //三面通过
        for (let i = 0; i < 4; i++) {
            jgstate[i].classList.add('w-confirm');
        }
        for (let i = 4; i <= 4; i++) {
            wd[i].style.display = 'none';
        }
        jgline.style.height = '59%';
    }

    if (res.state == '4') {  //进入成功
        for (let i = 0; i <= 4; i++) {
            jgstate[i].classList.add('w-confirm');
        }
        jgline.style.height = '80%';
    }

    if (res.state == '-1') {  //一面未过
        for (let i = 0; i < 3; i++) {
            jgstate[i].classList.add('w-confirm');
        }
        jgstate[1].classList.add('w-error');
        for (let i = 2; i <= 4; i++) {
            wd[i].style.display = 'none';
        }
        jgline.style.height = '18%';
        setTimeout(() => {
            jieguo.style.display='none';
            weitongguo.style.display='block';
        },5000);
    }
    if (res.state == '-2') {  //二面未过
        for (let i = 0; i < 3; i++) {
            jgstate[i].classList.add('w-confirm');
        }
        jgstate[2].classList.add('w-error');
        for (let i = 3; i <= 4; i++) {
            wd[i].style.display = 'none';
        }
        jgline.style.height = '37%';
        setTimeout(() => {
            jieguo.style.display='none';
            weitongguo.style.display='block';
        },5000);
    } 
    if (res.state == '-3') { //三面未过
        for (let i = 0; i < 4; i++) {
            jgstate[i].classList.add('w-confirm');
        }
        jgstate[3].classList.add('w-error');
        for (let i = 4; i <= 4; i++) {
            wd[i].style.display = 'none';
        }
        jgline.style.height = '59%';
        setTimeout(() => {
            jieguo.style.display='none';
            weitongguo.style.display='block';
        },5000);
    }
}


//结果点击页面
const jieguolan = document.querySelector('.jieguolan');
const dianjichakan = document.querySelector('.dianjichakan');
const jieguo = document.querySelector('.jieguo');
var user ={
    state:0
}

jieguolan.ontouchstart = function() {
    const user_xhr = new XMLHttpRequest();

    user_xhr.open('get', "http://124.221.253.24:9081/User/getState");

    user_xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

    user_xhr.send();

    user_xhr.onreadystatechange = function () {
    
        if (user_xhr.readyState == 4 && user_xhr.status >= 200 && user_xhr.status < 300) {
        var houtai_temp = JSON.parse(user_xhr.responseText);
        console.log(houtai_temp.data);

        if(houtai_temp.data == ""){
            user.state = 0;
            w_statechange(user);
        }

        else{
            user.state = houtai_temp.data;
            console.log(user.state);
            w_statechange(user);
        }

        setTimeout(() => {
            jieguo.style.display = 'block';
            dianjichakan.style.display='none';
        }, 200); 
      }
    }  
}
