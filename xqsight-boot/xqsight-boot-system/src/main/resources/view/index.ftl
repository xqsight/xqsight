<#include "common/head.html"/>
<link rel="stylesheet" type="text/css" href="${domain}/static/css/index.css">
<#include "common/navbar.html"/>

<div class="page-index">
    <div class="slider" id="slider">
        <ul class="slider-list clearfix" style="left: 0;">
            <#list cmsAds as cmsAd>
                <li style="background-image: url(${cmsAd.adImage});">
                    <div class="slider-text">
                        <h2>${cmsAd.adName}</h2>
                        <p>${cmsAd.adText}</p>
                        <a href="${cmsAd.adUrl}" class="btn">查看详情</a>
                    </div>
                </li>
            </#list>
        </ul>
        <ol class="slider-num clearfix">
            <li class="current"></li>
            <li></li>
            <li></li>
        </ol>
    </div>

    <script type="text/javascript">
        window.onload = function (){
            var oSlider = document.getElementById("slider");
            var oUl = oSlider.getElementsByTagName("ul")[0];
            var aLi = oUl.getElementsByTagName("li");
            var aBtn = oSlider.getElementsByTagName("ol")[0].getElementsByTagName("li");
            var w = -aLi[0].offsetWidth;
            var current = 0;
            var timer = null;

            //切换按钮
            for(var i = 0; i < aBtn.length; i++){
                aBtn[i].index = i;
                aBtn[i].onmousemove = function(){
                    animate(this.index);
                }
            }

            //鼠标移入图片静止
            oSlider.onmouseon = function(){
                clearInterval(timer);
            }

            //鼠标移出图片滚动
            oSlider.onmousemover = function(){
                autoPlay();
            }

            //自动循环播放
            function autoPlay(time){
                time = time || 2000;
                if(timer) clearInterval(timer)
                timer = setInterval(function(){
                    current += 1;
                    if(current >= aLi.length) current = 0;
                    animate(current);
                },time)
            }

            //图片按钮同时滚动
            function animate(index){
                //图片滚动
                oUl.style.left = w * index + "px";
                //按钮滚动
                for(var i = 0; i < aBtn.length; i++) aBtn[i].className = "";
                aBtn[index].className = "current";
            }

            autoPlay();
        }

    </script>


    <ul class="list01 clearfix">
    <#list articles as article>
        <li>
            <a href="${article.articleUrl}" class="list01-img" target="_blank"><img src="${article.articleImg}" alt=""></a>
            <h2 class="ellipsis"><a href="${article.articleUrl}" target="_blank">${article.articleTitle}</a></h2>
            <p class="ellipsis"><a href="${article.articleUrl}" target="_blank">${article.articleDesp}</a></p>
            <div class="list01-editer">
                <span class="text-light">>${article.department}</span> | <span>${article.articleAuthor}</span>
            </div>
            <div class="list01-tack">
                <div class="list01-tack01">
                    <#if article.tags??>
                        <#list article.tags as tag>
                            <span class="i-tag">${tag}</span>
                        </#list>
                    </#if>
                </div>
            </div>
        </li>
    </#list>
    </ul>

    <div class="partner">
        <h2>合作伙伴</h2>
        <ul class="clearfix">
            <li><a href=""><img src="http://placehold.it/162x100" alt=""></a></li>
            <li><a href=""><img src="http://placehold.it/162x100" alt=""></a></li>
            <li><a href=""><img src="http://placehold.it/162x100" alt=""></a></li>
            <li><a href=""><img src="http://placehold.it/162x100" alt=""></a></li>
            <li><a href=""><img src="http://placehold.it/162x100" alt=""></a></li>
            <li><a href=""><img src="http://placehold.it/162x100" alt=""></a></li>
            <li><a href=""><img src="http://placehold.it/162x100" alt=""></a></li>
        </ul>
    </div>
</div>

<#include "common/foot.html"/>