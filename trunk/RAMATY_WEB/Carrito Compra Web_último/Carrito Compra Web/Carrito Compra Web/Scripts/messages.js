/* Función generica: NO USAR */
function clearDialog() {
    $("#dialog-message").dialog("destroy");
    $("#dialog-message").html("");
}

/* Función generica: NO USAR */
function showDialog(title, modal) {
    $("#dialog-message").attr("title", title);
    $("#dialog-message").dialog({
        modal: modal,
        autoOpen: true,
        buttons: {
            Ok: function () {
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

function showAlert(title, message) {
    clearDialog();
    $("#dialog-message").html("<p><span class=\"ui-icon ui-icon-alert\" style=\"float:left; margin:0 7px 0px 0px;\"></span>" + message);
    showDialog(title, false);
}

function showError(title, message) {
    clearDialog();
    $("#dialog-message").html("<p><span class=\"ui-icon ui-icon-circle-close\" style=\"float:left; margin:0px 7px 0px 0px;\"></span>" + message);
    showDialog(title, true);
}

function showError2Button(title, message, functionAccept) {
    clearDialog();
    $("#dialog-message").html("<p><span class=\"ui-icon ui-icon-info\" style=\"float:left; margin:0px 7px 0px 0px;\"></span>" + message);
    
    $("#dialog-message").attr("title", title);
    $("#dialog-message").dialog({
        modal: true,
        autoOpen: true,
        buttons: {
            Aceptar: function () {
                functionAccept();
                $(this).dialog("close");
            },
            Cancelar: function () {
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

function showAceptAndCancel(title, message, functionAccept, functionCancel) {
    clearDialog();
    $("#dialog-message").html("<p><span class=\"ui-icon ui-icon-info\" style=\"float:left; margin:0px 7px 0px 0px;\"></span>" + message);

    $("#dialog-message").attr("title", title);
    $("#dialog-message").dialog({
        modal: true,
        autoOpen: true,
        buttons: {
            Aceptar: function () {
                functionAccept();
                $(this).dialog("close");
            },
            Cancelar: function () {
                functionCancel();
                $(this).dialog("close");
            }
        },
        close: function () {
            
        }
    });
}

function showMessage(title, message) {
    clearDialog();
    $("#dialog-message").attr("title", title);
    $("#dialog-message").html(message);
    $("#dialog-message").dialog({
        modal: true,
        autoOpen: true,
        buttons: {
            Ok: function () {
                $(this).dialog("close");
            }
        }
    });
}

function showDialogCarrito(title, content, modal, functionAccept, functionCancel) {
    $("#modal_carrito_compra").html(content);
    $("#modal_carrito_compra").attr("title", title);
    $("#modal_carrito_compra").dialog({
        modal: modal,
        autoOpen: true,
        width: 500,
        resizable: false,
        height: 500,
        buttons: {
            "Confirmar": function () {
                functionAccept();
            },
            "Cancelar": function () {
                functionCancel();
            },
            "Salir": function () {
                $(this).dialog("close");
            }
        },
        close: function () {

        }
    });
}