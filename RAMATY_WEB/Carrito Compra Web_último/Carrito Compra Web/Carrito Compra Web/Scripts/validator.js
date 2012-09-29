function validate_null(class_component) {
    var bandera = true;
    $("." + class_component).each(function (index, value) {
        if ($(this).val() == "") {
            $(this).addClass("ui-state-error");
            $(this).attr("title", "Los valores son requeridos");
            bandera = false;
        }
        else {
            $(this).removeClass("ui-state-error");
            $(this).attr("title", "");
        }
    });
    return bandera;
}

/*VALIDACIONES NROS*/
function validate_number(class_component) {
    var bandera = true;
    $("." + class_component).each(function (index, value) {
        if ($.isNumeric($(this).val())) {
            $(this).removeClass("ui-state-error");
            $(this).attr("title", "");
        }
        else {
            $(this).addClass("ui-state-error");
            $(this).attr("title", "Los valores deben ser numéricos");
            bandera = false;
        }
    });
    return bandera;
}

function validate_number_between(class_component, valMin, valMax) {
    var bandera = true;
    $("." + class_component).each(function (index, value) {
        if ($.isNumeric($(this).val())) {
            if ($(this).val() >= valMin && $(this).val() <= valMax) {
                $(this).removeClass("ui-state-error");
                $(this).attr("title", "");
            }
            else {
                $(this).addClass("ui-state-error");
                $(this).attr("title", "Los valores deben estar comprendedidos entre " + valMin + " y " + valMax);
                bandera = false;
            }
        } else {
            $(this).addClass("ui-state-error");
            $(this).attr("title", "Los valores deben ser numericos");
            bandera = false;
        }
    });
    return bandera;
}

function validate_number_greater_than(class_component, val) {
    var bandera = true;
    $("." + class_component).each(function (index, value) {
        if ($.isNumeric($(this).val())) {
            if ($(this).val() > val) {
                $(this).removeClass("ui-state-error");
                $(this).attr("title", "");
            }
            else {
                $(this).addClass("ui-state-error");
                $(this).attr("title", "Los valores deben ser mayores a " + val);
                bandera = false;
            }
        }
        else {
            $(this).addClass("ui-state-error");
            $(this).attr("title", "Los valores deben ser numericos");
            bandera = false;
        }
    });
    return bandera;
}

function validate_number_less_than(class_component, val) {
    var bandera = true;
    $("." + class_component).each(function (index, value) {
        if ($.isNumeric($(this).val())) {
            if ($(this).val() < val) {
                $(this).removeClass("ui-state-error");
                $(this).attr("title", "");
            }
            else {
                $(this).addClass("ui-state-error");
                $(this).attr("title", "Los valores deben ser menores a " + val);
                bandera = false;
            }
        }
        else {
            $(this).addClass("ui-state-error");
            $(this).attr("title", "Los valores deben ser numericos");
            bandera = false;
        }
    });
    return bandera;
}
/*FIN VALIDACIONES NROS*/

/*VALIDACIONES FECHA */
function validate_date(class_component) {
    var bandera = true;
    $("." + class_component).each(function (index, value) {
        var value = new Date($(this).val());
        if (value == NaN || value == Invalid) {
            $(this).addClass("ui-state-error");
            $(this).attr("title", "Formato de fecha invalido");
            bandera = false;
        }
        else {
            var d = value.substr(0, 2);
            var m = value.substr(3, 2);
            var a = value.substr(6, 4);
            if ((m >= 12) || (d >= 31)) {
                $(this).addClass("ui-state-error");
                $(this).attr("title", "Formato de fecha invalido");
                bandera = false;
            }
            else {
                if ((a % 4 != 0) && (m == 2) && (d > 28)) {
                    /* El año no es bisiesto */
                    $(this).addClass("ui-state-error");
                    $(this).attr("title", "Formato de fecha invalido");
                    bandera = false;
                }
                else {
                    if ((((m == 4) || (m == 6) || (m == 9) || (m == 11)) && (d > 30)) || ((m == 2) && (d > 29))) {
                        $(this).addClass("ui-state-error");
                        $(this).attr("title", "Formato de fecha invalido");
                        bandera = false;
                    }
                    else {
                        $(this).removeClass("ui-state-error");
                        $(this).attr("title", "");
                        bandera = true;
                    }
                }
            }
        }
    });
    return bandera;
}

function is_null(val) {
    if (val == "") {
		return true;
    }
	return false;
}

/*VALIDACIONES NROS*/
function is_number(val) {
    if ($.isNumeric(val)) {
		return true;
    }
    return false;
}

function is_number_between(val, valMin, valMax) {
    if (is_number(val) && is_number(valMin) && is_number(valMax)) {
		if (val >= valMin && val <= valMax) {
			return true;
		}
	}
    return false;
}

function is_number_greater_than(val, valReference) {
    if (is_number(val) && is_number(valReference)) {
		if (val > valReference) {
			return true;
		}
	}
    return false;
}

function is_number_less_than(val, valReference) {
    if (is_number(val) && is_number(valReference)) {
		if (val < valReference) {
			return true;
		}
	}
    return false;
}

function is_integer(val) {
    return parseInt(val, 10) == val;
}
/*FIN VALIDACIONES NROS*/

/*VALIDACIONES FECHA */
function is_date(val) {
    var value = new Date(val);
    if (value == NaN || value == Invalid) {
		return false;
    }
    else {
		var d = value.substr(0, 2);
        var m = value.substr(3, 2);
        var a = value.substr(6, 4);
        if ((m >= 12) || (d >= 31)) {
            return false;
        }
        else {
			if ((a % 4 != 0) && (m == 2) && (d > 28)) {
				return false;
            }
            else {
				if ((((m == 4) || (m == 6) || (m == 9) || (m == 11)) && (d > 30)) || ((m == 2) && (d > 29))) {
					return false;
                }
                else {
					return true;
				}
            }
        }
    }
    return false;
}