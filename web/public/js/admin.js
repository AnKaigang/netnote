/// <reference path="../Scripts/jquery-1.7.1-vsdoc.js" />
var g_blnCheckUnload = true;

$.ajaxSetup({
    type: 'post',
    dataType: 'json',
    contentType: 'application/json; charset=utf-8'
});

function AjaxPost(url, postData, successFunc) {
    $.ajax({
        url: url,
        data: postData,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json; charset=utf8',
        success: function (data) {
            if (data.d) {
                successFunc(data.d);
            }
        },
        error: function (xhr) {
        }
    });
}

function GetNewMsgCount() {
    $.ajax({
        url: '/msg/unreadcount',
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            if (data && data.count > 0) {
                var span = document.createElement("span");
                span.setAttribute("class", "new_msg");
                span.innerHTML = "(" + data.count + ")";
                $("#lnk_site_msg").append(span);
            }
        }
    });
}

function insertTag(tag) {
    var txbTag = document.getElementById("Editor_Edit_Advanced_txbTag");
    if (txbTag.value == '') {
        txbTag.value += tagname;
    }
    else {
        txbTag.value += "," + tagname;
    }
}

var blogEditor = {};

blogEditor.getTitle = function () {
    return $("#Editor_Edit_txbTitle").val();
}

blogEditor.setTitle = function (content) {
    $("#Editor_Edit_txbTitle").val(content);
}

blogEditor.getContent = function () {
    var editorContent;
    if (currentUseEditor == 1) {
        editorContent = document.getElementById(currentEditorId).GetDocument().body.innerHTML;
    } else if (currentUseEditor == 3) {
        if (tinyMCE.get(currentEditorId)) {
            editorContent = tinyMCE.get(currentEditorId).getContent();
        } else {
            editorContent = document.getElementById(currentEditorId).value;
        }
    } else {
        editorContent = document.getElementById(currentEditorId).value;
    }

    var regex = /^\s*<br>\s*$/ig;
    if (regex.test(editorContent)) {
        editorContent = '';
    }
    return editorContent;
}

blogEditor.setContent = function (content) {
    if (currentUseEditor == 1) {
        document.getElementById(currentEditorId).GetDocument().body.innerHTML = content;
    } else if (currentUseEditor == 3) {
        if (tinyMCE.get(currentEditorId)) {
            editorContent = tinyMCE.get(currentEditorId).setContent(content);
        } else {
            document.getElementById(currentEditorId).value = content;
        }
    } else {
        document.getElementById(currentEditorId).value = content;
    }
}

blogEditor.restoreContent = function (content) {
    if (content.indexOf('<p>---恢复内容开始---</p>') < 0) {
        content = '<p>---恢复内容开始---</p>' + content + '<p>---恢复内容结束---</p>';
    }

    if (currentUseEditor == 1) {
        document.getElementById(currentEditorId).GetDocument().body.innerHTML = content + document.getElementById(currentEditorId).GetDocument().body.innerHTML;
    } else if (currentUseEditor == 3) {
        if (tinyMCE.get(currentEditorId)) {
            editorContent = tinyMCE.get(currentEditorId).setContent(content + tinyMCE.get(currentEditorId).getContent());
        } else {
            document.getElementById(currentEditorId).value = content + document.getElementById(currentEditorId).value;
        }
    } else {
        document.getElementById(currentEditorId).value = content + document.getElementById(currentEditorId).value;
    }
}

function insertDescImg() {
    var imgSrc = prompt('请输入图片网址', 'http://');
    if (!imgSrc) {
        return false;
    }
    var strImg = "<img src=\"" + imgSrc + "\" class=\"desc_img\"/>";
    document.getElementById("Editor_Edit_Advanced_txbExcerpt").value = strImg + document.getElementById("Editor_Edit_Advanced_txbExcerpt").value;
    document.getElementById("Editor_Edit_Advanced_txbExcerpt").focus();
    return false;
}

