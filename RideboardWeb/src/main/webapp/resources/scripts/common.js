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
    initLayout();

    $(document).on('click', '*[data-action-url]', function (e) {
        if ($(this).data("allow-action") != 'False')
            window.location = $(this).attr("data-action-url");
    });

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

    $("[class^=num_]").ForceNumericOnly();
});

hotKey2();

function initSelectElement() {
    $('select').each(function () {
        var str = $(this).attr('onchange');
        var cls = $(this).attr('class');
        if ((cls == null || cls == 'undefined' || cls == '') || (cls != 'multiselect' && cls.indexOf('ignoreStyle') == -1  )) {
            if (str != null && str != 'undefined' && str != '') {
                str = str.substring(0, str.lastIndexOf(")") + 1).trim();
                var n = str.indexOf("(");
                var s2 = str.substring(n);
                var s1 = str.substring(0, n);
                var arg = (s2 == '()' ? '' : s2.substring(1, s2.length - 1));
                $(this).selectmenu(
                    {
                        change: function () {
                            window[s1](arg);
                        }
                    }).selectmenu("menuWidget").addClass("overflow");
            } else {
                $(this).selectmenu().selectmenu("menuWidget").addClass("overflow");
            }
            $(this).selectmenu().selectmenu("menuWidget").css("max-height", "150px");
        }
    });
}

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

function openAjaxDialog(sUrl, sMethod, sDivName, sTitle, width, height, newDiv, frmName) {
    openAjaxDialog(sUrl, sMethod, sDivName, sTitle, width, height, newDiv, frmName, null);
}
function openAjaxDialog(sUrl, sMethod, sDivName, sTitle, width, height, newDiv, callback) {
    openAjaxDialog(sUrl, sMethod, sDivName, sTitle, width, height, newDiv, callback);
}
function openAjaxDialog(sUrl, sMethod, sDivName, sTitle, width, height, newDiv, frmName, callback) {
    newDiv = newDiv || !document.getElementById(sDivName);
    var divItem = newDiv ? document.createElement('div') : document.getElementById(sDivName);
    divItem.id = sDivName;
    divItem.style.width = width;
    divItem.style.height = height;
    divItem.title = sTitle;
    divItem.style.zIndex = 5001;
    divItem.className = 'popup';
    document.getElementsByTagName('body')[0].appendChild(divItem);
    
    showDialogDiv(sDivName, width, height, false);
    makeAjaxRequestPopup(sUrl, sMethod, sDivName, newDiv, frmName, callback);
}
function closeAjaxDialog(divName, delDiv) {
    closeDialogDiv(divName);
    $('#' + divName).innerHtml = '';
    if (delDiv) {
        try { document.getElementsByTagName('body')[0].removeChild(document.getElementById(divName)); } catch (e) {}
    }
}
function makeAjaxRequestPopup(sUrl, sMethod, sDivTarget, newDiv, frmName, callback) {
    var objDiv = document.getElementById(sDivTarget);
    if (objDiv == null || sUrl == null || sUrl == '') return;
    sMethod = sMethod == null || sMethod == '' ? 'GET' : sMethod.toUpperCase();
    var request_data;

    if (typeof frmName == 'string') // now frm_name is a multi-usage param
    {
        request_data = $('#' + frmName).serialize(); // if it is a string, serialize the element with that id
    }
    else if (typeof frmName == 'object')
    {
        request_data = frmName; // if it is an object, just simply use it
    }

    if (sMethod == 'LOCAL') {
        $(objDiv).load(sUrl);
    } else {
        objDiv.innerHTML = ""; 
        showLoading();
        $.ajax(
          {
              url: sUrl,
              method: sMethod,
              data: request_data,
              success: function (jqXHR, textStatus, errorThrown) {
                  hideLoading();

                  $('#' + sDivTarget).html(jqXHR);
                  $('#' + sDivTarget).css('font-size', $('#mainBody').css('font-size'));  // add-on to fix dialog failed to adjust universal fontsize
                  initLayout();

                  if (typeof $('#' + sDivTarget).dialog != 'undefined') {
                      $('#' + sDivTarget).dialog('option', 'position', $('#' + sDivTarget).dialog('option', 'position'));
                  }

                  if (callback != undefined) callback();
              },
              error: function (jqXHR, textStatus, errorThrown) {
                  hideLoading();
                  //$('#' + sDivTarget).html(jqXHR);
				  writeError(errorThrown);
				  closeAjaxDialog(sDivTarget, true);
              }
          });
    }
}
function makeAjaxRequest(frmName, callback) {
    if (frmName) {
        var frm = $("form#" + frmName);
        if (frm) {
            var sMethod = frm.attr('method');
            var sUrl = frm.attr('action');
            var sType = frm.attr('type');
            if(sType) { sType = sType; } else { sType = sMethod; }
            var data = sType=='json'?frm.serializeObject():frm.serialize();
            showLoading();
            $.ajax(
			  {
			      url: sUrl,
			      method: sMethod,
                  type: sType,
			      data: data,
			      success: function (data, textStatus) {
			          hideLoading();
			          if (callback) callback(data, textStatus);
			      },
			      error: function (data, textStatus, errorThrown) {
			          hideLoading();
			          if (callback) callback(data, textStatus, errorThrown);
			          else //document.getElementById("ajaxMessageDiv").innerHTML = data;
						showMessagePrompt('Ajax submission error : ' + data, 'Error');
			      }
			  });
        }
    }
}
function getXMLR() {
    return (window.XMLHttpRequest) ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
}
function showMessagePrompt(message, level, preCloseHandler)
{
    if (typeof level != "string" || level == "")
    {
        level = "info";
    }

    if (typeof message != "undefined")
    {
        var clone_id = 'message_prompt_dialog_' + Math.floor((Math.random() * 1000000) + 1).toString();
        var clone = $('#message_prompt_dialog_template').clone();
        clone.prop('id', clone_id);

        $('#message_prompt_dialog_template').after(clone);

        var title = level.charAt(0).toUpperCase() + level.slice(1).toLowerCase();
		document.getElementById(clone_id).title = title;
        //$('#' + clone_id).title = title;
        $('#' + clone_id + ' > .icon').prop('src', base + '/Images/message_prompt_' + level + '.png');
        $('#' + clone_id + ' > .message').html(message);

        var opts = {
            buttons: [
                { 
                    text: "OK", 
                    click: function () {
                        if (typeof preCloseHandler === "function")
                        {
                            preCloseHandler();
                        }

                        $(this).dialog('close');
                    } 
                }
            ],
            close: function () {
                $(this).dialog("destroy").remove();
            }
        };
        showDialogDiv(clone_id, 'auto', 'auto', false, opts, level, true);
    }
}

