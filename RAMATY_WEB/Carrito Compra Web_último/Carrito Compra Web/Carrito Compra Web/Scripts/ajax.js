function ajax(type, url, jsonData, functionSuccess,functionError) {
    var httpReq;

    //Si es Mozilla, Opera, etc...
    if (window.XMLHttpRequest) {
        ajaxNotIE(type, url, jsonData, functionSuccess, functionError);
    }
    else //Internet Explorer lo expone como control Active X
    {
        ajaxIE(type, url, jsonData, functionSuccess, functionError);
        
    }
}

function ajaxIE(type, url, jsonData, functionSuccess, functionError) {
    ajax_show_back();
    http = new ActiveXObject("Microsoft.XMLHTTP");
    http.onreadystatechange = function () {
        ajax_hide_back();
        switch (http.readyState) {
            case 4:
                var data = JSON.parse(http.responseText);
                /* Valido los códigos de error. */

                if (data.Status == 0) {
                    /* Todo bien */
                    functionSuccess(data.InnerObject);
                }
                else if (data.Status == 1) {
                    /* Error */
                    if (functionError == null || functionError == undefined) {
                        showError("Error", "<b>Se gener&oacute; un error:</b><br />" + data.Message);
                    } else {
                        functionError(data.Message);
                    }
                }
                else if (data.Status == 2) {
                    /* Redirect */
                    window.location = data.UrlRedirect;
                }
                break;
        }
    };
    http.open(type, url, true);
    http.setRequestHeader("Content-Type", "application/json");
    http.send(JSON.stringify(jsonData));
    return (true);
}

function ajaxNotIE(type, url, jsonData, functionSuccess, functionError) {
    ajax_show_back();
    jQuery.support.cors = true;
    $.ajax({
        type: type,
        url: url,
        async: false,
        data: JSON.stringify(jsonData),
        contentType: "application/json",
        dataType: "json",
        cache: false,
        crossDomain: true,
        success: function (retorno) {
            ajax_hide_back();
            if (retorno.Status == 0) {
                /* Todo bien */
                functionSuccess(retorno.InnerObject);
            }
            else if (retorno.Status == 1) {
                /* Error */
                //showError("Error", "<b>Se gener&oacute; un error:</b><br />" + retorno.Message);
                if (functionError == null || functionError == undefined) {
                    showError("Error", "<b>Se gener&oacute; un error:</b><br />" + retorno.Message);
                } else {
                    functionError(retorno.Message);
                }
            }
            else if (retorno.Status == 2) {
                /* Redirect */
                window.location = retorno.UrlRedirect;
            }
        },
        error: function (objeto, error, objeto2) {
            ajax_hide_back();
            showError("Error", "<b>Se gener&oacute; un error:</b><br />" + objeto.responseText);
        }
    });
}
function ajax_show_back() {
    $("#ajax_wait").width($(window).width());
    $("#ajax_wait").height($(window).height());
    $("#ajax_wait").css("z-index", "999");
    $("#img_ajax_wait").css("top", $(window).height() / 2 - 24);
    $("#img_ajax_wait").css("left", $(window).width() / 2 - 24);
    $("select").each(function () {
        $(this).hide();
    });
    $("#ajax_wait").show();
}

function ajax_hide_back() {
    $("select").each(function () {
        $(this).show();
    });
    $("#ajax_wait").hide();
}