function CheckInput() {
    $('#tip_posging').html("正在提交...");
    var postTitle = $.trim(document.getElementById(txbTitleId).value);
    var postBody = $.trim(getBlogEditorContent());
    var message = '';
    if (postTitle == '') {
        message = '请输入标题！';
    }
    if (postBody == '') {
        if (message != '') {
            message += '\n';
        }
        message += '请输入内容！';
    }

    
    var isAuthenticated = false;
    $.ajax({
        type:'get',
        url: '/user/IsAuthenticated',
        async: false,
        timeout: 30000,
        success: function (data) {
            isAuthenticated = data;
            if (!isAuthenticated) {
                message = "提交失败！当前登录已过期。不要关闭当前窗口！\n请打开新窗口，访问 i.cnblogs.com ，成功登录后，再回到该窗口进行提交";
            }
        },
        error: function (xhr) {
            message = "提交失败！失败原因：\n" + xhr.responseText.replace(/(<([^>]+)>)/ig, '');
        }
    });

    if (message != '') {
        alert(message);
        $('#tip_posging').html("");
        return false;
    }

    BypassCheck();
    return true;
}

function PubishConfirm(element) {
    if (element.checked) {
        if (!confirm("友情提醒：\n博客园首页是万众瞩目的地方，一定要是精品文章哦:)\n确认要发布到博客园首页吗?")) {
            element.checked = false;
        }
    }
}

function DeletePost(entryId, title) {
    if (!confirm('确认删除“' + title + "”吗？")) return false;
    $("#entry_" + entryId).html("<td colspan='7'><span style='color:red'>删除操作中...</span></td>");
    $.ajax({
        url: '/post/delete',
        type: 'post',
        data: '{"postId":' + entryId + '}',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data) {
            if (data.isSuccess) {
                $("#entry_" + entryId).html("<td colspan='7'><span style='color:red'>删除成功！</span></td>");
            } else {
                $("#entry_" + entryId).html("<td colspan='7'><span style='color:red'>删除失败！" + data.msg + "</span></td>");
            }
        },
        error: function (xhr) {
            $("#entry_" + entryId).html("<td colspan='7'><span style='color:red'>" + xhr.responseText + "</span></td>");
        }
    });
}

function BypassCheck() { g_blnCheckUnload = false; }

function CancelPost() {
    if (!isWriteSomething()) {
        return true;
    }
    if (confirm('确认取消吗？')) {
        BypassCheck();
        return true;
    }
    else {
        return false;
    }
}

function canLeavePage() {
    localPreserver.saveDraftTitle();
    localPreserver.saveDraftBody();
    if (!g_blnCheckUnload) {
        return true;
    }
    if (!isWriteSomething()) {
        return true;
    }
    return false;
}

function isWriteSomething() {
    if ($.trim(document.getElementById(txbTitleId).value) != '') {
        return true;
    }
    if ($.trim(getBlogEditorContent()) != '') {
        return true;
    }
    return false;
}

function getBlogEditorContent() {
    return blogEditor.getContent();
}

function setBlogEditorContent(content) {
    blogEditor.setContent(content);
}

function checkEntryName() {
    var element = $('#Editor_Edit_Advanced_txbEntryName');
    var entryName = element.val();
    if (entryName) {
        var regex = /^[a-zA-Z0-9][\w-_]{1,149}$/g;
        if (!regex.test(entryName)) {
            $("#entryname_tip").css('color', 'red').html('"' + entryName + '"不合法，只能使用字母/数字/-/_，不能超过150个字符');
            element.val('');
            return;
        }
        var entryId = $("#currentPostId").html();
        if (entryId == '') {
            entryId = '0';
        }
        $("#entryname_tip").css('color', 'red').html("正在检查EntryName是否存在同名...");
        $.ajax({
            url: '/post/IsEntryNameDuplicated',
            type: 'post',
            data: '{"postId":' + entryId + ',"entryName":"' + entryName + '"}',
            contentType: 'application/json',
            dataType: 'json',
            success: function (data) {
                if (data) {
                    $("#entryname_tip").html('"' + entryName + '"已存在，请换一个名称！');
                    element.val('');
                } else {
                    $("#entryname_tip").css('color', 'green').html("检查通过");
                }
            },
            error: function (xhr) {
                $("#entryname_tip").css('color', 'red').html(xhr.responseText);
            }
        });
    }

}

