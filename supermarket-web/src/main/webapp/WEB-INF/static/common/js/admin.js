
$(function () {
    var modal = $('#DataModal', window.parent.document);
    var tabNav = $("#myTab>ul:eq(0)");
    var tabContent = $("#myTab>div:eq(0)");
    var timeout = null;
    var name = 'curmenu';
    $(".nav-pills>li").mouseenter(function () {
        $(this).addClass("active").siblings().removeClass("active");
    });
    
    /*$("body").on("mouseenter",".item",function () {
        var _this = $(this);
        timeout = setTimeout(function () {
            if (!_this.hasClass(name)) {
                var ul = _this.siblings('.' + name).children("ul");
                _this.addClass(name).children("ul").slideDown();
                ul.slideUp().parent().removeClass(name);
            }
        }, 150);
    }).on("mouseleave",".item",function () {
        clearTimeout(timeout);
        });*/
    $("body").on("click",".item",function () {
        var _this = $(this);
            if (!_this.hasClass(name)) {
                var ul = _this.siblings('.' + name).children("ul");
                _this.addClass(name).children("ul").slideDown();
                ul.slideUp().parent().removeClass(name);
            }else{
                var ul = _this.siblings('.' + name).children("ul");
                _this.removeClass(name).children("ul").slideUp();
                ul.slideDown().parent().addClass(name);
            }
    })
    
    $("body").on("click", ".tabnav", function () {
        var item = $(this);
        var title = item.html();
        var MenuId = item.attr("MenuId");
        var url =transUrl('/'+item.attr("href"));
        var index = tabNav.children("li.tab" + MenuId).index();
        var activeli = tabNav.children("li.active");
        var activediv = tabContent.children("div.active");
        if (index==-1) {
            var sumWidth = 0;
            tabNav.children().each(function () {
                sumWidth += $(this).width();
            })
            if (tabNav.width() < sumWidth+140) {
                alert("窗口数量达到最大，请关闭部分窗口后重新打开新窗口");
            } else {
                var pagetitle = '<li url="'+url+'" class="active tab' + MenuId + '"><a href="#tab' + MenuId + '" data-toggle="tab"><span>' + title + '</span><strong class="glyphicon glyphicon-remove"></strong></a></li>';
                var page = '<div class="tab-pane fade active in" id="tab' + MenuId + '"><iframe style=" min-height:' + tabContent.children("div").height()+'px;width:100%;" scrolling="auto" frameborder="0" src="' + url+ '"></iframe></div>';
                activeli.removeClass("active");
                activediv.removeClass("active in");
                tabNav.append(pagetitle);
                tabContent.append(page);
            }
        } else {
            activediv.removeClass("active in");
            activeli.removeClass("active");
            tabNav.children().eq(index).addClass("active");
            tabContent.children().eq(index).addClass("active in");
        }
        return false;
    });

    tabNav.on("click", ".glyphicon-remove", function () {
        var _this = $(this).parent().parent();
        var num = tabNav.children().length;
        var index = _this.index();
        var tabdiv = tabContent.children().eq(index);
        if (_this.hasClass("active")) {
            if (index == num - 1) {
                _this.prev().addClass("active");
                tabdiv.prev().addClass("active in");
            } else {
                _this.next().addClass("active");
                tabdiv.next().addClass("active in");
            }
        }
        _this.remove();
        tabdiv.remove();
    });

    tabNav.on("contextmenu", "li", function (event) {
        var url = $(this).attr('url');
        var index = $(this).index();
        var _this = $(this);
        if (index > 0) {
            var rightmenu = ([
                '<div id="rightmenu">',
                '<ul class="list-group">',
                '<li class="list-group-item refersh"><b class="glyphicon glyphicon-refresh"></b>刷新</li>',
                '<li class="list-group-item close-cur"><b class="glyphicon glyphicon-remove"></b>关闭标签</li>',
                '<li class="list-group-item close-others"><b class="glyphicon glyphicon-remove-circle"></b>关闭其他标签</li>',
                '<li class="list-group-item close-left"><b class="glyphicon glyphicon-arrow-left"></b>关闭左侧标签</li>',
                '<li class="list-group-item close-right"><b class="glyphicon glyphicon-arrow-right"></b>关闭右侧标签</li>',
                '</ul>',
                '</div>'
            ].join(''));
            $("body").append(rightmenu);
            var menu = $("#rightmenu");
            menu.css("left", event.pageX).css("top", event.pageY);
            menu.on("click", "li", function () {
                var firstli = tabNav.children().eq(0);
                var firstdiv = tabContent.children().eq(0);
                if ($(this).hasClass("refersh")) {
                    var iframe = tabContent.children().eq(index).children("iframe");
                    iframe.attr("src", url);
                } else if ($(this).hasClass("close-cur")) {
                    var num = tabNav.children().length;
                    var tabdiv = tabContent.children().eq(index);
                    if (_this.hasClass("active")) {
                        if (index == num - 1) {
                            _this.prev().addClass("active");
                            tabdiv.prev().addClass("active in");
                        } else {
                            _this.next().addClass("active");
                            tabdiv.next().addClass("active in");
                        }
                    }
                    _this.remove();
                    tabdiv.remove();
                } else if ($(this).hasClass("close-others")) {
                    _this.siblings().not(firstli).remove();
                    tabContent.children().eq(index).siblings().not(firstdiv).remove();
                } else if ($(this).hasClass("close-left")) {
                    _this.prevAll().not(firstli).remove();
                    tabContent.children().eq(index).prevAll().not(firstdiv).remove();
                } else if ($(this).hasClass("close-right")) {
                    _this.nextAll().not(firstli).remove();
                    tabContent.children().eq(index).nextAll().not(firstdiv).remove();
                }
                menu.remove();
                
            });
            menu.mouseleave(function(){
            	this.remove();
            });
        }
        return false;

    })
    
})
