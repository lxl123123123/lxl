var M_con=document.getElementsByClassName("M_con")[0]
        var M_heart=document.getElementsByClassName("M_heart")[0]
        var M_bird=document.getElementsByClassName("M_bird")[0]
        var M_cloud=document.getElementsByClassName("M_cloud")[0]
        var M_cloud_text=document.getElementsByClassName("M_cloud_text")[0]
        var M_text=document.getElementsByClassName("M_text")[0]
        var M_sword=document.getElementsByClassName("M_sword")[0]
        var M_human=document.getElementsByClassName("M_human")[0]
        var M_tree=document.getElementsByClassName("M_tree")[0]
        var M_cat=document.getElementsByClassName("M_cat")[0]
        var M_another_cloud=document.getElementsByClassName("M_another_cloud")[0]
        var M_another_innerText=document.getElementById("M_another_innerText")
        var M_introduce=document.getElementsByClassName("M_introduce")[0]
        var M_sign_up=document.getElementsByClassName("M_sign_up")[0]
        var M_block1=document.getElementsByClassName("block1")[0]
        var M_block2=document.getElementsByClassName("block2")[0]
        var M_flower=document.getElementsByClassName("M_flower")[0]
        var M_congratulation1=document.getElementsByClassName("M_congratulation1")[0]
        var M_congratulation2=document.getElementsByClassName("M_congratulation2")[0]
        var M_cup=document.getElementsByClassName("M_cup")[0]
        M_heart.onclick=function(){
            this.style.backgroundImage="url(./heart_red.png)"
            fade(M_cloud_text,20)
            setTimeout(()=>{
                M_cloud_text.style.opacity=1
                M_cloud_text.innerHTML="召唤成功！"
                setTimeout(()=>{
                    var tem_str="288% 163%"//////////////////////////////////////这里声明一个全局变量
                    tem_str=Number(tem_str.split(" ")[0].slice(0,3))
                    let timer=setInterval(()=>{
                    if(tem_str>=175){
                        tem_str-=2;
                        M_con.style.backgroundSize=tem_str+"% "+"163%"
                    }else{
                        clearInterval(timer)
                        tem_str=100
                        setTimeout(()=>{
                            let timer2=setInterval(()=>{
                                if(tem_str>0){
                                    tem_str-=2
                                    M_con.style.backgroundPosition=tem_str+"% "+"100%"
                                }else{
                                    clearInterval(timer2)
                                    M_con.style.backgroundPosition="0% 100%";
                                    unfade(M_text,20)
                                    setTimeout(()=>{
                                        M_con.style.backgroundImage="url(./bgc2.png)"
                                        M_con.style.backgroundSize="100% 100%"
                                        M_con.style.backgroundRepeat="no-repeat"
                                        M_bird.style.display="block"
                                        M_bird.style.top="48.5%"
                                        M_bird.style.opacity="1"
                                        M_cloud.style.display="block"
                                        M_cloud.style.top="35%"
                                        M_cloud.style.opacity="1"
                                        console.log(M_cloud_text)
                                        console.log(M_cloud_text.style.display)
                                        M_cloud_text.style.display="block"
                                        M_cloud_text.innerHTML="点击武器召唤冒险家吧"
                                        console.log(M_cloud_text.style.display)
                                        M_heart.style.display="none"
                                        M_text.style.display="none"
                                        M_sword.style.display="block"
                                    },1000)
                                }
                            },40)
                        },1000)
                    }
                },20)
                fade(M_heart,20)
                fade(M_bird,20)
                fade(M_cloud,20)
                },1000)
            },1000)
            this.onclick=null;
        }
        M_sword.onclick=function(){
            fade(M_sword,20)//剑消失
            fade(M_cloud_text)
            setTimeout(()=>{
                // M_cloud_text.style.opacity=1
                M_cloud_text.innerHTML="召唤成功"
                unfade(M_cloud_text,20)
                setTimeout(()=>{
                    fade(M_cloud,20)
                    fade(M_bird,20)
                    M_human.style.display="block"
                    M_sword.style.width="9%"
                    M_sword.style.height="4.1%"
                    M_sword.style.display="block"
                    M_sword.style.zIndex="1"
                    M_sword.style.top="79%";
                    M_sword.style.left="23%"
                    unfade(M_sword,20);
                    setTimeout(()=>{
                        fade(M_cloud_text,20)
                        unfade(M_cloud_text,20)
                        M_cat.style.display="block";
                        M_another_cloud.style.display="block"
                        M_another_innerText.innerHTML="请选择您的冒险奖励"
                        unfade(M_another_cloud,20)
                        unfade(M_cat,20)
                        setTimeout(()=>{
                            fade(M_cat,20)
                            fade(M_another_cloud,20)
                            fade(M_another_innerText,20)
                            setTimeout(()=>{
                                M_con.style.filter="blur(10px)"
                                M_sign_up.style.display="block"
                                M_introduce.style.display="block"
                            },1000)
                        },1000) 
                    },1000)//文字消失
                },1000)
            },1000)
            this.onclick = null;
        }
        M_introduce.onclick=function intro(){
            M_con.style.filter="none"
            M_introduce.style.display="none"
            M_sign_up.style.display="none"
            M_tree.style.display="block"
            M_sword.style.display="none"
            M_block1.style.display="block"
            jump_human(9,82,M_human)
            setTimeout(()=>{
                M_human.style.transition="all 2s"
                jump_human(40,43,M_human)
                M_block2.style.display="block"
                M_flower.style.top="30.5%"
                M_flower.style.left="72%"
                M_flower.style.position="absolute"
                M_flower.style.display="block"
                setTimeout(()=>{
                    jump_human(71,27,M_human)
                    M_flower.style.width="7%"
                    M_flower.style.height="3.8%"
                    M_flower.style.top="25%"
                    M_flower.style.left="82%"
                    setTimeout(()=>{///恭喜你冒险成功部分
                        M_con.style.filter="blur(10px)"
                        M_congratulation1.style.display="block"
                        M_congratulation2.style.display="block"
                        setTimeout(()=>{
                            M_congratulation1.style.display="none"
                            M_congratulation2.innerHTML="即将进入部门介绍"
                            M_congratulation2.style.opacity=0;
                            M_congratulation2.style.transition="all 1s"
                            M_congratulation2.style.opacity=1
                            window.location.href="../bumenxuanze/bumenchakan.html";
                            ////////链接到部门介绍部分-----------------------------------------------------------------
                        },2000)
                    },2500)
                },2000)
            },1000)
            this.onclick = null;
        }
        M_sign_up.onclick=function(){
            M_con.style.filter="none"
            M_introduce.style.display="none"
            M_sign_up.style.display="none"
            M_tree.style.display="block"
            M_sword.style.display="none"
            M_block1.style.display="block"
            jump_human(9,82,M_human)
            setTimeout(()=>{
                M_human.style.transition="all 1.2s linear"
                jump_human(28,33,M_human)
                setTimeout(() => {
                    M_human.style.transition="all 0.4s"
                    jump_human(40,43,M_human)
                    M_block2.style.display="block"
                    M_cup.style.top="30.5%"
                    M_cup.style.left="72%"
                    M_cup.style.position="absolute"
                    M_cup.style.display="block"
                    setTimeout(()=>{
                        M_human.style.transition="all 1.2s linear"
                        jump_human(65,21,M_human)
                        M_cup.style.width="7%"
                        M_cup.style.height="3.8%"
                        M_cup.style.top="25%"
                        M_cup.style.left="82%"
                        setTimeout(()=>{
                            M_human.style.transition="all 0.4s linear"
                            jump_human(71,27,M_human)
                            setTimeout(()=>{///恭喜你冒险成功部分
                                M_con.style.filter="blur(10px)"
                                M_congratulation1.style.display="block"
                                M_congratulation2.style.display="block"
                                setTimeout(()=>{
                                    M_congratulation1.style.display="none"
                                    M_congratulation2.innerHTML="即将进入报名页面"
                                    M_congratulation2.style.opacity=0;
                                    M_congratulation2.style.transition="all 1s"
                                    M_congratulation2.style.opacity=1
                                    window.location.href="../baomingye/baomingye.html";
                                    ////////链接到报名部分-----------------------------------------------------------------
                                },2000)
                            },2500)

                        },1000)
                    },2000)
                },1000 );
            },1000)
            this.onclick = null;
        }
        function fade(ele,time){
            ele.style.opacity="1"
            let timer=setInterval(()=>{
                num=Number(ele.style.opacity)
                ele.style.opacity-=0.05
                if(ele.style.opacity<=0){
                    ele.style.opacity=0;
                    clearInterval(timer)
                }   
            },time)
        }
        function unfade(ele,time){
            ele.style.opacity="0"
            let timer=setInterval(()=>{
                num=Number(ele.style.opacity)
                ele.style.opacity=num+0.05
                if(ele.style.opacity>=1){
                    ele.style.opacity=1;
                    clearInterval(timer)
                }
            },time)
        }
        function jump_human(pos_x,pos_y,ele){
            let init_x,init_y;
            init_x=ele.style.left.slice(0,-1)
            init_y=ele.style.top.slice(0,-1)
            ele.style.left=pos_x+"%"
            ele.style.top=pos_y+"%"
        }