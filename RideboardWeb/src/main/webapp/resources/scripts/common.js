var isIE = true;
navigator.sayswho = (function () {
    var N = navigator.appName, ua = navigator.userAgent, tem;

    // if IE11+
    if (new RegExp("Trident/.*rv:([0-9]{1,}[\.0-9]{0,})").exec(ua) !== null) {
        var M = ["Internet Explorer"];
        if (M && (tem = ua.match(/rv:([0-9]{1,}[\.0-9]{0,})/)) != null) M[2] = tem[1];
        M = M ? [M[0], M[2]] : [N, navigator.appVersion, '-?'];
        return M;
    }

    var M = ua.match(/(opera|chrome|safari|firefox|msie)\/?\s*(\.?\d+(\.\d+)*)/i);
    if (M && (tem = ua.match(/version\/([\.\d]+)/i)) != null) M[2] = tem[1];
    M = M ? [M[1], M[2]] : [N, navigator.appVersion, '-?'];
    return M;
})();


function disableCtrlCombinationAndBack(e) {
    var forbiddenKeys = new Array('n', 'r', 'p');
    var realEvent;
    var key;
    var isCtrl;
    var isAlt;
    var tagName;
    var isIE;

    if (window.event) {
        //IE
        isIE = true;
        key = event.keyCode;
        tagName = event.srcElement.type;
        realEvent = event;
    }
    else {
        //firefox
        isIE = false;
        key = e.which;
        tagName = e.target.type;
        realEvent = e;
    }

    isCtrl = realEvent.ctrlKey;
    isAlt = realEvent.altKey;



    if (tagName == null)  //if nothing is focused
        tagName = "";

    // block backspace go back function
    if ((key == 8 && tagName.toUpperCase() != "TEXT" &&
        tagName.toUpperCase() != "TEXTAREA" && tagName.toUpperCase() != "PASSWORD")) {
        return false;
    }

    // block F5
    if (key == 116) {
        if (isIE) {
            event.keyCode = 0;
            return false;
        }
        else {
            if (realEvent.charCode == null || realEvent.charCode == 0)
                return false;
        }
    }

    // block F11
    if (key == 122) {

        if (isIE) {
            event.keyCode = 0;
            return false;
        }
        else {
            if (realEvent.charCode == null || realEvent.charCode == 0)
                return false;
        }
    }

    // block alternate left/right arrow
    if ((key == 37 || key == 39) && isAlt)
        return false;

    //if ctrl is pressed check if other key is in forbidenKeys array
    if (isCtrl) {
        for (i = 0; i < forbiddenKeys.length; i++) {
            //case-insensitive comparation
            if (forbiddenKeys[i].toLowerCase() == String.fromCharCode(key).toLowerCase())
                return false;
        }
    }
    return true;
}

function clickIE4() {
    if (event.button == 2) {
        return false;
    }
}

function clickNS4(e) {
    if (document.layers || document.getElementById && !document.all) {
        if (e.which == 2 || e.which == 3) {
            return false;
        }
    }
}

function hotKey() {
    if (document.layers) {
        document.captureEvents(Event.MOUSEDOWN);
        document.ona = clickNS4;
    }
    else if (document.all && !document.getElementById) {
        document.ona = clickIE4;
    }

    document.oncontextmenu = new Function("return false")
    if (document.layers)
        document.captureEvents(Event.KEYPRESS)
    document.onkeydown = disableCtrlCombinationAndBack;
    document.ona = disableCtrlCombinationAndBack;
    document.ona = disableCtrlCombinationAndBack;
}

function hotKey2() {
    var allowsKeyCodes = new Array('67', '80', '86', '88', '99', '112', '118', '120');  // c, x, v, p
    jQuery(window).keydown(function (e) {
        if (e.ctrlKey) {
            var allows = false;
            for (var i = 0; i < allowsKeyCodes.length; i++) {
                if (e.keyCode == allowsKeyCodes[i]) {
                    allows = true;
                    break;
                }
            }
            if (!allows) {
                e.stopImmediatePropagation();
                e.preventDefault(); e.stopPropagation();
            }
        }

        //-------Code for blocking Backspace-----------
        var realEvent;
        var key;
        var tagName;
        var isIE;

        if (window.event) {
            //IE
            isIE = true;
            key = event.keyCode;
            tagName = event.srcElement.type;
            realEvent = event;
        }
        else {
            //firefox
            isIE = false;
            key = e.which;
            tagName = e.target.type;
            realEvent = e;
        }

        if (tagName == null)  //if nothing is focused
            tagName = "";

        // block backspace go back function
        if ((key == 8 && tagName.toUpperCase() != "TEXT" &&
            tagName.toUpperCase() != "TEXTAREA" && tagName.toUpperCase() != "PASSWORD")) {
            return false;
        }
        //-------Code for blocking Backspace-----------
        if (e.altKey) {
            e.stopImmediatePropagation();
            e.preventDefault(); e.stopPropagation();
            return false;
        }
        if (e.keyCode >= 112 && e.keyCode < 123) {
            e.keyCode = 0;
            return false;
        }
    });
    document.oncontextmenu = new Function("return false");
}