function showMessagePrompt(message, level, preCloseHandler, postCloseHandler)
{
    if (typeof level != "string" || level == "")
    {
        level = "info";
    }

    if (typeof message != "undefined")
    {
        var clone_id = 'message_prompt_dialog_' + Math.floor((Math.random() * 1000000) + 1).toString();
        var clone = $('#message_prompt_dialog_template').clone();
        clone.prop('id', clone_id);

        $('#message_prompt_dialog_template').after(clone);

        var title = level.charAt(0).toUpperCase() + level.slice(1).toLowerCase();
		document.getElementById(clone_id).title = title;
        //$('#' + clone_id).title = title;
        $('#' + clone_id + ' > .icon').prop('src', base + '/Images/message_prompt_' + level + '.png');
        $('#' + clone_id + ' > .message').html(message);

        var opts = {
            buttons: [
                { 
                    text: "OK", 
                    click: function () {
                        if (typeof preCloseHandler === "function")
                        {
                            preCloseHandler();
                        }

                        $(this).dialog('close');

                        if (typeof postCloseHandler === "function")
                        {
                            postCloseHandler();
                        }
                    } 
                }
            ],
            close: function () {
                $(this).dialog("destroy").remove();
            }
        };
        showDialogDiv(clone_id, 'auto', 'auto', false, opts, level, true);
    }
}

