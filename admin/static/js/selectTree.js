/**
 * Created by zy8 on 2017/2/23.
 */


$.fn.ComboBox = function (options) {
    //options参数：description,height,width,allowSearch,url,param,data
    var $select = $(this);
    if (!$select.attr("id")) {
        return false;
    }
    if (options) {
        if ($select.find(".ui-select-text").length == 0) {
            var $select_html = "";
            $select_html += "<div class=\"ui-select-text\" style='color:#999;'>" + options.description + "</div>";
            $select_html += "<div class=\"ui-select-option\">";
            $select_html += "<div class=\"ui-select-option-content\" style=\"max-height: " + options.height + "\">" + $select.html() + "</div>";
            if (options.allowSearch) {
                $select_html += "<div class=\"ui-select-option-search\"><input type=\"text\" class=\"form-control\" placeholder=\"搜索关键字\" /><span class=\"input-query\" title=\"Search\"><i class=\"fa fa-search\"></i></span></div>";
            }
            $select_html += "</div>";
            $select.html("");
            $select.append($select_html);
        }
    }
    var $option_html = $($("<p>").append($select.find(".ui-select-option").clone()).html());
    $option_html.attr("id", $select.attr("id") + "-option");
    $select.find(".ui-select-option").remove();
    if ($option_html.length > 0) {
        $("body").find("#" + $select.attr("id") + "-option").remove();
    }
    $("body").prepend($option_html);
    var $option = $("#" + $select.attr("id") + "-option");
    if (options.url != undefined) {
        $option.find(".ui-select-option-content").html("");
        $.ajax({
            url: options.url,
            data: options.param,
            type: "GET",
            dataType: "json",
            async: false,
            success: function (data) {
                options.data = data;
                var json = data;
                loadComboBoxView(json);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.fn.modalMsg(errorThrown, "warning");
            }
        });
    } else if (options.data != undefined) {
        var json = options.data;
        loadComboBoxView(json);
    } else {
        $option.find("li").css("padding", "0 5px");
        $option.find("li").click(function (e) {
            var data_text = $(this).text();
            var data_value = $(this).attr("data-value");
            $select.attr("data-value", data_value).attr("data-text", data_text);
            $select.find(".ui-select-text").html(data_text).css("color", "#000");
            $option.slideUp(150);
            $select.trigger("change");
            e.stopPropagation();
        }).hover(function (e) {
            if (!$(this).hasClass("liactive")) {
                $(this).toggleClass("on");
            }
            e.stopPropagation();
        });
    }

    function loadComboBoxView(json, searchValue, m) {
        if (json.length > 0) {
            var $_html = $("<ul></ul>");
            if (options.description) {
                $_html.append("<li data-value=\"\">" + options.description + "</li>");
            }
            $.each(json, function (i) {
                var row = json[i];
                var title = row[options.title];
                if (title == undefined) {
                    title = "";
                }
                if (searchValue != undefined) {
                    if (row[m.text].indexOf(searchValue) != -1) {
                        $_html.append("<li data-value=\"" + row[options.id] + "\" title=\"" + title + "\">" + row[options.text] + "</li>");
                    }
                } else {
                    $_html.append("<li data-value=\"" + row[options.id] + "\" title=\"" + title + "\">" + row[options.text] + "</li>");
                }
            });
            $option.find(".ui-select-option-content").html($_html);
            $option.find("li").css("padding", "0 5px");
            $option.find("li").click(function (e) {
                var data_text = $(this).text();
                var data_value = $(this).attr("data-value");
                $select.attr("data-value", data_value).attr("data-text", data_text);
                $select.find(".ui-select-text").html(data_text).css("color", "#000");
                $option.slideUp(150);
                $select.trigger("change");
                e.stopPropagation();
            }).hover(function (e) {
                if (!$(this).hasClass("liactive")) {
                    $(this).toggleClass("on");
                }
                e.stopPropagation();
            });
        }
    }

    //操作搜索事件
    if (options.allowSearch) {
        $option.find(".ui-select-option-search").find("input").bind("keypress", function (e) {
            if (event.keyCode == "13") {
                var value = $(this).val();
                loadComboBoxView($(this)[0].options.data, value, $(this)[0].options);
            }
        }).focus(function () {
            $(this).select();
        })[0]["options"] = options;
    }

    $select.unbind("click");
    $select.bind("click", function (e) {
        if ($select.attr("readonly") == "readonly" || $select.attr("disabled") == "disabled") {
            return false;
        }
        $(this).addClass("ui-select-focus");
        if ($option.is(":hidden")) {
            $select.find(".ui-select-option").hide();
            $(".ui-select-option").hide();
            var left = $select.offset().left;
            var top = $select.offset().top + 29;
            var width = $select.width();
            if (options.width) {
                width = options.width;
            }
            if (($option.height() + top) < $(window).height()) {
                $option.slideDown(150).css({ top: top, left: left, width: width });
            } else {
                var _top = (top - $option.height() - 32);
                $option.show().css({ top: _top, left: left, width: width });
                $option.attr("data-show", true);
            }
            $option.css("border-top", "1px solid #ccc");
            $option.find("li").removeClass("liactive");
            $option.find("[data-value=" + $select.attr("data-value") + "]").addClass("liactive");
            $option.find(".ui-select-option-search").find("input").select();
        } else {
            if ($option.attr("data-show")) {
                $option.hide();
            } else {
                $option.slideUp(150);
            }
        }
        e.stopPropagation();
    });
    $(document).click(function (e) {
        var e = e ? e : window.event;
        var tar = e.srcElement || e.target;
        if (!$(tar).hasClass("form-control")) {
            if ($option.attr("data-show")) {
                $option.hide();
            } else {
                $option.slideUp(150);
            }
            $select.removeClass("ui-select-focus");
            e.stopPropagation();
        }
    });
    return $select;
};
$.fn.ComboBoxSetValue = function (value) {
    if ($.isNullOrEmpty(value)) {
        return;
    }
    var $select = $(this);
    var $option = $("#" + $select.attr("id") + "-option");
    $select.attr("data-value", value);
    var data_text = $option.find("ul").find("[data-value=" + value + "]").html();
    if (data_text) {
        $select.attr("data-text", data_text);
        $select.find(".ui-select-text").html(data_text).css("color", "#000");
        $option.find("ul").find("[data-value=" + value + "]").addClass("liactive");
    }
    return $select;
};

changeUrlParam = function (url, key, value) {
    var newUrl = "";
    var reg = new RegExp("(^|)" + key + "=([^&]*)(|$)");
    var tmp = key + "=" + value;
    if (url.match(reg) != null) {
        newUrl = url.replace(eval(reg), tmp);
    } else {
        if (url.match("[\?]")) {
            newUrl = url + "&" + tmp;
        } else {
            newUrl = url + "?" + tmp;
        }
    }
    return newUrl;
};