function filterScript(content) {
    if (content == null || content == '')
        return content;
    str = content.replace(/<script([^>])*>/gi, '<script>\/*');
    str = str.replace(/<\/script([^>])*>/gi, '*\/<\/script>');
    return str;
}

//for security issue, don't allow back page from browser.
window.history.forward();
function noBack() { window.history.forward(); }

$(document).ready(function () {
    //Change input char to uppercase 
    $(".text-uppercase").keypress(function (e) {
        if (e.char.match(/[a-z]/)) {
            $(this).val(function (_, val) {
                return val + String.fromCharCode(e.which).toLocaleUpperCase();
            });
            return false;
        }
    });

    //change paste data to uppercase 
    $(".text-uppercase").keyup(function (e) {
        if (e.ctrlKey == true && e.keyCode == 86) {
            this.value = this.value.toLocaleUpperCase();
        }
    });

    jQuery.fn.ForceNumericOnly =
        function () {
            return this.each(function () {
                $(this).keydown(function (e) {
                    var key = e.charCode || e.keyCode || 0;
                    // allow backspace, tab, delete, enter, arrows, numbers and keypad numbers ONLY
                    // home, end, period, and numpad decimal

                    if (key != 229) {
                        return (
                            key == 8 ||
                            key == 9 ||
                            key == 13 ||
                            key == 46 ||
                            key == 110 ||
                            key == 190 ||
                            (key >= 35 && key <= 40) ||
                            (key >= 48 && key <= 57) ||
                            (key >= 96 && key <= 105));
                    }
                    else {
                        return (
                            e.char == '.' ||
                            e.char == '1' || e.char == '2' || e.char == '3' || e.char == '4' || e.char == '5' ||
                            e.char == '6' || e.char == '7' || e.char == '8' || e.char == '9' || e.char == '0');
                    }
                });
            });
        };

    //$("[class^=dec_]").spinner({
    //    min: 0,
    //    step: 0.01,
    //    numberFormat: "9"
    //});
    $("[class^=num_]").ForceNumericOnly();
});

hotKey2();


function showModalDiv(divName, width, height, dialogOptions) {
    var opts = {
        width: width,
        height: height,
        overlayId: 'confirm-overlay',
        containerId: 'confirm-container',
        onShow: function (dialog) {
            var modal = this;
        }
    };

    if (typeof dialogOptions == 'object') {
        opts = $.extend(opts, dialogOptions);
    }

    $('#' + divName).modal(opts);
}

function showDialogDiv(divName, width, height, hasCloseBtn, dialogOptions, sTitle, noCloseCorner) {
    var opts = {
        autoOpen: false,
        resizable: false,
        modal: true,
        width: (typeof width != 'undefined') ? width : 'auto',
        height: (typeof height != 'undefined') ? height : 'auto',
        // workaround of the issue which caused iframes are displayed over the dialog's overlay  
        open: function (event, ui) {
            $('iframe:visible:not(.hidden-by-ui-dialog)').addClass('hidden-by-ui-dialog').hide();
        },
        beforeClose: function (event, ui) {
            $('iframe:hidden').filter('.hidden-by-ui-dialog').removeClass('hidden-by-ui-dialog').show();
        },
    };

    if (hasCloseBtn)
    {
        $.extend(opts, {
            buttons: {
                Close: function () { $(this).dialog("close"); }
            }
        });
    }
    if (noCloseCorner) {
        $.extend(opts, { dialogClass: "dialog-no-close" });
    }

    if (typeof dialogOptions == 'object')
    {
        $.extend(opts, dialogOptions);
    }

    $('#' + divName).css('max-width', window.innerWidth);
    $('#' + divName).css('max-height', window.innerHeight);

    $('#' + divName).dialog(opts);
    $('div#'+divName).bind('dialogclose', function(event){
        closeAjaxDialog(divName, true);
    });
    $('#' + divName).title = sTitle;
    $('#' + divName).dialog("open");
}

function closeDialogDiv(divName) {
    $('#' + divName).dialog("close");
    try { $('#' + divName).style.display = "none"; } catch (e) { }
}

function showLoading() {
    $('#loadingPage').css('display', 'block');
}
function hideLoading() {
    $('#loadingPage').css('display', 'none');
}

function openProfileDiv() {
	$('#divProfile').css('display', 'block');
}

function closeProfileDiv() {
	$('#divProfile').css('display', 'none');
}