function showCustomizedPrompt(message, level, btnOptions) {
    if (typeof level != "string" || level == "") {
        level = "info";
    }

    if (typeof message != "undefined") {
        var clone_id = 'message_prompt_dialog_' + Math.floor((Math.random() * 1000000) + 1).toString();
        var clone = $('#message_prompt_dialog_template').clone();
        clone.prop('id', clone_id);

        $('#message_prompt_dialog_template').after(clone);

        var title = level.charAt(0).toUpperCase() + level.slice(1).toLowerCase();
		document.getElementById(clone_id).title = title;
        //$('#' + clone_id).title = title;
        $('#' + clone_id + ' > .icon').prop('src', base + '/Images/message_prompt_' + level + '.png');
        $('#' + clone_id + ' > .message').html(message);

        var opts = {
            buttons: btnOptions,
            close: function () {
                $(this).dialog("destroy").remove();
            }
        };
        showDialogDiv(clone_id, 'auto', 'auto', false, opts, level);
    }
}

function showMessagePromptByMessageId(msg_cd, preCloseHandler) {
    var msg_text;
    var msg_level;

    $.ajax({
        cache: false,
        async: false,
        success: function (data) {
            if (data.success) {
                msg_text = data.msg_text;
                msg_level = data.msg_level;
            }
        },
        method: 'post',
        type: 'json',
        url: getDCSMessage_url,
        data: { msg_cd: msg_cd },
    });

    showMessagePrompt(msg_text, msg_level, preCloseHandler);
}

function showConfirmPrompt(message, yesEvent, noEvent, hasCancel) {
    if (typeof yesEvent == "undefined") {
        throw new Error("yesEvent must be defined when using showConfirmPrompt()");
    }

    if (typeof noEvent == "undefined") {
        noEvent = function () { $(this).dialog("close"); };
    }

    var clone_id = 'message_prompt_dialog_' + Math.floor((Math.random() * 1000000) + 1).toString();
    var clone = $('#message_prompt_dialog_template').clone();
    clone.prop('id', clone_id);

    $('#message_prompt_dialog_template').after(clone);

	document.getElementById(clone_id).title = 'Confirmation';
    $('#' + clone_id + ' > .icon').prop('src', base + '/Images/message_prompt_question.png');
    $('#' + clone_id + ' > .message').html(message);


    if (hasCancel) {
        var opts = {
            buttons: [
                { text: "Yes", click: yesEvent },
                { text: "No", click: noEvent },
                { text: "Cancel", click: function () { $(this).dialog("destroy").remove() } }
            ],
            close: function () {
                $(this).dialog("destroy").remove();
            }
        };
    } else {
        var opts = {
            buttons: [
                { text: "Yes", click: yesEvent },
                { text: "No", click: noEvent }
            ],
            close: function () {
                $(this).dialog("destroy").remove();
            }
        };
    }
    showDialogDiv(clone_id, 'auto', 'auto', false, opts, 'Confirmation', true);
    return clone_id;
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

function writeError(errMsg) {
	$('#errorDiv').html(errMsg);
}

function initLayout() {
    try
    {
        window.moveTo(0, 0);
        window.resizeTo(screen.width, screen.availHeight);
        $(window).resize(adjustBodyContentDimension).trigger('resize');
    }
    catch(err)
    {
        console.log('Init Layout move and resize window error'); 
    }

    $(function () {
        $("#sizingSlider").slider({
            //range: "max",
            min: 1.0,
            max: 2.0,
            value: 1.0,
            step: 0.1,
            slide: function (event, ui) {
                resize(ui);
            }
        });
    });

   var rightClickMenuW =
        {
            'addShortcut': {
                name: "Add Shortcut",
                icon: "add",
                callback: function (key, options) {
                    showMessagePrompt('Show Add Shortcut Popup ...', "Info");
                }
            }
        };

//    $("#shortcutTr").contextMenu({
//        selector: "td[type='shortcut']",
//        items: rightClickMenuW
//    });

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
                        e.char == '6' || e.char == '7' || e.char == '8' || e.char == '9' || e.char == '0' );
	            }
	        });
	    });
	};

    $("[class$=numField]").ForceNumericOnly();

    $("[class^=dec_]").spinner({
        min: 0,
        step: 0.01,
        numberFormat: "9"
    });
    $("[class^=num_]").ForceNumericOnly();

    //if (userId != '' && userId != 'null') assignFontSize(layoutUrl);
}