function OnHomeCandidateChange(element) {
    if (element.checked) {
        var checkbox = document.getElementById('Editor_Edit_APOptions_APSiteHome_cbIsPublishToSiteHome');
        if (checkbox && checkbox.checked) {
            checkbox.checked = false;
        }
    }
}

function OnSiteHomeChange(element) {
    if (element.checked) {
        var checkbox = document.getElementById('Editor_Edit_APOptions_APSiteHome_cbHomeCandidate');
        if (checkbox && checkbox.checked) {
            checkbox.checked = false;
        }
    }
}

function HandleTabKey(evt) {
    if (evt.keyCode == 9) {
        var editor1 = document.getElementById(currentEditorId);
        editor1.PasteHTML('　　');
        if (evt.preventDefault) { evt.preventDefault(); }
        else { evt.returnValue = false; }
        editor1.FocusDocument();
    }
}

function CuteEditor_attachEvents() {
    var editor1 = document.getElementById(currentEditorId);
    var editdoc = editor1.GetDocument();
    if (editdoc.attachEvent)
        editdoc.attachEvent('onkeydown', HandleTabKey);
    else if (editdoc.addEventListener)
        editdoc.addEventListener('keydown', HandleTabKey, true);
}

function CuteEditor_detachEvents() {
    var editor1 = document.getElementById(currentEditorId);
    var editdoc = editor1.GetDocument();
    if (editdoc.detachEvent)
        editdoc.detachEvent('onkeydown', HandleTabKey);
    else if (editdoc.removeEventListener)
        editdoc.removeEventListener('keydown', HandleTabKey, true);
}
function CuteEditor_OnInitialized(editor) {
    CuteEditor_attachEvents();
}

function title_keydown(evt) {
    if (evt.keyCode == 9) {
        if (evt.preventDefault) { evt.preventDefault(); }
        else { evt.returnValue = false; }
        if (currentUseEditor == 1) {
            document.getElementById(currentEditorId).FocusDocument();
        }
        else if (currentUseEditor == 3) {
            tinyMCE.get(currentEditorId).focus();
        }
        else {
            document.getElementById(currentEditorId).focus();
        }
    }
}

//#endregion

//#region LocalPreserver

function localPreserver() { };

localPreserver.draftBodyKey = "DraftBody";
localPreserver.draftTitleKey = "DraftTitle";

localPreserver.saveDraftTitle = function () {
    if (isNewPost && window.localStorage) {
        var title = blogEditor.getTitle();
        if (title) {
            localStorage.setItem(localPreserver.draftTitleKey, title);
        }
    }
}

localPreserver.saveDraftBody = function () {
    if (window.localStorage) {
        var draftBody = blogEditor.getContent();
        if (draftBody && draftBody.length > 10) {
            var storedContent = localStorage.getItem(localPreserver.draftBodyKey);
            if (storedContent != draftBody) {
                localStorage.setItem(localPreserver.draftBodyKey, draftBody);
                $("#write_hint").css("color", "green").html('本地自动保存于' + new Date().toLocaleTimeString() + '，<a href="javascript:void(0);" onclick="localPreserver.showSaved();return false;">查看</a>');
            }
        }
    }
    else {
        $("#write_hint").css("color", "green").html("当前浏览器不支持HTML5，无法启用本地自动保存功能");
    }
}

localPreserver.showSaved = function () {
    var title = localStorage.getItem(localPreserver.draftTitleKey);
    var restoreOperation = '<span style="float:right"><a href="javascript:void(0);" onclick="localPreserver.loadDraft();$.cnblogs_close();return false;">恢复</a></span>';
    if (!title) title = '无标题';
    title += restoreOperation;
    var body = localStorage.getItem(localPreserver.draftBodyKey);
    $.cnblogs_bind({ title: title, content: body, width: "710px" });
}

localPreserver.showViewTip = function () {
    if (localStorage.getItem(localPreserver.draftBodyKey))
    {
        $("#write_hint").css("color", "green").html('<a href="javascript:void(0);" onclick="localPreserver.showSaved();return false;">查看自动保存内容</a>');
    }
}

