document.addEventListener('DOMContentLoaded',function(){
    var back = document.querySelector('.return');
    var back1 = document.querySelector('.return1');
    var back2 = document.querySelector('.return2');
    var back3 = document.querySelector('.return3');
    var qianduan = document.querySelector('.qianduan');
    var houtai = document.querySelector('.houtai');
    var sheji = document.querySelector('.sheji');
    var anquan = document.querySelector('.anquan');
    var zhuye = document.querySelector('.zhuye');
    var dir = document.querySelector('.choose-container').querySelectorAll('img');

    //回到冒险
    var return_risk = document.querySelector('.return-risk');
    return_risk.onclick = function() {
        window.location.href='../baomingye/baomingye.html';
    }

    //页面效果
    dir[0].onclick  = function(){
        qianduan.style.display = 'block';
        zhuye.style.display = 'none';
    }
    dir[1].onclick = function(){
        houtai.style.display = 'block';
        zhuye.style.display = 'none';
    }
    dir[2].onclick = function(){
        sheji.style.display = 'block';
        zhuye.style.display = 'none';
    }
    dir[3].onclick  = function(){
        anquan.style.display = 'block';
        zhuye.style.display = 'none';
    }
    
    back.onclick = function(){
        back.children[1].className='wave a-1';
        back.children[2].className='wave a-2';
        back.children[0].style.display='none';
        setTimeout(function(){
            zhuye.style.display='block';
            back.children[0].style.display='block';
            qianduan.style.display='none';
            houtai.style.display='none';
            sheji.style.display='none';
            anquan.style.display='none';
            back.children[1].className='';
            back.children[2].className='';
        },1000)
    }

    back1.onclick = function(){
        back1.children[1].className='wave a-1';
        back1.children[2].className='wave a-2';
        back1.children[0].style.display='none';
        setTimeout(function(){
            zhuye.style.display='block';
            back1.children[0].style.display='block';
            qianduan.style.display='none';
            houtai.style.display='none';
            sheji.style.display='none';
            anquan.style.display='none';
            back1.children[1].className='';
            back1.children[2].className='';
        },1000)
    }

    back2.onclick = function(){
        back2.children[1].className='wave a-1';
        back2.children[2].className='wave a-2';
        back2.children[0].style.display='none';
        setTimeout(function(){
            zhuye.style.display='block';
            back2.children[0].style.display='block';
            qianduan.style.display='none';
            houtai.style.display='none';
            sheji.style.display='none';
            anquan.style.display='none';
            back2.children[1].className='';
            back2.children[2].className='';
        },1000)
    }

    back3.onclick = function(){
        back3.children[1].className='wave a-1';
        back3.children[2].className='wave a-2';
        back3.children[0].style.display='none';
        setTimeout(function(){
            zhuye.style.display='block';
            back3.children[0].style.display='block';
            qianduan.style.display='none';
            houtai.style.display='none';
            sheji.style.display='none';
            anquan.style.display='none';
            back3.children[1].className='';
            back3.children[2].className='';
        },1000)
    }
}) 