localPreserver.loadDraft = function () {
    if (window.localStorage) {
        editorContent = blogEditor.getContent();
        if (!blogEditor.getTitle()) {
			blogEditor.setTitle(localStorage.getItem(localPreserver.draftTitleKey));
		}

        //if (!editorContent || editorContent == '编辑器加载中...') {
        var draftContent = localStorage.getItem(localPreserver.draftBodyKey);
        if (draftContent) {
            blogEditor.restoreContent(draftContent);
        }
        //}
    }
}

localPreserver.clearDraft = function () {
    if (window.localStorage) {
        localStorage.removeItem(localPreserver.draftTitleKey);
        localStorage.removeItem(localPreserver.draftBodyKey);
    }
}

//#endregion

function parseText(textarea, t) {
    var doc = textarea.document;
    textarea.focus();
    if (doc) {
        textarea.focus();
        doc.selection.createRange().collapse;
        textarea.document.selection.createRange().text = t;
    }
    else if (textarea.selectionStart || textarea.selectionStart == '0') {
        var s = textarea.selectionStart;
        var e = textarea.selectionEnd;
        var val = textarea.value;
        var start = val.substring(0, s);
        var end = val.substring(e);
        textarea.value = start + t + end;
        textarea.selectionStart = textarea.selectionEnd = s + t.length;
    }
}

function insert_tab(el, e) {
    if (e.type == 'keydown' && e.keyCode == 9) {
        e.preventDefault();
        parseText(el, '    ');
        return false;
    }
}

function open_new_window(url, width, height, offset) {
    document.domain = 'cnblogs.' + location.hostname.substring(location.hostname.lastIndexOf(".") + 1, location.hostname.length);
    var leftVal = (screen.width - width) / 2 - offset;
    var topVal = (screen.height - height) / 2 - offset;
    var new_window = window.open(url, '_blank', 'width=' + width + ',height=' + height + ',toolbars=0,resizable=1,left=' + leftVal + ',top=' + topVal);
    new_window.focus();
}

function markdown_image_upload(id) {
    var url = 'http://upload.cnblogs' + location.hostname.substring(location.hostname.lastIndexOf(".")) +
        '/imageuploader/upload?host=www.cnblogs.com&editor=4#' + id;
    open_new_window(url, 450, 120, 200);
}

function markdown_code_insert(id) {
    var url = "http://util.cnblogs.com/insert_code_text.html#" + id;
    open_new_window(url, 550, 480, 200);
}

var SUCCESS_AUTOSAVE_TEXT = '成功自动保存于服务器，保存时间 ';
var SUCCESS_FAILURE_TEXT = '自动保存失败（联系邮箱contact@cnblogs.com），发生时间 ';
function autosaveToServer() {
    var data = {};
    var postIdElement = $('#currentPostId').html();
    if (postIdElement.length > 0) {
        data.postId = parseInt(postIdElement);
    }  else {
        data.postId = null;
    }
    data.title = $('#' + txbTitleId).val();
    data.content = blogEditor.getContent();
    $.ajax({
        url: '/autosave/save',
        data: JSON.stringify(data),
        contentType: 'application/json',
        type: 'post',
        dataType: 'json',
        success: function (data) {
            if (data) {
                $("#write_hint").css("color", "green").html(SUCCESS_AUTOSAVE_TEXT + new Date().toLocaleTimeString());
            } else {
                $("#write_hint").css("color", "red").html(SUCCESS_FAILURE_TEXT + new Date().toLocaleTimeString());
            }
        },
        error: function (response) {
            {
                if (response.status > 0) {
                    $("#write_hint").css("color", "red").html(SUCCESS_FAILURE_TEXT + new Date().toLocaleTimeString());
                }
            }
        }
    });
}

function autosaveWriting() {
    autosaveToServer();
}

function beginningAutosave() {
    var write_hint = $("#write_hint");
    if (write_hint.html() == '') {
        write_hint.html('自动保存已启动，每隔2分钟自动备份所写内容');
    }
}